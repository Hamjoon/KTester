#!/usr/bin/env python3
"""Render the ignored final handover from committed experiment measurements."""
import argparse
import ast
import csv
import json
from pathlib import Path
import subprocess

KT = Path(__file__).resolve().parents[1]


def revision(repo):
    return subprocess.check_output(['git', '-C', str(repo), 'rev-parse', 'HEAD'], text=True).strip()


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--repo', type=Path, required=True)
    args = ap.parse_args()
    exp = args.repo / 'experiments/ktester-commit-survival'
    funnel = json.loads((exp / 'results/llm-funnel.json').read_text())
    assert funnel['frozen'], 'Cannot issue final handover before freezing the denominator'
    denominator = list(csv.DictReader((exp / 'results/llm-denominator.csv').open()))
    assert len(denominator) == funnel['llm_denominator']
    safe = {}
    for node in ast.walk(ast.parse((KT / 'code/settings.py').read_text())):
        if isinstance(node, ast.Assign):
            for target in node.targets:
                if isinstance(target, ast.Name) and target.id in ['MODEL', 'FIX_TRIES', 'TEMPERATURE', 'MAX_WORKERS', 'DATASET_PATH', 'CODE_INFO_PATH', 'CASES_LIST', 'PROMPT_LIST', 'CASE_THEN_CODE']:
                    safe[target.id] = ast.literal_eval(node.value)
    assert safe['CASES_LIST'] == []
    generation = []
    for row in funnel['rounds']:
        path = KT / f"results/commit-survival/round{row['round']}/commons-codec/generation-summary.json"
        generation.append(json.loads(path.read_text()))
    lines = ['# Part 2 final handover', '',
             f"Part 2 is complete. The frozen LLM denominator is **{len(denominator)}**, compared with **143** developer methods. Rounds: **{len(generation)}**. Duplicates removed: **{funnel['duplicates_removed']}**. Shortfall: **{funnel['shortfall']}**.", '',
             '## Final repositories', '',
             f'- KTester: `{revision(KT)}`.', f'- commons-codec: `{revision(args.repo)}`.',
             '- Branch in both repositories: `experiment/2026-10-week3-ktester-commit-survival`.',
             '- No pushes. Every created commit has the required Codex co-author trailer. This handover is ignored.', '',
             '## Population and settings', '',
             'Population: 52 non-private, non-deprecated methods with a body, excluding constructors. Measured class counts: Base32 5, Base64 22, Md5Crypt 10, MurmurHash3 9, Sha2Crypt 6. Previous 66-method files remain in population-66/. All 14 excluded methods were verified @Deprecated in the snapshot and upstream cd16988. The five original included focal entries match the author fields up to line endings; 52 unique IDs and nonempty code fields are verified.', '',
             'Fixed settings (no key):', '', '```json', json.dumps(safe, indent=2), '```', '',
             'The dataset uses the existing symlink to the authors’ snapshot and the authors’ unchanged index. The developer denominator and 539-commit range are unchanged. No index rebuild, workspace reset or preparation.py -W was run.', '',
             'Provider routing is unpinned. Gary explicitly authorized removing only the extra_body routing argument, added locally in September commit 9c055e13. Separate commit b61d042, “Unpin OpenRouter provider for gpt-oss-120b”, removes exactly one line. This is the sole authorized pipeline-code exception; model, prompts, algorithm, repair attempts, temperature, worker count and scoring remain unchanged. The released client does not retain provider metadata, so actual provider identity is unavailable.', '',
             '## Generation rounds', '',
             '| Round | Tasks submitted | Files | 600s timeouts | OOM occurrences | Wall seconds | Author compile pass | Author execution pass | Author tests/class |',
             '|---:|---:|---:|---:|---:|---:|---:|---:|---:|']
    for g in generation:
        a = g['authors_evaluator_reference']
        lines.append('| ' + ' | '.join(str(x) for x in [g['round'], g['tasks_submitted'], g['test_class_files'], g['timeouts'], g['out_of_memory_occurrences'], g['wall_seconds'], a['compile_pass_rate'], a['execution_pass_rate'], a['tests_per_produced_class']]) + ' |')
    lines += ['', 'Author evaluator values are reference measurements only. The LLM denominator comes from the independent three-run validation at t. generation-summary.json stores exact run windows, return codes, missing class IDs, error records, timeout/OOM task associations and reference counts for each round. Timeout events inferred from a missing JavaRunner return-code record and at least 599 seconds before the next command are explicitly labeled as inferred.', '']
    for g in generation:
        lines += [f"- Round {g['round']}: {g['run']['start']} to {g['run']['end']}; generate_rc={g['run']['generate_rc']}; coverage_rc={g['run']['coverage_rc']}; missing class IDs: {', '.join(g['missing_test_class_ids']) or 'none'}."]
    lines += ['', 'Canonical generation archives are under KTester results/commit-survival/roundN/. Relative symlinks at results/commit-survival-roundN/ preserve the prescribed stage paths. The source run_project.sh and its generation/evaluation invocation are unchanged. Raw generation/coverage logs are archived; the repository’s existing ignore rules continue to exclude binary class files and JaCoCo HTML. Generated source files are under generated/roundN/ here, with r2-/r3- filename prefixes for subsequent rounds.', '',
             '## Independent funnel and freeze', '',
             (exp / 'results/llm-funnel.md').read_text().strip(), '',
             'Frozen outputs: results/llm-denominator.csv, generated/MANIFEST.md, generated/frozen-source-sha256.json. All generated sources are preserved unchanged, including compile failures and classes contributing no retained method. Do not edit or regenerate them. Per-round method/case CSVs and validation JSON retain compile errors, exact commands and each run’s results. Logs and XML remain in ignored work/llm-roundN/.', '',
             'Validator checks covered assertion failures, exceptions, disabled tests, parameterized methods, nested tests, compile failure, timeout process termination and semantic literal preservation during body normalization; results/llm-validator-selftest.json records the checks.', '',
             '## Deviations and preparation corrections', '',
             'The authorized addendum reduced the population from 66 to 52. The initial population helper’s whole-file equality check was too strict because Base32/Base64 differ in comments; the corrected helper verified every parsed method declaration without comments and deprecation annotations in both sources. No source/index/jar/author-dataset changes were made. The 52-task no-model preflight assembled all 260 prompts successfully.', '']
    for g in generation:
        affected = g['missing_output_directory_task_ids']
        if affected:
            lines += [f"Round {g['round']} encountered missing response/repair output directories for {len(affected)} task IDs: " + ', '.join(affected) + '. The preflight checked prompt assembly but did not create every per-task response/fix directory. The running generation was left untouched and allowed to finish. Existing class files were not eligible for a targeted rerun under the stated rule; the independent funnel determines whether another full round is needed.', '']
    if len(generation) > 1:
        lines += ['Before subsequent rounds, only authorized output files were removed; no directories were removed. Missing per-task directories were created to match preparation.set_file_structure(), without resetting the source workspace or changing pipeline code. KTester roundN-workspace.json records every removal and directory creation.', '']
    lines += ['The run was resumed after a user-interrupted monitoring turn without restarting the active generation. No extra full-generation invocation was made for a round. Review each archived generation summary for any other model JSON parsing or execution errors; these were preserved rather than silently repaired outside the released pipeline.', '',
              f"TODO (Gary): OpenRouter tokens and cost for the run window {generation[0]['run']['start']} to {generation[-1]['run']['end']}."]
    (exp / 'docs/handover-part2.md').write_text('\n'.join(lines) + '\n')
    print(json.dumps({'llm_denominator': len(denominator), 'developer_denominator': 143,
                      'rounds': len(generation), 'shortfall': funnel['shortfall'], 'handover': str(exp / 'docs/handover-part2.md')}))


if __name__ == '__main__':
    main()
