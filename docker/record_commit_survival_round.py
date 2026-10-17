#!/usr/bin/env python3
"""Archive a completed generation stage and record measurements without rerunning it."""
import argparse
import ast
from collections import Counter
from datetime import datetime
import hashlib
import json
from pathlib import Path
import re
import shutil

KT = Path(__file__).resolve().parents[1]


def credentials():
    keys = []
    for node in ast.walk(ast.parse((KT / 'code/settings.py').read_text())):
        if isinstance(node, ast.Dict):
            for key, value in zip(node.keys, node.values):
                if isinstance(key, ast.Constant) and key.value == 'api_key':
                    keys.append(ast.literal_eval(value).encode())
    assert keys
    return [key for key in keys if key]


def analyze_log(path):
    lines = path.read_text(errors='replace').splitlines()
    active = None
    timeouts, ooms, errors = [], [], []
    for number, line in enumerate(lines, 1):
        match = re.match(r'(\d{4}-\d\d-\d\d \d\d:\d\d:\d\d,\d+) - (.*?) - (\w+) - (.*)', line)
        if not match:
            if 'OutOfMemoryError' in line:
                ooms.append({'line': number, 'class': active['class'] if active else None, 'text': line})
            continue
        timestamp, logger, level, message = match.groups()
        now = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S,%f')
        if active and 'return code:' not in message and (message.startswith('Running single unit test,') or message.startswith('javac ')):
            elapsed = (now - active['started']).total_seconds()
            if not active['returned'] and elapsed >= 599:
                timeouts.append({'class': active['class'], 'start_line': active['line'], 'next_command_line': number,
                                 'elapsed_until_next_command': elapsed,
                                 'evidence': '600s runner timeout inferred from no return-code log before next command'})
            active = None
        if message.startswith('Running single unit test, testclass: '):
            active = {'class': message.split('testclass: ', 1)[1], 'started': now, 'line': number, 'returned': False}
        if active and message.startswith('return code:'):
            active['returned'] = True
        if active and message.startswith('error occured in execute test class'):
            active['returned'] = True  # Non-timeout runner exception already terminated this attempt.
        if 'OutOfMemoryError' in message:
            ooms.append({'line': number, 'class': active['class'] if active else None, 'text': message})
        if level == 'ERROR':
            errors.append({'line': number, 'message': message})
    if active and not active['returned']:
        explicit = [i for i, line in enumerate(lines, 1) if 'Time takes too long while executing test class' in line and active['class'] in line]
        if explicit:
            timeouts.append({'class': active['class'], 'start_line': active['line'], 'explicit_lines': explicit})
    return {'file': path.name, 'timeouts': timeouts, 'out_of_memory_occurrences': ooms, 'error_records': errors,
            'framework_completed': len(re.findall(r'Completed test class framework generation for ', '\n'.join(lines))),
            'test_generation_completed': len(re.findall(r'Completed test case generation for ', '\n'.join(lines))),
            'explicit_timeout_mentions': sum('Time takes too long while executing test class' in line for line in lines)}


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--round', type=int, required=True, choices=[1, 2, 3])
    parser.add_argument('--repo', type=Path, required=True)
    parser.add_argument('--rerun', action='store_true')
    args = parser.parse_args()
    suffix = '-rerun' if args.rerun else ''
    stage = f'commit-survival-round{args.round}{suffix}'
    source = KT / 'results' / stage
    dest = KT / 'results/commit-survival' / f'round{args.round}{suffix}'
    assert source.is_dir() and not source.is_symlink()
    project = source / 'commons-codec'
    run = json.loads((project / 'run.json').read_text())
    assert not dest.exists()
    keys = credentials()
    for path in source.rglob('*'):
        if path.is_file():
            assert all(key not in path.read_bytes() for key in keys), 'Secret detected; archive withheld'
    tasks = json.loads((KT / 'data/commit-survival/commons-codec/dataset_info.json').read_text())['commons-codec']['focal-methods']
    assert len(tasks) == 52
    test_files = sorted((project / 'test_classes').glob('*_Test.java'))
    ids = {p.name.removesuffix('_Test.java') for p in test_files}
    expected = {t['id'] for t in tasks}
    assert ids <= expected
    logs = [analyze_log(p) for p in sorted(project.glob('*.log'))]
    assert len(logs) == 2
    summary_path = project / 'reports/summary.json'
    summary = json.loads(summary_path.read_text()) if summary_path.exists() else {}
    items = [value for key, value in summary.items() if isinstance(value, dict)]
    raw_results = {}
    for log in project.glob('*-coverage.log'):
        for line in log.read_text(errors='replace').splitlines():
            marker = ' - evaluations.coverage_test - INFO - '
            if marker in line and line.split(marker, 1)[1].startswith("{'org.apache.commons.codec."):
                raw_results = ast.literal_eval(line.split(marker, 1)[1])
    if raw_results:
        (project / 'authors-raw-test-results.json').write_text(json.dumps(raw_results, indent=2) + '\n')
    console_path = KT / 'evaluation/logs' / f'{stage}-console.log'
    console = console_path.read_text(errors='replace') if console_path.exists() else ''
    summary_failure = None
    if run['coverage_rc'] and any("ValueError: could not convert string to float: 'n/a'" in text for text in [console] + [p.read_text(errors='replace') for p in project.glob('*-coverage.log')]):
        summary_failure = "ValueError: could not convert string to float: 'n/a' in the reference evaluator's branch-coverage parser"
    tests = sum(item.get('test_cases', 0) for item in items)
    passed = sum(item.get('passed_cases', 0) for item in items)
    compiled = sum(item.get('test_cases', 0) for item in items if item.get('error_type') != 'compile error')
    errors = [e for log in logs for e in log['error_records']]
    output_ids = sorted({match.group(1) for e in errors for match in re.finditer(r'(?:responses|fix)/([^/]+)/', e['message'])})
    evidence = {'round': args.round, 'rerun': args.rerun, 'stage': stage, 'run': run,
                'population_tasks': len(tasks), 'tasks_submitted': len(tasks) if not args.rerun else None,
                'test_class_files': len(test_files), 'missing_test_class_ids': sorted(expected - ids),
                'wall_seconds': (datetime.fromisoformat(run['end'].replace('Z', '+00:00')) - datetime.fromisoformat(run['start'].replace('Z', '+00:00'))).total_seconds(),
                'provider_routing': 'unpinned', 'providers_observed': [],
                'provider_note': 'Released LLMCaller returns message content only and does not log response provider metadata; provider identity unavailable.',
                'logs': logs, 'timeouts': sum(len(log['timeouts']) for log in logs),
                'out_of_memory_occurrences': sum(len(log['out_of_memory_occurrences']) for log in logs),
                'missing_output_directory_task_ids': output_ids,
                'authors_evaluator_reference': {'summary_available': bool(summary), 'task_entries': len(items),
                    'summary_failure': summary_failure,
                    'na_failure_nonblocking_authorized': args.round >= 2 and summary_failure is not None,
                    'test_cases': tests if summary else None, 'passed_cases': passed if summary else None, 'compiled_cases': compiled if summary else None,
                    'compile_pass_rate': summary.get('compile_pass_rate'), 'execution_pass_rate': summary.get('execution_pass_rate'),
                    'tests_per_produced_class': tests / len(test_files) if test_files and summary else None,
                    'error_type_counts': dict(Counter(item.get('error_type', 'none') for item in items)),
                    'raw_results_available': bool(raw_results), 'raw_task_entries': len(raw_results),
                    'raw_files_compiled': sum(v.get('error_type') != 'compile error' for v in raw_results.values()),
                    'raw_executed_test_cases': sum(v.get('test_cases', 0) for v in raw_results.values()),
                    'raw_passed_test_cases': sum(v.get('passed_cases', 0) for v in raw_results.values()),
                    'raw_note': 'Raw evaluator execution dictionary recovered from the log before coverage-summary parsing; not the authors final filtered/scored summary.'},
                'source_sha256': {p.name: hashlib.sha256(p.read_bytes()).hexdigest() for p in test_files}}
    (project / 'generation-summary.json').write_text(json.dumps(evidence, indent=2) + '\n')
    destination = args.repo / 'experiments/ktester-commit-survival/generated' / f'round{args.round}'
    destination.mkdir(parents=True, exist_ok=True)
    prefix = '' if args.round == 1 else f'r{args.round}-'
    for path in test_files:
        target = destination / (prefix + path.name)
        if target.exists():
            assert target.read_bytes() == path.read_bytes(), 'Refusing to overwrite an already archived source'
        else:
            shutil.copyfile(path, target)
    # Canonical archive obeys results/commit-survival/ while retaining the prescribed stage path.
    source.rename(dest)
    source.symlink_to(Path('commit-survival') / dest.name, target_is_directory=True)
    print(json.dumps({k: evidence[k] for k in ['round', 'run', 'test_class_files', 'missing_test_class_ids', 'wall_seconds', 'timeouts', 'out_of_memory_occurrences', 'missing_output_directory_task_ids', 'authors_evaluator_reference']}, indent=2))


if __name__ == '__main__':
    main()
