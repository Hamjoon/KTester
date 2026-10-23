#!/usr/bin/env python3
"""Adapt commit_survival_prompt_check to three fresh Options methods; no model calls."""
import json
import os
from pathlib import Path
import re
import sys

sys.dont_write_bytecode = True
ROOT = Path(__file__).resolve().parents[1]
DS = ROOT / 'scratch/extractor-check/cli-fresh'
OUT = ROOT / 'results/extractor-check'
SELECTED = ['getOption(String)', 'hasOption(String)', 'getRequiredOptions()']


def main():
    os.chdir(ROOT / 'code')
    sys.path.insert(0, str(ROOT / 'code'))
    import jpype
    from settings import FileStructure as FS, TaskSettings as TS
    from procedure import generate_prompt
    from stage0_build_index import start_jvm

    FS.DATASET_PATH = str(DS)
    FS.CODE_INFO_PATH = str(DS / 'project_index')
    FS.PROMPT_PATH = str(OUT / 'prompts/<project>')
    TS.PROJECTS = ['commons-cli']
    if TS.CASE_THEN_CODE:
        TS.PROMPT_LIST = [p if p.endswith('4case') or p == 'gencode' else p + '4case'
                          for p in TS.PROMPT_LIST]
        if 'gencode' not in TS.PROMPT_LIST:
            TS.PROMPT_LIST.append('gencode')
    dataset = json.loads((DS / 'dataset_info.json').read_text())
    project = dataset['commons-cli']
    tasks = [next(m for m in project['focal-methods'] if m['method-name'] == name) for name in SELECTED]
    assert all(m['class'] == 'org.apache.commons.cli.Options' for m in tasks)
    assert len({m['id'] for m in tasks}) == len(tasks)
    project['focal-methods'] = tasks
    assert (DS / project['project-url']).resolve() == (DS / 'projects/commons-cli').resolve()
    invoke = json.loads((DS / 'project_index/codegraph/commons-cli_invoke.json').read_text())
    start_jvm()
    try:
        generate_prompt.generate_init_prompts(FS, TS, dataset)
        generate_prompt.generate_test_case_prompts(FS, TS, dataset)
        reports = []
        for task in tasks:
            folder = Path(FS.PROMPT_PATH.replace('<project>', 'commons-cli'), task['id'])
            for prompt in ['init'] + TS.PROMPT_LIST:
                assert (folder / (prompt + '_prompt.md')).stat().st_size > 0
            context_file = folder / 'init_context.json'
            context = json.loads(context_file.read_text())
            key = 'invoke examples of focal method'
            examples = re.findall(r'^example \d+:$', context.get(key, ''), re.MULTILINE)
            expected = len(invoke[task['class']].get(task['method-name'], []))
            assert len(examples) == expected
            reports.append({'method': task['method-name'], 'id': task['id'],
                            'construct_context': str(context_file.relative_to(ROOT)),
                            'invoke_examples_present': key in context, 'invoke_example_count': len(examples)})
        result = {'status': 'passed', 'methods': reports, 'tasks': len(tasks),
                  'prompts_per_task': 1 + len(TS.PROMPT_LIST), 'prompt_list': TS.PROMPT_LIST,
                  'case_then_code': TS.CASE_THEN_CODE, 'model_calls': 0,
                  'selection': 'Fixed distinct method names with unique released IDs, avoiding addOption ID collisions.'}
        (OUT / 'prompt-check.json').write_text(json.dumps(result, indent=2) + '\n')
        print(json.dumps(result, indent=2))
    finally:
        jpype.shutdownJVM()


if __name__ == '__main__':
    main()
