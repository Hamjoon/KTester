#!/usr/bin/env python3
"""Clean only authorized output files and create the released pipeline's directories."""
import argparse
import hashlib
import json
from pathlib import Path

KT = Path(__file__).resolve().parents[1]


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--round', type=int, choices=[2, 3], required=True)
    parser.add_argument('--repo', type=Path, required=True)
    args = parser.parse_args()
    previous = KT / f'results/commit-survival-round{args.round - 1}/commons-codec/run.json'
    assert previous.exists(), 'Previous generation must be finished and archived'
    funnel = json.loads((args.repo / 'experiments/ktester-commit-survival/results/llm-funnel.json').read_text())
    assert len(funnel['rounds']) == args.round - 1
    assert funnel['llm_denominator'] < 143 and not funnel['frozen'], 'Another round is not permitted'
    assert not (KT / f'results/commit-survival-round{args.round}').exists(), 'Round already attempted'
    dataset = json.loads((KT / 'data/commit-survival/commons-codec/dataset_info.json').read_text())
    tasks = dataset['commons-codec']['focal-methods']
    assert len(tasks) == len({t['id'] for t in tasks}) == 52
    root = KT / 'evaluation/commons-codec'
    files = sorted(p for name in ['context+prompts', 'responses', 'fix', 'test_classes', 'reports'] for p in (root / name).rglob('*') if p.is_file())
    files += sorted((KT / 'data/projects/commons-codec/src/test').rglob('*_Test.java'))
    removed = [{'file': str(p.relative_to(KT)), 'bytes': p.stat().st_size,
                'sha256': hashlib.sha256(p.read_bytes()).hexdigest()} for p in files]
    for p in files:
        p.unlink()
    # These are the paths created by preparation.set_file_structure(), without -W or reset.
    needed = [root / 'test_classes/temp', root / 'reports/jacoco-report-csv']
    for task in tasks:
        needed += [root / name / task['id'] for name in ['context+prompts', 'responses', 'fix']]
        needed.append(root / 'reports/jacoco-report-html' / task['id'])
    created = []
    for p in needed:
        if not p.exists():
            created.append(str(p.relative_to(KT)))
        p.mkdir(parents=True, exist_ok=True)
    result = {'round': args.round, 'removed_files': removed, 'directories_removed': 0,
              'created_directories': created, 'task_directory_sets_verified': len(tasks),
              'settings_changed': False, 'model_calls': 0,
              'reason': 'All per-task output directories must exist before generation; replicate directory creation only from preparation.set_file_structure without workspace reset'}
    (KT / f'results/commit-survival/round{args.round}-workspace.json').write_text(json.dumps(result, indent=2) + '\n')
    print(json.dumps({'round': args.round, 'removed_files': len(files), 'directories_removed': 0,
                      'created_directories': len(created), 'verified_tasks': len(tasks)}))


if __name__ == '__main__':
    main()
