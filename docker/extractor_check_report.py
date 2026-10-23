#!/usr/bin/env python3
"""Validate the recorded runs and write the evidence-backed experiment summary."""
import ast
import collections
import hashlib
import json
from pathlib import Path
import platform
import subprocess
import sys

sys.dont_write_bytecode = True
ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / 'results/extractor-check'


def read(name):
    return json.loads((OUT / name).read_text())


def main():
    sys.path.insert(0, str(ROOT / 'code'))
    import networkx as nx
    import procedure.preprocess_project as pp

    # A focused mixed-reachability check: both equal-length paths survive and the
    # unreachable target's own line remains. No Java or author data is changed.
    graph = nx.DiGraph()
    for node, kind, lines in [(0, 'BEGIN', [0]), (1, 'STATEMENT', [10]),
                              (2, 'STATEMENT', [20]), (3, 'STATEMENT', [30]), (4, 'STATEMENT', [99])]:
        graph.add_node(node, kind=kind, lines=lines)
    graph.add_edges_from([(0, 1), (0, 2), (1, 3), (2, 3)])
    extractor = pp.InvokePatternExtractor.__new__(pp.InvokePatternExtractor)
    lines = sorted(extractor._get_lines_from_cfg(graph, [30, 99]))
    assert lines == [0, 10, 20, 30, 99] and pp.UNREACHABLE_TARGETS == 1
    graph.nodes[0]['kind'] = 'STATEMENT'
    no_begin = sorted(extractor._get_lines_from_cfg(graph, [30, 99]))
    assert no_begin == [30, 99] and pp.UNREACHABLE_TARGETS == 1
    syntax_files = sorted(ROOT.glob('docker/extractor_check_*.py')) + [ROOT / 'code/procedure/preprocess_project.py']
    for path in syntax_files:
        ast.parse(path.read_text())

    baseline = read('run1-baseline.json')
    assert len(baseline['errors']) == 1
    assert '_get_lines_from_cfg' in baseline['errors'][0]
    assert baseline['errors'][0].strip().splitlines()[-1].startswith('networkx.exception.NetworkXNoPath:')
    runs = {name: read(name + '.json') for name in ['run2-patched', 'run3-with-junit']}
    comparisons = {name: read(name + '.json') for name in ['invoke-compare', 'invoke-compare-junit']}
    for result in runs.values():
        assert not result['errors']
        assert result['lucene']['identifiers_equal']
    for result in comparisons.values():
        assert sum(result['counts'].values()) == len(result['entries'])
        assert result['counts'] == dict(collections.Counter(e['category'] for e in result['entries'].values())) | {
            k: v for k, v in result['counts'].items() if v == 0}
    fresh = read('run4-fresh.json')
    dataset_files = sorted(OUT.glob('fresh-dataset-check-attempt-*.json'))
    dataset = json.loads(dataset_files[-1].read_text())
    prompts = read('prompt-check.json')
    assert fresh['status'] == 'completed' and dataset['extraction_succeeded'] and prompts['status'] == 'passed'
    assert prompts['model_calls'] == 0
    warning_reports = {}
    for name in ['run1-baseline', 'run2-patched', 'run3-with-junit', 'run4-fresh']:
        log = (OUT / (name + '.txt')).read_text()
        errors = [line for line in log.splitlines() if line.startswith('Error:')]
        warning_reports[name] = {'java_error_lines': len(errors),
                                'junit_assert_warnings': log.count('Unsolved symbol : org.junit.Assert'),
                                'unreachable_counter_prints': log.count('UNREACHABLE_TARGETS='),
                                'messages': dict(sorted(collections.Counter(errors).items()))}
    assert warning_reports['run3-with-junit']['junit_assert_warnings'] == 0
    assert all(warning_reports[n]['unreachable_counter_prints'] == 1 for n in ['run2-patched', 'run3-with-junit', 'run4-fresh'])
    (OUT / 'warnings.json').write_text(json.dumps(warning_reports, indent=2) + '\n')
    snapshot = ROOT / 'scratch/extractor-check/cli-snapshot'
    before_junit = (snapshot / 'project_index/codegraph/commons-cli_invoke.json').read_bytes()
    after_junit = (snapshot / 'project_index-junit/codegraph/commons-cli_invoke.json').read_bytes()
    checks = {'baseline_expected_failure': True, 'patched_snapshot_completed': True,
              'junit_snapshot_completed': True, 'fresh_index_completed': True,
              'mixed_reachability': {'lines': lines, 'unreachable_targets': 1, 'passed': True},
              'no_begin_behavior': {'lines': no_begin, 'passed': True},
              'parsed_python_files': [str(p.relative_to(ROOT)) for p in syntax_files],
              'invoke_output_identical_before_after_junit': before_junit == after_junit,
              'prompt_check_passed': True, 'model_calls': 0}
    subprocess.run(['git', 'diff', '--check', 'HEAD', '--'] +
                   [str(p.relative_to(ROOT)) for p in syntax_files], cwd=ROOT, check=True)
    checks['source_whitespace_check_passed'] = True
    checks['raw_artifact_whitespace'] = 'Full artifact diff check flags original trailing whitespace in captured prompts, DatasetPrepare stdout, and patch.diff context. These evidence files are intentionally preserved.'
    (OUT / 'verification.json').write_text(json.dumps(checks, indent=2) + '\n')
    env = {'python': platform.python_version(), 'networkx': nx.__version__,
           'java': subprocess.run(['java', '-version'], text=True, capture_output=True).stderr,
           'maven': subprocess.check_output(['mvn', '-version'], text=True),
           'branch': subprocess.check_output(['git', 'branch', '--show-current'], cwd=ROOT, text=True).strip(),
           'base_commit': subprocess.check_output(['git', 'rev-parse', 'experiment/2026-10-week3-ktester-commit-survival'], cwd=ROOT, text=True).strip(),
           'snapshot_commit': subprocess.check_output(['git', '-C', str(ROOT / 'scratch/upstream/commons-cli'), 'rev-parse', '90b3f2e'], text=True).strip(),
           'jar_sha256': {str(p.relative_to(ROOT)): hashlib.sha256(p.read_bytes()).hexdigest()
                          for p in [ROOT / 'code/Java/project-info-process.jar', ROOT / 'code/Java/project-index-builder.jar']}}
    (OUT / 'environment.json').write_text(json.dumps(env, indent=2) + '\n')
    report = ['# KTester extractor check', '',
              'The specified NetworkX patch allows a fresh-commit index and prompt assembly to finish. It does not reproduce the author invoke index exactly.', '',
              '## Scope and provenance', '',
              f'Branch: `{env["branch"]}`. Base: `{env["base_commit"]}`. No push and no model calls.', '',
              'The requested `claude/ktester-extractor-check-cc-instructions-2026-10-week4.md` path was absent. The repository copy `docs/cc-instructions-extractor-check.md` was used and preserved as `instructions.md`.', '',
              'All experiment scripts ran in `docker/` using `docker compose run --rm ktester bash -c ...`, with the repository mounted at `/work`. Environment and jar hashes are in `environment.json`. Only `code/procedure/preprocess_project.py` was changed under author code/data; `patch.diff` records it. Existing `.gitignore` edits and the staged deletion of `docs/handover-stage0-commit-survival.md` belong to the user and are excluded from this commit.', '',
              '## Baseline and snapshot comparison', '',
              f'`run1-baseline.txt` reproduces `networkx.exception.NetworkXNoPath` inside `_get_lines_from_cfg`; index construction took {baseline["seconds"]} seconds. The driver catches that exception so it can retain the other comparisons. See `run1-baseline.json`.', '',
              '| Run | Identical | Only author | Only generated | Differs | Unreachable targets | Index seconds |',
              '|---|---:|---:|---:|---:|---:|---:|']
    for run_name, comp_name in [('run2-patched', 'invoke-compare'), ('run3-with-junit', 'invoke-compare-junit')]:
        r, c = runs[run_name], comparisons[comp_name]['counts']
        report.append(f'| {run_name} | {c["identical"]} | {c["only in author"]} | {c["only in generated"]} | {c["differs"]} | {r["unreachable_targets"]} | {r["seconds"]} |')
    report += ['', 'Counts and full per-key differing line ranges are in `invoke-compare.json` and `invoke-compare-junit.json`; their Markdown companions list every non-identical key. Only `file_path` separators are normalized in these comparisons. Order and line ranges remain significant.', '',
               '`UNREACHABLE_TARGETS` counts caught target occurrences across extraction calls, not distinct CFG nodes. Index seconds are monotonic wall time through IndexBuilder, excluding the subsequent author comparison and JVM shutdown.', '',
               '| Raw JSON comparison | Patched differing paths | With JUnit differing paths |', '|---|---:|---:|']
    for name in runs['run2-patched']['json']:
        report.append(f'| {name} | {runs["run2-patched"]["json"][name]["differing_paths_count"]} | {runs["run3-with-junit"]["json"][name]["differing_paths_count"]} |')
    r = runs['run2-patched']['lucene']
    report += ['', 'Raw comparisons retain the survey’s CRLF-to-LF normalization only, so invoke path-separator differences remain in the raw counts. Full differing JSON paths are in the corresponding run JSON files.', '',
               f'Lucene: author {r["authors"]["document_count"]}, patched {r["generated"]["document_count"]}, JUnit {runs["run3-with-junit"]["lucene"]["generated"]["document_count"]} documents; identifier sets match in both patched runs. This checks counts and identifiers, not all stored contents or ranking.', '',
               f'JUnit Assert warning occurrences fell from {warning_reports["run2-patched"]["junit_assert_warnings"]} to {warning_reports["run3-with-junit"]["junit_assert_warnings"]}. The generated invoke files are byte-identical before/after adding JUnit, and the non-identical key count did not change. One Java `ResolvedWildcard` diagnostic remains in the JUnit run. Evidence: `warnings.json`, `verification.json`, `run3-with-junit.txt`, and dependency hashes in `download-junit.txt`.', '',
               '## Fresh-commit end-to-end result', '',
               f'Fresh upstream checkout: `{read("fresh-prepare.json")["commit"]}`. Dependency resolution succeeded on its first Maven attempt; `fresh-prepare.json` records command, duration, dependency classpath, and jar hashes. No author snapshot/index files were copied into the fresh root.', '',
               '| Metric | Count |', '|---|---:|']
    for part, counts in fresh['json'].items():
        for metric, count in counts.items():
            report.append(f'| JSON {part} {metric} | {count} |')
    report += [f'| JSON total classes | {fresh["json_total_classes"]} |',
               f'| JSON total methods (constructors excluded) | {fresh["json_total_methods"]} |',
               f'| Invoke class entries | {fresh["invoke_classes"]} |',
               f'| Invoke method/constructor keys | {fresh["invoke_method_keys"]} |',
               f'| Invoke examples | {fresh["invoke_examples"]} |',
               f'| Lucene documents | {fresh["lucene"]["document_count"]} |',
               f'| Unreachable target occurrences | {fresh["unreachable_targets"]} |',
               f'| Index seconds | {fresh["seconds"]} |', '',
               'Evidence: `run4-fresh.json` and `run4-fresh.txt`. Classes are JSON class entries, including the extractor’s nested classes; methods count method overload records. Invoke method keys include constructors.', '',
               f'DatasetPrepare extracted all {dataset["generated_method_count"]} declared non-private methods of `org.apache.commons.cli.Options`, with no missing/unexpected methods or empty code fields. There are only {dataset["unique_id_count"]} unique IDs: `Options_addOption_2` is shared by the three string-based overloads. This is a released DatasetPrepare ID bug, preserved without repair. Extraction succeeded on the first attempt; the complete dataset is not safe for ID-keyed batch generation. Evidence: `{dataset_files[-1].name}`, `fresh-dataset-meta.json`, and `fresh-dataset-info-attempt-1.json`.', '',
               '| Prompt method | Invoke examples present | Examples |', '|---|---|---:|']
    for method in prompts['methods']:
        report.append(f'| `{method["method"]}` | {method["invoke_examples_present"]} | {method["invoke_example_count"]} |')
    report += ['', f'The selected methods have distinct IDs. All {prompts["tasks"] * prompts["prompts_per_task"]} prompt files are nonempty; construct/usage contexts and prompts are under `prompts/`. `prompt-check.json` records the selection and model-call count. Assembly was checked; no generated tests were compiled or executed.', '',
               '## Remaining limitations and checks', '',
               f'The fresh Java extractor emitted {warning_reports["run4-fresh"]["java_error_lines"]} nonfatal `Error:` lines, including unresolved symbols, ambiguous calls, unsupported `ConditionalExpr`, and missing node data. Its process still completed and produced all index families. This demonstrates operational rebuildability, not complete or semantically equivalent extraction. Exact diagnostic counts/messages are in `warnings.json`.', '',
               'The author-index differences and DatasetPrepare ID collision remain unresolved because the instructions permit no other author-code changes. No specified stop condition was triggered after the expected baseline failure.', '',
               'The focused mixed-reachability check confirms that both equal-length reachable paths and an unreachable target’s own line are retained, with one counter increment. The no-BEGIN behavior and Python syntax checks also passed. Evidence: `verification.json`.', '',
               'Source whitespace checks pass. A full artifact whitespace check flags trailing whitespace emitted by the original prompt templates and DatasetPrepare stdout, plus whitespace-only context lines in `patch.diff`; those raw evidence files are preserved unchanged.', '',
               '## Files and reproduction', '',
               'The `docker/extractor_check_*.py` helpers are committed with the reports and patch. `file-inventory.json` lists experiment-created outputs, including the ignored scratch trees; local handover is `docs/handover-extractor-check.md`. Prior survey reports and `scratch/extract-check/` were not written by these scripts.', '',
               'From `docker/`, prefix each command below with `docker compose run --rm ktester bash -c`, running the quoted body from `/work`. These commands document the completed sequence; fresh preparation requires a new empty destination. The baseline must run before the patch.', '',
               '```sh',
               'cd /work && python3 docker/extractor_check_prepare.py',
               'cd /work && python3 docker/extractor_check_build_index.py --dataset /work/scratch/extractor-check/cli-snapshot --output /work/scratch/extractor-check/cli-snapshot/project_index --report /work/results/extractor-check/run2-patched.json',
               '# Add the Maven Central JUnit/Hamcrest jars to scratch libs, as recorded in instructions.md and download-junit.txt.',
               'cd /work && python3 docker/extractor_check_build_index.py --dataset /work/scratch/extractor-check/cli-snapshot --output /work/scratch/extractor-check/cli-snapshot/project_index-junit --report /work/results/extractor-check/run3-with-junit.json',
               'cd /work && python3 docker/extractor_check_compare.py --generated /work/scratch/extractor-check/cli-snapshot/project_index/codegraph/commons-cli_invoke.json',
               'cd /work && python3 docker/extractor_check_compare.py --generated /work/scratch/extractor-check/cli-snapshot/project_index-junit/codegraph/commons-cli_invoke.json --name invoke-compare-junit',
               'cd /work && python3 docker/extractor_check_fresh_prepare.py',
               'cd /work && python3 docker/extractor_check_fresh_index.py',
               'cd /work && python3 docker/extractor_check_dataset_prepare.py --attempt 1',
               'cd /work && python3 docker/extractor_check_prompt_check.py',
               'cd /work && python3 docker/extractor_check_report.py',
               '```']
    (OUT / 'README.md').write_text('\n'.join(report) + '\n')
    handover = ['# Extractor-check handover', '', 'Last commit: pending experiment commit; updated after commit.', '', *report[2:], '',
                '## Patch diff', '', '```diff', (OUT / 'patch.diff').read_text().rstrip(), '```']
    (ROOT / 'docs/handover-extractor-check.md').write_text('\n'.join(handover) + '\n')
    inventory = {'committed': ['code/procedure/preprocess_project.py'] + [str(p.relative_to(ROOT)) for p in sorted(ROOT.glob('docker/extractor_check_*.py'))]
                 + [str(p.relative_to(ROOT)) for p in sorted(OUT.rglob('*')) if p.is_file()],
                 'local_only': ['docs/handover-extractor-check.md'] +
                 [str(p.relative_to(ROOT)) for p in sorted((ROOT / 'scratch/extractor-check').rglob('*')) if p.is_file() or p.is_symlink()],
                 'note': 'Lists new experiment output trees, including clone internals and dependency/source copies. Does not include unrelated pre-existing user changes, temporary Docker container files, or Maven cache entries.'}
    inventory['committed'] = sorted(set(inventory['committed'] + ['results/extractor-check/file-inventory.json']))
    (OUT / 'file-inventory.json').write_text(json.dumps(inventory, indent=2) + '\n')
    print(json.dumps({'verification': 'passed', 'report': 'results/extractor-check/README.md',
                      'handover': 'docs/handover-extractor-check.md'}))


if __name__ == '__main__':
    main()
