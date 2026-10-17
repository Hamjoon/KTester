"""Verify the deprecated exclusions and extract the authorized 52-method set."""
import collections
import csv
import hashlib
import json
from pathlib import Path
import shutil
import subprocess
import sys

sys.dont_write_bytecode = True
KT = Path('/work')
EXP = Path('/repo/experiments/ktester-commit-survival')
OUT = KT / 'data/commit-survival/commons-codec'
PUBLIC = EXP / 'population'
sys.path.insert(0, str(EXP / 'tools'))
from renumber_dataset_ids import renumber, validate, RULE
from stage0_build_index import start_jvm, normalize
import jpype


def write_json(path, value):
    path.write_text(json.dumps(value, indent=2, ensure_ascii=False) + '\n')


def write_csv(path, fields, rows):
    with path.open('w', newline='') as stream:
        writer = csv.DictWriter(stream, fieldnames=fields, lineterminator='\n')
        writer.writeheader()
        writer.writerows(rows)


def sha(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()


def main():
    assert not (OUT / 'population-66').exists(), 'Population already archived; inspect before rerunning'
    old = json.loads((OUT / 'dataset_info.json').read_text())
    tasks = old['commons-codec']['focal-methods']
    assert len(tasks) == 66
    index = json.loads((KT / 'data/project_index/json/commons-codec.json').read_text())['source']
    missing = {x['id'] for x in json.loads((KT / 'results/commit-survival/index-compatibility.json').read_text())['missing']}
    snapshot = KT / 'data/projects/commons-codec'
    sources = {}
    for cls in sorted({t['class'] for t in tasks}):
        rel = 'src/main/java/' + cls.replace('.', '/') + '.java'
        raw = (snapshot / rel).read_text()
        upstream = subprocess.check_output(['git', '-C', '/repo', 'show', 'cd16988:' + rel]).decode()
        assert (EXP / 'work/t' / rel).read_text() == upstream
        sources[cls] = {'file': rel, 'sha256': sha(snapshot / rel),
                        'whole_file_equals_upstream': normalize(raw) == normalize(upstream)}
    protected = [KT / 'code/settings.py', KT / 'data/dataset_info.json',
                 KT / 'code/Java/project-info-process.jar', KT / 'code/Java/project-index-builder.jar']
    protected += [p for root in [snapshot / 'src/main', snapshot / 'libs', KT / 'data/project_index'] for p in root.rglob('*') if p.is_file()]
    before = {str(p.relative_to(KT)): sha(p) for p in protected}
    start_jvm()
    try:
        extractor = jpype.JClass('extractor.SpoonExtractor')([str(snapshot / 'src/main/java')], [str(snapshot / 'libs')])
        upstream_extractor = jpype.JClass('extractor.SpoonExtractor')([str(EXP / 'work/t/src/main/java')], [str(snapshot / 'libs')])
        declarations = {}
        upstream_declarations = {}
        for cls in sources:
            for method in upstream_extractor.getCtClass(cls).getMethods():
                sig = str(method.getSimpleName()) + '(' + ', '.join(str(p.getType().getSimpleName()) for p in method.getParameters()) + ')'
                deprecated = any(str(a.getAnnotationType().getQualifiedName()) == 'java.lang.Deprecated' for a in method.getAnnotations())
                upstream_extractor.removeComment(method)
                upstream_declarations[(cls, sig)] = (deprecated, str(method.prettyprint()))
        for cls in sources:
            for method in extractor.getCtClass(cls).getMethods():
                sig = str(method.getSimpleName()) + '(' + ', '.join(str(p.getType().getSimpleName()) for p in method.getParameters()) + ')'
                declarations[(cls, sig)] = {
                    'deprecated': any(str(a.getAnnotationType().getQualifiedName()) == 'java.lang.Deprecated' for a in method.getAnnotations()),
                    'line': int(method.getPosition().getLine()),
                    'eligible': not method.isPrivate() and method.getBody() is not None}
                extractor.removeComment(method)
                assert (declarations[(cls, sig)]['deprecated'], str(method.prettyprint())) == upstream_declarations[(cls, sig)], (cls, sig)
            sources[cls]['parsed_methods_equal_upstream_without_comments'] = True
        audit = []
        for task in tasks:
            cls, sig = task['class'], task['method-name']
            decl = declarations[(cls, sig)]
            present = any(m['signature'].endswith(sig) for name, methods in index[cls]['methods'].items() if name in sig for m in methods)
            audit.append({'id': task['id'], 'class': cls, 'signature': sig,
                          'deprecated': 'yes' if decl['deprecated'] else 'no',
                          'present-in-index': 'yes' if present else 'no', 'line': decl['line']})
            assert decl['eligible']
            assert decl['deprecated'] == (task['id'] in missing) == (not present), task['id']
        exclusions = [x for x in audit if x['deprecated'] == 'yes']
        assert len(exclusions) == 14
        for folder in [OUT, PUBLIC]:
            archive = folder / 'population-66'
            archive.mkdir()
            for path in list(folder.iterdir()):
                if path.is_file() and path.name != '.gitignore':
                    shutil.copyfile(path, archive / path.name)
        write_csv(OUT / 'deprecated-exclusions.csv', list(audit[0]), exclusions)
        write_csv(OUT / 'deprecation-audit.csv', list(audit[0]), audit)
        rows = list(csv.DictReader((OUT / 'population-66/candidates.csv').open()))
        rows = [{**r, 'deprecated': 'no'} for r in rows if not declarations[(r['class'], r['method'] + '(' + r['parameter-types'] + ')')]['deprecated']]
        assert len(rows) == 52
        write_csv(OUT / 'candidates.csv', list(rows[0]), rows)
        retained = [t for t in tasks if t['id'] not in missing]
        meta = json.loads((OUT / 'population-66/dataset_meta.json').read_text())
        meta[0]['method_name_to_idx'] = {t['class'] + '.' + t['method-name']: i for i, t in enumerate(retained)}
        write_json(OUT / 'dataset_meta.json', meta)
        jpype.JClass('DatasetPrepare').main([str(OUT)])
        shutil.copyfile(OUT / 'dataset_info.json', OUT / 'dataset_info.raw.json')
    finally:
        jpype.shutdownJVM()
    raw = json.loads((OUT / 'dataset_info.raw.json').read_text())
    result = renumber(raw)
    validation = validate(raw, result, expected_count=52)
    entries = result['commons-codec']['focal-methods']
    assert {t['class'] + '.' + t['method-name'] for t in entries} == set(meta[0]['method_name_to_idx'])
    author = json.loads((KT / 'data/dataset_info.json').read_text())['commons-codec']['focal-methods']
    comparisons = []
    for entry in entries:
        original = next((t for t in author if (t['class'], t['method-name']) == (entry['class'], entry['method-name'])), None)
        if original:
            assert normalize(original) == normalize(entry), entry['id']
            comparisons.append({'id': entry['id'], 'equal_up_to_line_endings': True})
    assert len(comparisons) == 5
    assert before == {str(p.relative_to(KT)): sha(p) for p in protected}
    write_json(OUT / 'dataset_info.json', result)
    mapping = [{'old-id': a['id'], 'new-id': b['id'], 'class': a['class'], 'method-signature': a['method-name']}
               for a, b in zip(raw['commons-codec']['focal-methods'], entries)]
    write_csv(OUT / 'id-renumbering.csv', list(mapping[0]), mapping)
    write_json(OUT / 'id-renumbering-validation.json', validation)
    counts = dict(sorted(collections.Counter(t['class'] for t in entries).items()))
    report = {'status': 'validated', 'entries': 52, 'unique_ids': 52, 'per_class': counts,
              'excluded_deprecated': 14, 'index_matched': 52, 'source_checks': sources,
              'original_entry_comparisons': comparisons, 'protected_inputs_unchanged': True,
              'protected_input_sha256': before, 'output_sha256': {'dataset_info.json': sha(OUT / 'dataset_info.raw.json')},
              'renumbering': validation, 'model_calls': 0}
    write_json(OUT / 'population-validation.json', report)
    lines = ['# Population: 52 non-deprecated methods', '',
             'Rule: non-private, non-deprecated methods with a body, excluding constructors, in the five fixed classes. The authors’ index is unchanged. Developer denominator: 143; commit range: 539.', '',
             'The previous 66-method artifacts are preserved in population-66/. Historical enumeration files describe that previous population. Current evidence is population-validation.json, deprecation-audit.csv and deprecated-exclusions.csv.', '',
             '| Class | Methods |', '|---|---:|']
    lines += [f'| {cls} | {count} |' for cls, count in counts.items()]
    lines += ['', 'All 14 excluded methods carry @Deprecated in both the snapshot and upstream cd16988. Parsed method declarations match after comment removal. Whole Base32/Base64 files have comment differences, so source equivalence is verified at the method level. None of the retained 52 methods is deprecated; all match the existing JSON index. Annotation and index checks cover the full prior 66-method population.', '', 'Excluded signatures:', '']
    lines += ['- `' + r['class'] + '.' + r['signature'] + '`' for r in exclusions]
    lines += ['', '## Extraction and renumbering', '',
              'Run `python /work/docker/part2_reduce_population.py` in the prescribed container. The unmodified DatasetPrepare jars read the authors’ snapshot through the existing symlink. The five original included focal entries match every author field up to line endings.', '', RULE, '',
              f"Measured: 52 entries and unique IDs; {validation['raw_unique_ids']} raw unique IDs; {validation['changed_ids']} changed IDs; {validation['unchanged_ids']} unchanged IDs. All previously unique entries and every non-naming field are preserved. Renumbering uses tools/renumber_dataset_ids.py; only its validation count is parameterized. All code fields are nonempty and naming paths are consistent.", '',
              'Settings, source, jars, author dataset and index hashes are unchanged across extraction. No model calls were made.']
    (OUT / 'README.md').write_text('\n'.join(lines) + '\n')
    for name in ['dataset_meta.json', 'dataset_info.raw.json', 'dataset_info.json', 'id-renumbering.csv', 'id-renumbering-validation.json', 'population-validation.json', 'candidates.csv', 'deprecated-exclusions.csv', 'deprecation-audit.csv', 'README.md']:
        shutil.copyfile(OUT / name, PUBLIC / name)
    print(json.dumps({'entries': 52, 'per_class': counts, 'excluded': 14, 'renumbering': validation}, indent=2))


if __name__ == '__main__':
    main()
