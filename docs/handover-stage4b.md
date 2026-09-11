# Stage 4b handover: second full run with FIX_TRIES=5

## Run window and restarts

The detached Stage 4b run started at 2026-09-11 03:21:25 UTC (12:21:25 KST) and reported `ALL DONE` at 07:32:35 UTC (16:32:35 KST), a wall-clock window of 4 hours, 11 minutes, and 10 seconds. Every project recorded `generate_rc=0` and `coverage_rc=0`. The runner did not stop prematurely, no project was restarted, and no targeted rerun was needed: all 111 expected task IDs had a final test class after their first full project run.

| Project | Start (UTC) | End (UTC) | Generate RC | Coverage RC |
| --- | --- | --- | ---: | ---: |
| commons-cli | 2026-09-11 03:21:25 | 2026-09-11 03:22:48 | 0 | 0 |
| windward | 2026-09-11 03:22:48 | 2026-09-11 03:24:49 | 0 | 0 |
| commons-csv | 2026-09-11 03:24:49 | 2026-09-11 03:31:49 | 0 | 0 |
| datafaker | 2026-09-11 03:31:49 | 2026-09-11 03:36:53 | 0 | 0 |
| batch-processing-gateway | 2026-09-11 03:36:56 | 2026-09-11 03:44:52 | 0 | 0 |
| commons-collections | 2026-09-11 03:44:53 | 2026-09-11 03:56:53 | 0 | 0 |
| ruler | 2026-09-11 03:57:09 | 2026-09-11 04:14:52 | 0 | 0 |
| commons-codec | 2026-09-11 04:14:53 | 2026-09-11 06:38:04 | 0 | 0 |
| gson | 2026-09-11 06:38:07 | 2026-09-11 06:57:25 | 0 | 0 |
| jdom2 | 2026-09-11 06:57:28 | 2026-09-11 07:32:31 | 0 | 0 |

`commons-codec` dominated the run time. `Sha2Crypt_sha2Crypt_Test` repeatedly consumed the 10-minute single-test timeout through all five repair rounds, then consumed it again during final generation validation and coverage. The measured Stage 4b mean was 150.04 seconds per task, compared with 83.96 seconds in Stage 4 and the authors' saved 152.68 seconds.

## Three-way comparison

The following is copied verbatim from `results/stage5/comparison_project_table_3way.md`.

| Project | Side | Tasks | TC compile | TC execution | Task compile | Task all pass | Task any pass | Mean inst cov | Mean branch cov | Mean correct inst cov | Mean correct branch cov | Tests/class |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| batch-processing-gateway | Ours (FIX_TRIES=3) | 6 | 66.67% | 41.67% | 66.67% | 16.67% | 33.33% | 49.33% | 45.83% | 31.33% | 28.83% | 10.00 |
| batch-processing-gateway | Ours (FIX_TRIES=5) | 6 | 87.23% | 61.70% | 83.33% | 50.00% | 50.00% | 34.00% | 31.50% | 30.33% | 24.67% | 7.83 |
| batch-processing-gateway | Authors mean | 6 | 100.00% | 83.44% | 100.00% | 63.33% | 96.67% | 79.33% | 65.03% | 76.83% | 60.70% | 6.23 |
| commons-cli | Ours (FIX_TRIES=3) | 2 | 100.00% | 63.41% | 100.00% | 50.00% | 100.00% | 98.50% | 96.00% | 82.00% | 69.00% | 20.50 |
| commons-cli | Ours (FIX_TRIES=5) | 2 | 100.00% | 70.00% | 100.00% | 50.00% | 100.00% | 97.50% | 86.50% | 60.00% | 55.50% | 10.00 |
| commons-cli | Authors mean | 2 | 100.00% | 79.04% | 100.00% | 60.00% | 100.00% | 95.20% | 87.10% | 74.80% | 66.40% | 8.40 |
| commons-codec | Ours (FIX_TRIES=3) | 18 | 96.79% | 48.17% | 94.44% | 16.67% | 83.33% | 61.94% | 57.50% | 47.72% | 45.33% | 12.11 |
| commons-codec | Ours (FIX_TRIES=5) | 18 | 100.00% | 43.33% | 100.00% | 27.78% | 66.67% | 58.33% | 50.28% | 38.39% | 33.78% | 11.67 |
| commons-codec | Authors mean | 18 | 100.00% | 77.85% | 100.00% | 53.33% | 95.56% | 72.56% | 63.78% | 64.93% | 56.50% | 8.53 |
| commons-collections | Ours (FIX_TRIES=3) | 14 | 82.35% | 71.90% | 85.71% | 50.00% | 71.43% | 47.43% | 40.43% | 41.93% | 36.29% | 10.93 |
| commons-collections | Ours (FIX_TRIES=5) | 14 | 97.14% | 90.00% | 92.86% | 78.57% | 92.86% | 49.21% | 43.14% | 49.21% | 42.93% | 10.00 |
| commons-collections | Authors mean | 14 | 100.00% | 89.26% | 100.00% | 78.57% | 97.14% | 60.96% | 51.24% | 56.71% | 47.63% | 7.50 |
| commons-csv | Ours (FIX_TRIES=3) | 6 | 69.81% | 32.08% | 83.33% | 16.67% | 66.67% | 48.17% | 47.50% | 40.50% | 36.67% | 8.83 |
| commons-csv | Ours (FIX_TRIES=5) | 6 | 78.38% | 24.32% | 83.33% | 16.67% | 50.00% | 49.50% | 46.83% | 23.83% | 19.50% | 12.33 |
| commons-csv | Authors mean | 6 | 100.00% | 62.13% | 100.00% | 50.00% | 96.67% | 67.23% | 59.77% | 56.47% | 48.20% | 7.40 |
| datafaker | Ours (FIX_TRIES=3) | 6 | 73.26% | 67.44% | 83.33% | 50.00% | 83.33% | 69.17% | 64.17% | 42.67% | 40.17% | 14.33 |
| datafaker | Ours (FIX_TRIES=5) | 6 | 83.58% | 37.31% | 83.33% | 16.67% | 66.67% | 72.50% | 68.83% | 52.83% | 45.50% | 11.17 |
| datafaker | Authors mean | 6 | 100.00% | 88.06% | 100.00% | 66.67% | 100.00% | 77.70% | 71.73% | 63.57% | 57.77% | 9.63 |
| gson | Ours (FIX_TRIES=3) | 20 | 92.67% | 65.52% | 90.00% | 45.00% | 80.00% | 59.75% | 54.45% | 55.55% | 50.40% | 11.60 |
| gson | Ours (FIX_TRIES=5) | 20 | 81.45% | 49.32% | 75.00% | 25.00% | 60.00% | 40.55% | 36.45% | 35.40% | 30.90% | 11.05 |
| gson | Authors mean | 20 | 100.00% | 80.40% | 100.00% | 62.00% | 99.00% | 72.50% | 63.51% | 63.46% | 54.30% | 8.94 |
| jdom2 | Ours (FIX_TRIES=3) | 21 | 75.77% | 40.72% | 80.95% | 23.81% | 57.14% | 36.71% | 33.43% | 26.57% | 23.67% | 9.24 |
| jdom2 | Ours (FIX_TRIES=5) | 21 | 85.39% | 29.21% | 90.48% | 19.05% | 57.14% | 40.38% | 36.90% | 23.57% | 21.14% | 8.48 |
| jdom2 | Authors mean | 21 | 100.00% | 73.94% | 100.00% | 57.14% | 91.43% | 61.07% | 53.80% | 49.94% | 43.43% | 7.97 |
| ruler | Ours (FIX_TRIES=3) | 16 | 67.92% | 25.47% | 75.00% | 18.75% | 18.75% | 16.38% | 12.12% | 6.81% | 4.81% | 6.62 |
| ruler | Ours (FIX_TRIES=5) | 16 | 71.25% | 35.00% | 75.00% | 18.75% | 37.50% | 18.38% | 12.81% | 9.06% | 5.56% | 10.00 |
| ruler | Authors mean | 16 | 100.00% | 70.76% | 100.00% | 53.75% | 87.50% | 33.90% | 26.20% | 26.45% | 19.21% | 7.21 |
| windward | Ours (FIX_TRIES=3) | 2 | 32.00% | 24.00% | 50.00% | 0.00% | 50.00% | 49.00% | 47.00% | 49.00% | 45.50% | 12.50 |
| windward | Ours (FIX_TRIES=5) | 2 | 64.44% | 64.44% | 50.00% | 50.00% | 50.00% | 45.50% | 44.00% | 45.50% | 44.00% | 22.50 |
| windward | Authors mean | 2 | 100.00% | 98.46% | 100.00% | 90.00% | 100.00% | 63.80% | 59.30% | 63.70% | 59.00% | 9.20 |
| Total | Ours (FIX_TRIES=3) | 111 | 82.19% | 51.80% | 83.78% | 29.73% | 63.06% | 47.77% | 43.40% | 37.59% | 33.95% | 10.52 |
| Total | Ours (FIX_TRIES=5) | 111 | 85.71% | 47.25% | 85.59% | 31.53% | 61.26% | 44.27% | 39.30% | 32.26% | 27.90% | 10.47 |
| Total | Authors mean | 111 | 100.00% | 78.81% | 100.00% | 60.72% | 94.95% | 63.94% | 55.46% | 55.52% | 47.21% | 8.06 |

The larger repair budget increased test-case compile rate by 3.52 percentage points and task compile rate by 1.81 points. It did not close the authors' compile gap, and the single Stage 4b sample was worse on test-case execution (-4.55 points), task-any-pass (-1.80 points), and correct coverage (-5.33 instruction and -6.05 branch points). This comparison changes both the stochastic generation sample and the repair budget, so those movements cannot be attributed to repair attempts alone.

## Recomputed gap analysis

For correct instruction coverage, the FIX_TRIES=5 run is inside the authors' five-repetition range for 75 of 111 tasks, below it for 33, and above it for 3. Stage 4's corresponding counts were 80 inside, 24 below, and 7 above.

The project-level gap exceeds the authors' own repetition spread for the following number of the four checked metrics (test-case compile, test-case execution, correct instruction coverage, and correct branch coverage):

- `batch-processing-gateway`: 4 of 4.
- `commons-cli`: 0 of 4.
- `commons-codec`: 3 of 4.
- `commons-collections`: 2 of 4.
- `commons-csv`: 3 of 4.
- `datafaker`: 2 of 4.
- `gson`: 3 of 4.
- `jdom2`: 4 of 4.
- `ruler`: 1 of 4.
- `windward`: 2 of 4.

The full metric-by-metric table is in `results/stage5/gap_analysis_fixtries5.md`.

## Stage 4 compile-failure follow-up

Of the 18 final classes that did not compile with FIX_TRIES=3, 10 compile in Stage 4b and 8 remain non-compiling. For all 18, the highest saved repair prompt is `repair_prompt_4.md`, meaning that all five permitted model repair responses were applied before the final class. There were also 8 new non-compiling tasks, leaving 16 Stage 4b task-level compile failures in total.

| Project | Task | Compiles in Stage 4b | Highest repair prompt | Final error type |
| --- | --- | --- | --- | --- |
| batch-processing-gateway | ApplicationSubmissionHelper_getExecutorSpec | yes | repair_prompt_4.md | none |
| batch-processing-gateway | SubmissionSummary_copyFrom | yes | repair_prompt_4.md | none |
| commons-codec | MurmurHash3_hash128x64Internal | yes | repair_prompt_4.md | none |
| commons-collections | AbstractPatriciaTrie_nextEntryImpl | yes | repair_prompt_4.md | none |
| commons-collections | SequencesComparator_getMiddleSnake | no | repair_prompt_4.md | compile error |
| commons-csv | CSVParser_createHeaders | no | repair_prompt_4.md | compile error |
| datafaker | SqlTransformer_handlePrimitivesInArray | no | repair_prompt_4.md | compile error |
| gson | LinkedTreeMap_find | yes | repair_prompt_4.md | none |
| gson | LinkedTreeMap_rebalance | no | repair_prompt_4.md | compile error |
| jdom2 | AbstractXMLOutputProcessor_printContent | yes | repair_prompt_4.md | none |
| jdom2 | StAXStreamBuilder_processPrunableElement | yes | repair_prompt_4.md | none |
| jdom2 | WalkerTRIM_analyzeMultiText | yes | repair_prompt_4.md | none |
| jdom2 | XPathHelper_getSingleStep | no | repair_prompt_4.md | compile error |
| ruler | ByteMachine_addRangePattern | no | repair_prompt_4.md | compile error |
| ruler | ByteMachine_findMatchPattern | no | repair_prompt_4.md | compile error |
| ruler | RuleCompiler_processMatchExpression | yes | repair_prompt_4.md | none |
| ruler | Ruler_matches | yes | repair_prompt_4.md | none |
| windward | AbstractRouterGroup_matchRouter | no | repair_prompt_4.md | compile error |

### New Stage 4b non-compiling tasks

- `batch-processing-gateway`: `ApplicationSubmissionHelper_getDriverSpec`.
- `gson`: `$Gson$Types_equals`, `Excluder_excludeField`, `ReflectiveTypeAdapterFactory_getBoundFields`, and `TypeToken_isAssignableFrom`.
- `jdom2`: `StringBin_locate`.
- `ruler`: `ByteMachine_addEndOfMatch` and `JsonRuleCompiler_processMatchExpression`.

The full generated follow-up is in `results/stage5/compile_failures_fixtries5.md`.

## Mechanical fixes and run anomalies

- `docker/run_project.sh` and `docker/run_all.sh` now accept a results-stage argument while defaulting to `stage4`, so the same runner archives this run under `results/stage4b/` without changing Stage 4 behavior.
- `docker/aggregate_stage4.py` accepts an optional results-stage argument, defaulting to `stage4`. `docker/aggregate_compare.py` generates the three-way artifacts, and `docker/analyze_stage5.py --fixtries5` generates only the new gap and compile-failure analyses. All three scripts passed `py_compile` and `git diff --check`.
- The global `*.log` ignore rule omitted archived logs from the normal per-project `git add`. The 20 project logs and the timing log were force-added after confirming that Stage 4 tracks the corresponding files. No result content was regenerated.
- Archive copies for `commons-collections` and `ruler` printed harmless `.DS_Store` destination-exists warnings. The project archives and both return codes were complete.
- KTester's repair parser printed non-fatal warnings for `SparkPodSpec_copyFrom` and `Ruler_matches` (class declaration not found) and for `AbstractStAXEventProcessor_printContent` (added code could not be parsed as a class). The affected projects still produced all expected final classes.
- `jdom2` logged nine `OutOfMemoryError` occurrences during generation and one during coverage, concentrated in generated SAX-output tests. KTester continued, and the project finished with return codes 0/0. These errors affect individual test outcomes even though they do not make the project command fail.
- `post_process.CodeRepairer` derives `half_tries` from `max_tries`. Raising `FIX_TRIES` from 3 to 5 therefore moved error-case removal from round 1 to round 2; this is part of the paper's configuration rather than an independent behavioral change.

## Open questions for Stage 6

1. How strongly should the final report attribute compile-rate differences to repair budget? FIX_TRIES=5 recovered 10 of the 18 old failures, but 8 old failures remained and 8 new failures appeared in a different stochastic sample.
2. Should the final report emphasize that the higher repair budget improved compilation while execution and correct coverage declined in this single repetition? Multiple FIX_TRIES=5 repetitions would be needed to separate budget effects from sampling variance.
3. Should the long `Sha2Crypt_sha2Crypt` timeouts and jdom2 JVM OOMs be treated as environment/runtime limitations, generated-test defects, or both when interpreting execution and coverage gaps?
4. Which unpublished aggregation or filtering rule produced the paper's EPR and tests/class figures, and should the published model be labeled gpt-4o-mini, `KTester-gpt4o`, or both?

The final report remains for Stage 6.
