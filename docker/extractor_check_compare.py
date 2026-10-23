#!/usr/bin/env python3
"""Compare every invoke-method key, normalizing only file-path separators."""
import argparse
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]


def normalized(value):
    if isinstance(value, list):
        return [normalized(item) for item in value]
    if isinstance(value, dict):
        return {key: item.replace('\\', '/') if key == 'file_path' else normalized(item)
                for key, item in value.items()}
    return value


def flatten(path):
    data = json.loads(path.read_text())
    return {cls + '#' + method: normalized(patterns)
            for cls, methods in data.items() for method, patterns in methods.items()}


def line_range_differences(a, b):
    differences = []
    for i in range(max(len(a), len(b))):
        x = a[i] if i < len(a) else []
        y = b[i] if i < len(b) else []
        for j in range(max(len(x), len(y))):
            old = x[j] if j < len(x) else None
            new = y[j] if j < len(y) else None
            if old != new:
                differences.append({'example_index': i, 'snippet_index': j,
                                    'author': old, 'generated': new})
    return differences


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--generated', type=Path, required=True)
    ap.add_argument('--name', default='invoke-compare')
    args = ap.parse_args()
    author = ROOT / 'data/project_index/codegraph/commons-cli_invoke.json'
    a, b = flatten(author), flatten(args.generated)
    entries = {}
    counts = dict.fromkeys(['identical', 'only in author', 'only in generated', 'differs'], 0)
    for key in sorted(a.keys() | b.keys()):
        category = ('only in generated' if key not in a else 'only in author' if key not in b
                    else 'identical' if a[key] == b[key] else 'differs')
        counts[category] += 1
        entry = {'category': category}
        if category != 'identical':
            entry.update(author=a.get(key), generated=b.get(key))
            entry['differing_line_ranges'] = line_range_differences(a.get(key, []), b.get(key, []))
            entry['differing_examples'] = [
                {'index': i, 'author': x, 'generated': y}
                for i in range(max(len(a.get(key, [])), len(b.get(key, []))))
                for x, y in [(a.get(key, [])[i] if i < len(a.get(key, [])) else None,
                              b.get(key, [])[i] if i < len(b.get(key, [])) else None)]
                if x != y]
        entries[key] = entry
    result = {'author': str(author.relative_to(ROOT)),
              'generated': str(args.generated.relative_to(ROOT)),
              'normalization': 'Only file_path: replace backslashes with forward slashes; preserve example ordering and line ranges.',
              'author_method_keys': len(a), 'generated_method_keys': len(b),
              'counts': counts, 'non_identical_keys': sum(v for k, v in counts.items() if k != 'identical'),
              'entries': entries}
    out = ROOT / 'results/extractor-check'
    (out / (args.name + '.json')).write_text(json.dumps(result, indent=2) + '\n')
    lines = ['# Invoke-pattern comparison', '', result['normalization'], '',
             f'Evidence: `{args.name}.json`. Example positions are zero-based; full author/generated line ranges are retained there.', '',
             '| Category | Method keys |', '|---|---:|']
    lines += [f'| {k} | {v} |' for k, v in counts.items()]
    lines += ['', '## All non-identical keys', '']
    lines += [f'- `{key}`: {entry["category"]}.' for key, entry in entries.items() if entry['category'] != 'identical']
    if not result['non_identical_keys']:
        lines += ['None.']
    (out / (args.name + '.md')).write_text('\n'.join(lines) + '\n')
    print(json.dumps({k: v for k, v in result.items() if k != 'entries'}, indent=2))


if __name__ == '__main__':
    main()
