#!/usr/bin/env python3
"""Resume the fixed 66-method population with the unmodified Stage 0 JVM jars.

Keep invalid raw output as evidence; never repair IDs or alter Java/source inputs.
The earlier part1_codec_population.py and its enumeration JSON are historical.
"""
import collections
import csv
import hashlib
import json
import os
from pathlib import Path
import shutil
import sys
import time

sys.dont_write_bytecode = True
KT = Path(__file__).resolve().parents[1]
OUT = KT / 'data/commit-survival/commons-codec'
PUBLIC = Path('/repo/experiments/ktester-commit-survival/population')
COMMAND = "docker compose run --rm -v /Users/donggi/_projects/experiment-projects/commons-codec:/repo ktester bash -c 'python /work/docker/part1_codec_dataset_prepare.py'"


def digest(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()


def normalized(value):
    if isinstance(value, str):
        return value.replace('\r\n', '\n')
    if isinstance(value, dict):
        return {k: normalized(v) for k, v in value.items()}
    if isinstance(value, list):
        return [normalized(v) for v in value]
    return value


def signature(entry):
    return entry['class'] + '.' + entry['method-name']


def render(result, enumeration):
    lines = ['# Focal-method population: 66 non-private methods', '',
             'The second addendum fixes membership at the existing candidates.csv. The two private original focal methods are explicitly excluded. No visibility changes or wrappers were used.', '',
             'Status: **' + result['status'] + '**. The raw DatasetPrepare output is diagnostic evidence and is not ready for test generation.', '',
             'Counts and checks below come from population-validation.json, produced from KTester/docker with:', '',
             '```sh', COMMAND, '```', '',
             'Source: the authors’ data/projects/commons-codec snapshot through the local commons-codec symlink. The bundled jars and Stage 0 JVM settings are unchanged. Metadata lists the five included original focal methods first (authors’ order), then the remaining candidates in CSV order, preserving original base IDs for the field comparison. No output IDs were rewritten.', '',
             'The earlier population-enumeration.json, enumeration-run.txt and docker/part1_codec_population.py preserve the first enumeration and its then-required stop. Their private-method inclusion conflict is superseded by addendum 2; do not rerun the historical driver to resume generation.', '',
             '| Class | Methods |', '|---|---:|']
    for cls, count in result['per_class'].items():
        lines.append(f'| {cls} | {count} |')
    lines += ['', f"Total: {result['expected_count']}; within 60–100: {result['within_60_to_100']}.", '',
              '## Dataset focal methods in scope', '',
              '| Original ID | Class and method | Visibility at t | Included |', '|---|---|---|---|']
    for entry in enumeration['original_methods']:
        lines.append(f"| {entry['id']} | {signature(entry)} | {entry['declaration_at_t']['visibility']} | {'yes' if entry['included'] else 'no — private'} |")
    lines += ['', '## Overload groups', '',
              f"{result['overload_group_count']} groups contain {result['methods_in_overload_groups']} methods. Groups use the same class and name with different parameter lists.", '']
    for group, methods in result['overload_groups'].items():
        lines.append('- `' + group + '`: ' + '; '.join('`' + m + '`' for m in methods))
    lines += ['', '## DatasetPrepare validation', '',
              f"Generated entries: {result['generated_count']}. Distinct IDs: {result['unique_id_count']}. Colliding IDs: {len(result['id_collisions'])}; affected methods: {result['collision_affected_methods']}. Missing methods: {len(result['missing_methods'])}; unexpected methods: {len(result['unexpected_methods'])}; empty fields: {len(result['empty_fields'])}.", '',
              '| Colliding ID | Affected methods |', '|---|---|']
    for ident, methods in result['id_collisions'].items():
        lines.append('| ' + ident + ' | ' + '<br>'.join(methods) + ' |')
    for field in ['missing_methods', 'unexpected_methods', 'empty_fields', 'duplicate_method_entries']:
        if result[field]:
            lines += ['', field + ':', '', '```json', json.dumps(result[field], indent=2), '```']
    lines += ['', '## Original-entry field comparison', '',
              'Every field is compared, permitting CRLF/LF differences only. Full per-field evidence is in population-validation.json.', '',
              '| Original ID | Equal up to line endings | Differing fields |', '|---|---|---|']
    for entry in result['original_entry_comparisons']:
        lines.append(f"| {entry['id']} | {entry['equal_up_to_line_endings']} | {', '.join(k for k, v in entry['fields'].items() if v == 'content difference')} |")
    lines += ['', 'The protected-input SHA-256 manifest matches before and after extraction. This includes the authors’ dataset, snapshot sources/libs, packaged extractor jars, DatasetPrepare.java and settings.py. No model call or index rebuild occurred.', '',
              'Raw dataset_meta.json and dataset_info.json are retained in this directory and copied byte-for-byte to the public experiment population directory. The local source symlink is ignored, and no jars are copied.', '',
              '## Stop', '',
              'The addendum explicitly requires stopping on an ID collision or missing/empty entry. Step 5 was not entered. The unmodified DatasetPrepare result has not been renumbered, split into smaller runs, or patched.', '',
              'TODO (Gary): Decide how to address the recorded DatasetPrepare validation failures while retaining the fixed 66-method population; Java changes and output ID repairs are not authorized by this addendum.']
    (OUT / 'README.md').write_text('\n'.join(lines) + '\n')


def main():
    start = time.monotonic()
    rows = list(csv.DictReader((OUT / 'candidates.csv').open()))
    assert (OUT / 'candidates.csv').read_bytes() == (PUBLIC / 'candidates.csv').read_bytes()
    enumeration = json.loads((OUT / 'population-enumeration.json').read_text())
    methods = [r['class'] + '.' + r['method'] + '(' + r['parameter-types'] + ')' for r in rows]
    assert len(methods) == len(set(methods)) == 66
    assert all(r['visibility'] != 'private' for r in rows)
    counts = dict(collections.Counter(r['class'] for r in rows))
    assert counts == enumeration['per_class']
    author_path = KT / 'data/dataset_info.json'
    original = json.loads(author_path.read_text())['commons-codec']
    scoped = [m for m in original['focal-methods'] if m['class'] in counts]
    included = [m for m in scoped if signature(m) in methods]
    excluded = [m for m in scoped if signature(m) not in methods]
    assert {m['id'] for m in excluded} == {'MurmurHash3_hash128x64Internal', 'Sha2Crypt_sha2Crypt'}
    assert len(included) == 5
    ordered = [signature(m) for m in included]
    ordered += [m for m in methods if m not in ordered]
    meta = [{'project_name': 'commons-codec', 'put_path': '/root/experiments/puts/commons-codec',
             'method_name_to_idx': {m: i for i, m in enumerate(ordered)}}]
    output = OUT / 'dataset_info.json'
    if output.exists():
        raise SystemExit('Refusing to overwrite an earlier DatasetPrepare result; review the recorded evidence.')
    (OUT / 'dataset_meta.json').write_text(json.dumps(meta, indent=2) + '\n')
    link = OUT / 'commons-codec'
    snapshot = KT / 'data/projects/commons-codec'
    if not link.is_symlink():
        link.symlink_to(os.path.relpath(snapshot, OUT), target_is_directory=True)
    assert link.resolve() == snapshot.resolve()
    (OUT / '.gitignore').write_text('/commons-codec\n')
    protected = [author_path, KT / 'code/settings.py', KT / 'code/Java/project-info-process.jar',
                 KT / 'code/Java/project-index-builder.jar', KT / 'code/Java/project-info-process/src/DatasetPrepare.java']
    protected += sorted(p for root in [snapshot / 'src/main', snapshot / 'libs'] for p in root.rglob('*') if p.is_file())
    before = {str(p.relative_to(KT)): digest(p) for p in protected}
    from stage0_build_index import start_jvm
    import jpype
    start_jvm()
    jpype.JClass('DatasetPrepare').main([str(OUT)])
    jpype.shutdownJVM()
    after = {str(p.relative_to(KT)): digest(p) for p in protected}
    assert before == after, 'Protected input changed'
    generated = json.loads(output.read_text())['commons-codec']
    entries = generated['focal-methods']
    by_method = collections.defaultdict(list)
    by_id = collections.defaultdict(list)
    for entry in entries:
        by_method[signature(entry)].append(entry)
        by_id[entry['id']].append(signature(entry))
    collisions = {k: v for k, v in by_id.items() if len(v) > 1}
    comparisons = []
    for old in included:
        matches = by_method[signature(old)]
        new = matches[0] if len(matches) == 1 else {}
        fields = {k: 'identical' if old.get(k) == new.get(k) else
                  'line-endings only' if normalized(old.get(k)) == normalized(new.get(k)) else
                  'content difference' for k in sorted(old.keys() | new.keys())}
        comparisons.append({'id': old['id'], 'method': signature(old), 'fields': fields,
                            'equal_up_to_line_endings': len(matches) == 1 and normalized(old) == normalized(new)})
    result = {'invocation': COMMAND, 'source': 'authors data/projects/commons-codec via local commons-codec symlink',
              'metadata_order': 'included original focal methods in authors order, then remaining candidates in CSV order',
              'expected_count': len(methods), 'within_60_to_100': 60 <= len(methods) <= 100,
              'per_class': counts, 'excluded_private_original_methods': [signature(m) for m in excluded],
              'overload_groups': enumeration['overload_groups'], 'overload_group_count': enumeration['overload_group_count'],
              'methods_in_overload_groups': enumeration['methods_in_overload_groups'],
              'dataset_prepare_run': True, 'generated_count': len(entries), 'unique_id_count': len(by_id),
              'id_collisions': collisions, 'collision_affected_methods': sum(map(len, collisions.values())),
              'missing_methods': sorted(set(methods) - by_method.keys()),
              'unexpected_methods': sorted(by_method.keys() - set(methods)),
              'duplicate_method_entries': {k: len(v) for k, v in by_method.items() if len(v) != 1},
              'empty_fields': [{'method': signature(m), 'field': f} for m in entries for f in ['id', 'focal-method', 'class-code'] if not m.get(f, '').strip()],
              'original_entry_comparisons': comparisons,
              'project_fields_equal': {k: original[k] == generated.get(k) for k in original if k != 'focal-methods'},
              'protected_input_sha256': before, 'protected_inputs_unchanged': before == after,
              'output_sha256': {name: digest(OUT / name) for name in ['candidates.csv', 'dataset_meta.json', 'dataset_info.json']},
              'seconds': round(time.monotonic() - start, 3)}
    result['valid'] = not any(result[k] for k in ['id_collisions', 'missing_methods', 'unexpected_methods', 'duplicate_method_entries', 'empty_fields']) and all(c['equal_up_to_line_endings'] for c in comparisons) and all(result['project_fields_equal'].values())
    result['status'] = 'population_validated' if result['valid'] else 'blocked_dataset_prepare_validation'
    (OUT / 'population-validation.json').write_text(json.dumps(result, indent=2) + '\n')
    render(result, enumeration)
    for name in ['candidates.csv', 'dataset_meta.json', 'dataset_info.json', 'population-validation.json', 'README.md']:
        shutil.copyfile(OUT / name, PUBLIC / name)
    print(json.dumps({k: result[k] for k in ['status', 'expected_count', 'generated_count', 'unique_id_count', 'id_collisions', 'missing_methods', 'empty_fields', 'original_entry_comparisons', 'seconds']}, indent=2))
    if not result['valid']:
        raise SystemExit('STOP: DatasetPrepare validation failed; preserve raw output and do not patch Java or repair IDs.')


if __name__ == '__main__':
    main()
