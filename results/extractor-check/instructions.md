# KTester extractor check: build the project index at an arbitrary commit

Purpose: confirm that KTester's project index (json, codegraph, invoke patterns, lucene) can be rebuilt for a commit that has no author snapshot. Last survey stopped at `NetworkXNoPath` in invoke-pattern extraction. This run patches that one spot, verifies the output against the author's index on commons-cli, then builds an index at a fresh commons-cli commit end to end. No model calls.

## Ground rules

- Repository: `/Users/donggi/_projects/experiment-projects/KTester`. Create branch `experiment/2026-10-week4-ktester-extractor-check` from the current HEAD of `experiment/2026-10-week3-ktester-commit-survival`. Do not push.
- Run everything inside Docker: from `docker/`, `docker compose run --rm ktester bash -c '...'`. The repo is mounted at `/work`.
- Author files under `code/`, `data/projects/`, `data/project_index/` stay untouched, with one exception: the patch in Step 2. Record its diff.
- Index outputs go under `scratch/` (gitignored). Reports go under `results/extractor-check/` (committed). Prior survey outputs under `results/stage0-survey/` and `scratch/extract-check/` are read-only references; do not overwrite them.
- Never print or commit `code/settings.py`.
- Keep every number in the report traceable to a file under `results/extractor-check/`.

## Step 1. Reproduce the failure

1. Prepare inputs the same way the survey did: `python3 docker/stage0_prepare_extract.py` with the dataset root changed to `scratch/extractor-check/cli-snapshot/` (commons-cli upstream commit `90b3f2e`, snapshot `libs/`, `dependencies.txt`, `src/test-original/`, `dataset_info.json` with the commons-cli entry). Copy the script to `docker/extractor_check_prepare.py` if its paths are hard-coded; do not edit the original.
2. Run the index driver `docker/stage0_build_index.py` against that root (copy to `docker/extractor_check_build_index.py` and parameterize the comparison target if needed). Save stdout+stderr to `results/extractor-check/run1-baseline.txt`.
3. Confirm the traceback ends in `networkx.exception.NetworkXNoPath` inside `_get_lines_from_cfg`. If it does not, stop and write the handover.

## Step 2. Patch

In `code/procedure/preprocess_project.py`, `InvokePatternExtractor._get_lines_from_cfg`: wrap the `nx.all_shortest_paths(cfg, start_node, target)` iteration per target in `try/except nx.NetworkXNoPath`. On the exception, skip that target (its own lines are already in `visited`) and increment a module-level counter `UNREACHABLE_TARGETS`. Print the counter once at the end of `extract_invoke_patterns`. Nothing else changes. Save `git diff code/procedure/preprocess_project.py` to `results/extractor-check/patch.diff`.

## Step 3. Compare with the author's index (commons-cli at snapshot commit)

1. Rerun Step 1.2 with the patch. Save output to `results/extractor-check/run2-patched.txt`. Record wall time and `UNREACHABLE_TARGETS`.
2. Compare generated `scratch/.../codegraph/commons-cli_invoke.json` with `data/project_index/codegraph/commons-cli_invoke.json`. Normalize `\\` to `/` in `file_path`. For every `class_fqn#method_sig` key report one of: identical / only in author / only in generated / differs (list the differing line ranges). Write `results/extractor-check/invoke-compare.json` and a short `invoke-compare.md` with counts per category and the full list of non-identical keys.
3. Keep the json, callgraph, controlflow, and lucene comparisons the survey already does, written to the same folder.

## Step 4. Dependency check (only if Step 3 shows non-identical keys)

1. The survey logged `Unsolved symbol : org.junit.Assert`. Add `junit:junit:4.13.2` and `org.hamcrest:hamcrest-core:1.3` jars to the scratch `libs/` (download from Maven Central; do not touch the snapshot `libs/`).
2. Rerun Step 3. Save to `run3-with-junit.txt` and `invoke-compare-junit.{json,md}`. Report whether the warnings disappeared and whether the non-identical key count changed.

## Step 5. End-to-end index at a fresh commit (no author files)

1. Clone `apache/commons-cli` into `scratch/extractor-check/cli-fresh/projects/commons-cli` at upstream `master` HEAD recorded in `results/stage0-survey/upstream.md` (`58c64b5`). Build `libs/` with `mvn -q dependency:copy-dependencies -DoutputDirectory=libs` (JDK 17, offline cache allowed). Copy `src/test` to `src/test-original`. Write `dependencies.txt` the same way the snapshot has it.
2. Run the patched extractor + IndexBuilder on this root. Save output to `run4-fresh.txt`. Record counts: classes and methods in `json/`, entries in `_invoke.json`, lucene documents, `UNREACHABLE_TARGETS`, wall time.
3. Run `DatasetPrepare` the same way `docker/stage0_dataset_prepare.py` does, for the class `org.apache.commons.cli.Options` only, producing `dataset_info.json` under this root. Record the method count.
4. Assemble KTester prompts for three methods of that class without calling the model (reuse `docker/commit_survival_prompt_check.py`; copy and point it at this root if needed). Save the three construct-context dumps to `results/extractor-check/prompts/`. Report, per method, whether `invoke examples of focal method` is present and how many examples it holds.

## Step 6. Handover

Write `docs/handover-extractor-check.md` (local only) with: branch and last commit, the patch diff, a table of Step 3/4 comparison counts, Step 5 counts, every file written, anything that failed. Commit `results/extractor-check/` and the patch on the branch. Do not push.

## Stop conditions

Stop and write the handover instead of improvising when: the baseline in Step 1 does not fail as expected; the patch does not remove the exception; the Step 5 Maven build or `DatasetPrepare` fails after one retry. Do not change any other author code to get past a failure.
