# KTester extractor check

The specified NetworkX patch allows a fresh-commit index and prompt assembly to finish. It does not reproduce the author invoke index exactly.

## Scope and provenance

Branch: `experiment/2026-10-week4-ktester-extractor-check`. Base: `e003545d88f15431efedcd06a9b5235e17c9ea57`. No push and no model calls.

The requested `claude/ktester-extractor-check-cc-instructions-2026-10-week4.md` path was absent. The repository copy `docs/cc-instructions-extractor-check.md` was used and preserved as `instructions.md`.

All experiment scripts ran in `docker/` using `docker compose run --rm ktester bash -c ...`, with the repository mounted at `/work`. Environment and jar hashes are in `environment.json`. Only `code/procedure/preprocess_project.py` was changed under author code/data; `patch.diff` records it. Existing `.gitignore` edits and the staged deletion of `docs/handover-stage0-commit-survival.md` belong to the user and are excluded from this commit.

## Baseline and snapshot comparison

`run1-baseline.txt` reproduces `networkx.exception.NetworkXNoPath` inside `_get_lines_from_cfg`; index construction took 3.179 seconds. The driver catches that exception so it can retain the other comparisons. See `run1-baseline.json`.

| Run | Identical | Only author | Only generated | Differs | Unreachable targets | Index seconds |
|---|---:|---:|---:|---:|---:|---:|
| run2-patched | 48 | 0 | 0 | 93 | 397 | 2.431 |
| run3-with-junit | 48 | 0 | 0 | 93 | 397 | 2.535 |

Counts and full per-key differing line ranges are in `invoke-compare.json` and `invoke-compare-junit.json`; their Markdown companions list every non-identical key. Only `file_path` separators are normalized in these comparisons. Order and line ranges remain significant.

`UNREACHABLE_TARGETS` counts caught target occurrences across extraction calls, not distinct CFG nodes. Index seconds are monotonic wall time through IndexBuilder, excluding the subsequent author comparison and JVM shutdown.

| Raw JSON comparison | Patched differing paths | With JUnit differing paths |
|---|---:|---:|
| json/commons-cli.json | 348 | 6499 |
| codegraph/commons-cli_callgraph.json | 106 | 106 |
| codegraph/commons-cli_controlflow.json | 4926 | 4926 |
| codegraph/commons-cli_invoke.json | 900 | 900 |

Raw comparisons retain the survey’s CRLF-to-LF normalization only, so invoke path-separator differences remain in the raw counts. Full differing JSON paths are in the corresponding run JSON files.

Lucene: author 223, patched 223, JUnit 223 documents; identifier sets match in both patched runs. This checks counts and identifiers, not all stored contents or ranking.

JUnit Assert warning occurrences fell from 774 to 0. The generated invoke files are byte-identical before/after adding JUnit, and the non-identical key count did not change. One Java `ResolvedWildcard` diagnostic remains in the JUnit run. Evidence: `warnings.json`, `verification.json`, `run3-with-junit.txt`, and dependency hashes in `download-junit.txt`.

## Fresh-commit end-to-end result

Fresh upstream checkout: `58c64b52a8fb1c38c4f4b2d41db0349241475f95`. Dependency resolution succeeded on its first Maven attempt; `fresh-prepare.json` records command, duration, dependency classpath, and jar hashes. No author snapshot/index files were copied into the fresh root.

| Metric | Count |
|---|---:|
| JSON source classes | 40 |
| JSON source methods | 370 |
| JSON source constructors | 43 |
| JSON test classes | 62 |
| JSON test methods | 518 |
| JSON test constructors | 7 |
| JSON total classes | 102 |
| JSON total methods (constructors excluded) | 888 |
| Invoke class entries | 40 |
| Invoke method/constructor keys | 227 |
| Invoke examples | 396 |
| Lucene documents | 370 |
| Unreachable target occurrences | 596 |
| Index seconds | 4.746 |

Evidence: `run4-fresh.json` and `run4-fresh.txt`. Classes are JSON class entries, including the extractor’s nested classes; methods count method overload records. Invoke method keys include constructors.

DatasetPrepare extracted all 18 declared non-private methods of `org.apache.commons.cli.Options`, with no missing/unexpected methods or empty code fields. There are only 16 unique IDs: `Options_addOption_2` is shared by the three string-based overloads. This is a released DatasetPrepare ID bug, preserved without repair. Extraction succeeded on the first attempt; the complete dataset is not safe for ID-keyed batch generation. Evidence: `fresh-dataset-check-attempt-1.json`, `fresh-dataset-meta.json`, and `fresh-dataset-info-attempt-1.json`.

| Prompt method | Invoke examples present | Examples |
|---|---|---:|
| `getOption(String)` | True | 3 |
| `hasOption(String)` | True | 1 |
| `getRequiredOptions()` | True | 1 |

The selected methods have distinct IDs. All 15 prompt files are nonempty; construct/usage contexts and prompts are under `prompts/`. `prompt-check.json` records the selection and model-call count. Assembly was checked; no generated tests were compiled or executed.

## Remaining limitations and checks

The fresh Java extractor emitted 70 nonfatal `Error:` lines, including unresolved symbols, ambiguous calls, unsupported `ConditionalExpr`, and missing node data. Its process still completed and produced all index families. This demonstrates operational rebuildability, not complete or semantically equivalent extraction. Exact diagnostic counts/messages are in `warnings.json`.

The author-index differences and DatasetPrepare ID collision remain unresolved because the instructions permit no other author-code changes. No specified stop condition was triggered after the expected baseline failure.

The focused mixed-reachability check confirms that both equal-length reachable paths and an unreachable target’s own line are retained, with one counter increment. The no-BEGIN behavior and Python syntax checks also passed. Evidence: `verification.json`.

Source whitespace checks pass. A full artifact whitespace check flags trailing whitespace emitted by the original prompt templates and DatasetPrepare stdout, plus whitespace-only context lines in `patch.diff`; those raw evidence files are preserved unchanged.

## Files and reproduction

The `docker/extractor_check_*.py` helpers are committed with the reports and patch. `file-inventory.json` lists experiment-created outputs, including the ignored scratch trees; local handover is `docs/handover-extractor-check.md`. Prior survey reports and `scratch/extract-check/` were not written by these scripts.

From `docker/`, prefix each command below with `docker compose run --rm ktester bash -c`, running the quoted body from `/work`. These commands document the completed sequence; fresh preparation requires a new empty destination. The baseline must run before the patch.

```sh
cd /work && python3 docker/extractor_check_prepare.py
cd /work && python3 docker/extractor_check_build_index.py --dataset /work/scratch/extractor-check/cli-snapshot --output /work/scratch/extractor-check/cli-snapshot/project_index --report /work/results/extractor-check/run2-patched.json
# Add the Maven Central JUnit/Hamcrest jars to scratch libs, as recorded in instructions.md and download-junit.txt.
cd /work && python3 docker/extractor_check_build_index.py --dataset /work/scratch/extractor-check/cli-snapshot --output /work/scratch/extractor-check/cli-snapshot/project_index-junit --report /work/results/extractor-check/run3-with-junit.json
cd /work && python3 docker/extractor_check_compare.py --generated /work/scratch/extractor-check/cli-snapshot/project_index/codegraph/commons-cli_invoke.json
cd /work && python3 docker/extractor_check_compare.py --generated /work/scratch/extractor-check/cli-snapshot/project_index-junit/codegraph/commons-cli_invoke.json --name invoke-compare-junit
cd /work && python3 docker/extractor_check_fresh_prepare.py
cd /work && python3 docker/extractor_check_fresh_index.py
cd /work && python3 docker/extractor_check_dataset_prepare.py --attempt 1
cd /work && python3 docker/extractor_check_prompt_check.py
cd /work && python3 docker/extractor_check_report.py
```
