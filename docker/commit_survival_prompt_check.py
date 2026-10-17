"""Assemble the released prompts without importing or calling generation code."""
import json
import os
from pathlib import Path
import sys

os.chdir('/work/code')
sys.path.insert(0, '/work/code')
import jpype
from settings import FileStructure as FS, TaskSettings as TS
from procedure import generate_prompt

TS.PROJECTS = ['commons-codec']
if TS.CASE_THEN_CODE:
    TS.PROMPT_LIST = [p if p.endswith('4case') or p == 'gencode' else p + '4case'
                      for p in TS.PROMPT_LIST]
    if 'gencode' not in TS.PROMPT_LIST:
        TS.PROMPT_LIST.append('gencode')
FS.PROMPT_PATH = '/work/evaluation/commit-survival-dry-check/<project>'
dataset = json.loads(Path(FS.DATASET_PATH, 'dataset_info.json').read_text())
tasks = dataset['commons-codec']['focal-methods']
assert len(tasks) == len({t['id'] for t in tasks}) == 52
assert Path(FS.DATASET_PATH, dataset['commons-codec']['project-url']).resolve() == Path('/work/data/projects/commons-codec')
jpype.startJVM(jpype.getDefaultJVMPath(), '-Xmx4g', '-Djava.class.path=' + os.pathsep.join([
    './Java/project-info-process.jar', './Java/project-index-builder.jar']))
try:
    generate_prompt.generate_init_prompts(FS, TS, dataset)
    generate_prompt.generate_test_case_prompts(FS, TS, dataset)
    for task in tasks:
        folder = Path(FS.PROMPT_PATH.replace('<project>', 'commons-codec'), task['id'])
        for prompt in ['init'] + TS.PROMPT_LIST:
            assert (folder / (prompt + '_prompt.md')).stat().st_size > 0
    result = {'tasks': len(tasks), 'prompts_per_task': 1 + len(TS.PROMPT_LIST),
              'prompt_list': TS.PROMPT_LIST, 'case_then_code': TS.CASE_THEN_CODE,
              'model_calls': 0, 'status': 'passed'}
    Path('/work/results/commit-survival/prompt-check.json').write_text(json.dumps(result, indent=2) + '\n')
    print(json.dumps(result))
finally:
    jpype.shutdownJVM()
