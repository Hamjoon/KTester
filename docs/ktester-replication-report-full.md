# KTester Replication Report (full)

Replication of *Knowledge Matters: Injecting Project and Testing Knowledge into LLM-based Unit Test Generation* (Li, Liu, Chen, Pei, Li, Dai, Wang, Zheng; ICSE 2026; arXiv 2511.14224; tool: KTester).
Period: 2026-09-10 to 2026-09-13. This is the authoritative record; the summary report is an excerpt of it. Every number here is produced by a script whose output is committed under `results/`.

Repository: KTester fork, branch `experiment/2026-09-week2-ktester-replication`. Stage handovers: `docs/handover-stage1-2.md`, `docs/handover-stage3.md`, `docs/handover-stage4.md`, `docs/handover-stage5.md`, `docs/handover-stage4b.md`, `docs/handover-stage4c.md`. Agent instruction files: `docs/cc-instructions-stage*.md`.

Revision note: the 2026-09-11 version of this report covered runs A and B (gpt-4o-mini). This version adds run C (gpt-4o), the evaluator correct-coverage check, and the revised interpretation.

## 1. Replication question and scope

Question: can the paper's main experiment (RQ1, Table 2, KTester row) be re-run from the released artifacts, and do the resulting numbers match the paper and the authors' own published result archive?

In scope: the KTester pipeline on the full dataset with the paper's model (gpt-4o-mini), and one further full run with gpt-4o to test whether the model explains the gap found in the first two runs. Out of scope: re-running the four baseline tools (ChatUniTest, ChatTester, HITS, UTGen), the RQ2 ablations, and the other-model discussion.

## 2. Artifacts used

| Artifact | Source | Notes |
|---|---|---|
| Code | github.com/SYSUSELab/KTester, commit `fce83f1` (2026-04-03) | Python 3.13 + two Java jars (Spoon/JavaParser extractor, Lucene indexer) |
| Prompt templates | `code/templates/*.j2` (10 files) | condition / io / exception case prompts, init structure, code generation, repair |
| Dataset index | `data/dataset_info.json` | 10 projects, 111 focal methods (paper text: 110) |
| Static-analysis outputs | `data/project_index/{json,codegraph}` | committed in the repository |
| Maven projects | authors' Google Drive `projects.zip` (346 MB) | already contains `libs/` and `src/test-original/`; `src/test/` held leftovers of the authors' runs and was emptied |
| Lucene indexes | authors' Google Drive `lucene.zip` | 10 per-project indexes |
| Authors' results | authors' Google Drive `evaluation_results/RQ1.7z` (1.5 GB) | `RQ1/KTester-gpt4o/rep_1..rep_5/<project>/`, plus the four baselines and a time record |

Archive layout note: rep_1 has an extra `gpt4o/` directory level under `responses`, `fix`, `test_classes`, `reports` for nine projects (not for batch-processing-gateway); rep_2 to rep_5 do not. All 50 (rep, project) summaries exist and each contains the same 111 task keys as `dataset_info.json`.

## 3. Environment

Docker image `ktester-replication:latest` (`docker/Dockerfile`, `docker/compose.yml`): `python:3.13-slim-bookworm`, OpenJDK 17.0.20.1, Apache Maven 3.9.9, Python 3.13.15, JPype 1.6.0, `lxml` 6.1.3; `_JAVA_OPTIONS=-Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8`; 12 GB memory limit; arm64 (Apple Silicon host). Paper environment: OpenJDK 17.0.12, Python 3.13.0, Maven 3.9.9, Windows.

LLM access: OpenRouter, provider pinned to OpenAI with fallbacks disabled (the first trial showed OpenRouter alternating between OpenAI and Azure). JSON mode (`response_format={'type':'json_object'}`) was verified to pass through. Runs A and B used `openai/gpt-4o-mini`; the response `model` field reported `openai/gpt-4o-mini`. Run C used `openai/gpt-4o`; the API check reported `openai/gpt-4o` and the OpenRouter activity log showed Model = GPT-4o, Provider = OpenAI at 2026-09-12 20:07 KST (`results/stage4c/model_check.txt`).

## 4. Changes to the released code

All changes are separate commits with the reason in the message. None alters prompts, model parameters, the repair algorithm or scoring.

| # | File | Problem on Linux | Change |
|---|---|---|---|
| 1 | `settings.py.template`, `tools/time_agent.py` | `TimeRecord` settings class imported everywhere but absent from the template; first-use `KeyError` in time records | added the class; `setdefault` on first key |
| 2 | `procedure/workspace_preparation.py` | `subprocess.run(list, shell=True)` executes only `cd` on POSIX; `;` classpath separator | run Maven with `cwd=`; `os.pathsep` |
| 3 | `tools/execute_test.py` | same shell pattern in 3 call sites; `;` in test classpath | `cwd=` without shell; `os.pathsep` |
| 4 | `procedure/post_process.py` | compile-error parser converts paths to `\` before matching javac output, so on Linux no error line is ever found and rule-based repair is inert | `os.sep` |
| 5 | `preparation.py`, `generate_unit_test.py` | `;` in JPype classpath | `os.pathsep` |
| 6 | `tools/llm_api.py` | OpenRouter routed calls to two providers | `extra_body={"provider": {"order": ["OpenAI"], "allow_fallbacks": False}}` |

Why `shell=True` was not replaced by a joined string: three gson tasks have `$Gson$Types` in file and class names; a POSIX shell would expand `$Gson` to an empty string.

Helper scripts added (not part of KTester): `docker/reset_workspace.sh`, `docker/prepare_gson.sh` (gson needs the `generate-sources` phase for the templated `GsonBuildConfig` class, which the authors' `mvn compiler:compile` goal skips), `docker/run_project.sh`, `docker/run_all.sh`, `docker/aggregate_stage4.py`, `docker/aggregate_compare.py`, `docker/analyze_stage5.py`.

## 5. Configuration

`code/settings.py` (gitignored): `TEMPERATURE = 0.5`, `PROMPT_LIST = ['condition','io','exception']`, `CASE_THEN_CODE = True`, `SIM_TOP_K = "10"`, `MAX_WORKERS = 8`, `REPETITION_NUM = 5` (unused by us). All are the template defaults except the model string, the endpoint and, in runs B and C, `FIX_TRIES`.

| Run | `MODEL` | `FIX_TRIES` |
|---|---|---:|
| A | `openai/gpt-4o-mini` | 3 |
| B | `openai/gpt-4o-mini` | 5 |
| C | `openai/gpt-4o` | 5 |

`FIX_TRIES`: the released template says 3. The paper says "We limited automatic repair iterations to 5 in KTester." The authors' archive contains `repair_prompt_4.md` files (a fifth round) for 380 task/repetition pairs. Run A used 3, runs B and C used 5. `post_process.CodeRepairer` derives `half_tries` (the round from which failing test cases are commented out) from `max_tries`, so that threshold also moves from round 1 to round 2 between the runs; this is inherent to the paper's setting.

Repair loop, for reference: `post_process.check_test_class` iterates up to `FIX_TRIES` rounds. Each round applies rule-based repair (`repair_by_rules`), deletes a hanging method if the previous execution timed out (`check_timeout_cases`), makes at most one LLM repair call (`repair_by_LLM`), and from round `half_tries` onward comments out failing test cases (`clean_error_cases`). The candidate with the highest pass rate across rounds is kept.

Prompt drift check (`results/stage5/prompt_drift.md`): for 30 tasks in commons-cli, windward, commons-csv and gson, all condition / io / exception prompts are byte-identical to the authors' after line-ending normalisation; generation prompts differ only in the order of list members; the authors' `init_prompt.md` contains `@Timeout(600)` in every case, ours never does (the released template lacks it).

## 6. Runs

| Run | FIX_TRIES | Window (KST) | Wall time | Tasks | Test classes | Reruns |
|---|---:|---|---|---:|---:|---|
| Trial (Stage 3) | 3 | 2026-09-10 19:32–19:34 | 99 s | 2 (commons-cli) | 2 | – |
| A (Stage 4) | 3 | 2026-09-10 20:02–22:16 | 2 h 14 min | 111 | 111 | commons-cli once (see §10) |
| B (Stage 4b) | 5 | 2026-09-11 12:21–16:32 | 4 h 11 min | 111 | 111 | none |
| C (Stage 4c, gpt-4o) | 5 | 2026-09-12 20:24 – 09-13 01:59 | 5 h 35 min | 111 | 111 | none |

Projects were run in order commons-cli, windward, commons-csv, datafaker, batch-processing-gateway, commons-collections, ruler, commons-codec, gson, jdom2; each project's outputs were committed on completion (`results/stage4/<project>/`, `results/stage4b/<project>/`, `results/stage4c/<project>/`). In run C every project finished with `generate_rc=0` and `coverage_rc=0`, and no rate-limit or credit error appears in any log.

Token usage was read from the OpenRouter dashboard for the trial only: 13 generation calls, 34,533 input and 9,779 output tokens, $0.011. Runs A and B were not itemised; extrapolating by task count and prompt size puts each under $5. Run C cost $23.70 on the OpenRouter dashboard for the run window, $0.214 per task.

## 7. Metric definitions

All numbers come from KTester's `reports/summary.json` (one per project), which has per-task fields `test_cases`, `passed_cases`, `inst_cov`, `bran_cov`, `correct_inst_cov`, `correct_bran_cov` and project-level `compile_pass_rate`, `execution_pass_rate`, `average_*`. The same script (`docker/aggregate_compare.py`) is applied to our runs and to each of the authors' five repetitions.

- Test-case level: compile pass = compiled test cases / generated; execution pass = passed / generated.
- Task level: a task compiles if its final class compiled; "all pass" if every generated case passed; "any pass" if at least one did.
- Coverage: focal-method instruction and branch coverage from JaCoCo; "correct" variants count only passing tests. Means are unweighted over tasks.

Matching to the paper (`results/stage5/comparison_project_table.md`): the authors' archive, mean over 5 repetitions, gives CPR 100.00, instruction 63.94, branch 55.46, i.e. the paper's CPR, LC and BC exactly. The paper's LC is therefore KTester's instruction coverage. EPR: test-case level 78.81 (paper 76.41), task all-pass 60.72, task any-pass 94.95; none matches exactly. Tests per class: archive 8.06, paper 7.33.

## 8. Results

### 8.1 Four-way comparison (verbatim from `results/stage5/comparison_project_table_4way.md`)

| Project | Side | Tasks | TC compile | TC execution | Task compile | Task all pass | Task any pass | Mean inst cov | Mean branch cov | Mean correct inst cov | Mean correct branch cov | Tests/class |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| batch-processing-gateway | Ours (FIX_TRIES=3) | 6 | 66.67% | 41.67% | 66.67% | 16.67% | 33.33% | 49.33% | 45.83% | 31.33% | 28.83% | 10.00 |
| batch-processing-gateway | Ours (FIX_TRIES=5) | 6 | 87.23% | 61.70% | 83.33% | 50.00% | 50.00% | 34.00% | 31.50% | 30.33% | 24.67% | 7.83 |
| batch-processing-gateway | Ours (gpt-4o, FIX_TRIES=5) | 6 | 74.63% | 43.28% | 83.33% | 33.33% | 83.33% | 73.17% | 68.00% | 64.17% | 55.17% | 11.17 |
| batch-processing-gateway | Authors mean | 6 | 100.00% | 83.44% | 100.00% | 63.33% | 96.67% | 79.33% | 65.03% | 76.83% | 60.70% | 6.23 |
| commons-cli | Ours (FIX_TRIES=3) | 2 | 100.00% | 63.41% | 100.00% | 50.00% | 100.00% | 98.50% | 96.00% | 82.00% | 69.00% | 20.50 |
| commons-cli | Ours (FIX_TRIES=5) | 2 | 100.00% | 70.00% | 100.00% | 50.00% | 100.00% | 97.50% | 86.50% | 60.00% | 55.50% | 10.00 |
| commons-cli | Ours (gpt-4o, FIX_TRIES=5) | 2 | 100.00% | 80.56% | 100.00% | 50.00% | 100.00% | 98.50% | 92.00% | 98.50% | 90.00% | 18.00 |
| commons-cli | Authors mean | 2 | 100.00% | 79.04% | 100.00% | 60.00% | 100.00% | 95.20% | 87.10% | 74.80% | 66.40% | 8.40 |
| commons-codec | Ours (FIX_TRIES=3) | 18 | 96.79% | 48.17% | 94.44% | 16.67% | 83.33% | 61.94% | 57.50% | 47.72% | 45.33% | 12.11 |
| commons-codec | Ours (FIX_TRIES=5) | 18 | 100.00% | 43.33% | 100.00% | 27.78% | 66.67% | 58.33% | 50.28% | 38.39% | 33.78% | 11.67 |
| commons-codec | Ours (gpt-4o, FIX_TRIES=5) | 18 | 88.85% | 65.00% | 88.89% | 22.22% | 83.33% | 63.28% | 59.11% | 54.17% | 46.50% | 14.44 |
| commons-codec | Authors mean | 18 | 100.00% | 77.85% | 100.00% | 53.33% | 95.56% | 72.56% | 63.78% | 64.93% | 56.50% | 8.53 |
| commons-collections | Ours (FIX_TRIES=3) | 14 | 82.35% | 71.90% | 85.71% | 50.00% | 71.43% | 47.43% | 40.43% | 41.93% | 36.29% | 10.93 |
| commons-collections | Ours (FIX_TRIES=5) | 14 | 97.14% | 90.00% | 92.86% | 78.57% | 92.86% | 49.21% | 43.14% | 49.21% | 42.93% | 10.00 |
| commons-collections | Ours (gpt-4o, FIX_TRIES=5) | 14 | 100.00% | 70.62% | 100.00% | 71.43% | 85.71% | 50.14% | 41.43% | 47.43% | 38.79% | 11.43 |
| commons-collections | Authors mean | 14 | 100.00% | 89.26% | 100.00% | 78.57% | 97.14% | 60.96% | 51.24% | 56.71% | 47.63% | 7.50 |
| commons-csv | Ours (FIX_TRIES=3) | 6 | 69.81% | 32.08% | 83.33% | 16.67% | 66.67% | 48.17% | 47.50% | 40.50% | 36.67% | 8.83 |
| commons-csv | Ours (FIX_TRIES=5) | 6 | 78.38% | 24.32% | 83.33% | 16.67% | 50.00% | 49.50% | 46.83% | 23.83% | 19.50% | 12.33 |
| commons-csv | Ours (gpt-4o, FIX_TRIES=5) | 6 | 100.00% | 62.32% | 100.00% | 33.33% | 100.00% | 76.50% | 73.00% | 70.00% | 65.50% | 11.50 |
| commons-csv | Authors mean | 6 | 100.00% | 62.13% | 100.00% | 50.00% | 96.67% | 67.23% | 59.77% | 56.47% | 48.20% | 7.40 |
| datafaker | Ours (FIX_TRIES=3) | 6 | 73.26% | 67.44% | 83.33% | 50.00% | 83.33% | 69.17% | 64.17% | 42.67% | 40.17% | 14.33 |
| datafaker | Ours (FIX_TRIES=5) | 6 | 83.58% | 37.31% | 83.33% | 16.67% | 66.67% | 72.50% | 68.83% | 52.83% | 45.50% | 11.17 |
| datafaker | Ours (gpt-4o, FIX_TRIES=5) | 6 | 100.00% | 89.53% | 100.00% | 50.00% | 100.00% | 71.00% | 69.67% | 68.33% | 65.50% | 14.33 |
| datafaker | Authors mean | 6 | 100.00% | 88.06% | 100.00% | 66.67% | 100.00% | 77.70% | 71.73% | 63.57% | 57.77% | 9.63 |
| gson | Ours (FIX_TRIES=3) | 20 | 92.67% | 65.52% | 90.00% | 45.00% | 80.00% | 59.75% | 54.45% | 55.55% | 50.40% | 11.60 |
| gson | Ours (FIX_TRIES=5) | 20 | 81.45% | 49.32% | 75.00% | 25.00% | 60.00% | 40.55% | 36.45% | 35.40% | 30.90% | 11.05 |
| gson | Ours (gpt-4o, FIX_TRIES=5) | 20 | 91.59% | 66.37% | 95.00% | 55.00% | 70.00% | 48.30% | 45.65% | 43.90% | 42.00% | 11.30 |
| gson | Authors mean | 20 | 100.00% | 80.40% | 100.00% | 62.00% | 99.00% | 72.50% | 63.51% | 63.46% | 54.30% | 8.94 |
| jdom2 | Ours (FIX_TRIES=3) | 21 | 75.77% | 40.72% | 80.95% | 23.81% | 57.14% | 36.71% | 33.43% | 26.57% | 23.67% | 9.24 |
| jdom2 | Ours (FIX_TRIES=5) | 21 | 85.39% | 29.21% | 90.48% | 19.05% | 57.14% | 40.38% | 36.90% | 23.57% | 21.14% | 8.48 |
| jdom2 | Ours (gpt-4o, FIX_TRIES=5) | 21 | 100.00% | 59.61% | 100.00% | 42.86% | 90.48% | 46.95% | 41.71% | 38.29% | 34.19% | 12.14 |
| jdom2 | Authors mean | 21 | 100.00% | 73.94% | 100.00% | 57.14% | 91.43% | 61.07% | 53.80% | 49.94% | 43.43% | 7.97 |
| ruler | Ours (FIX_TRIES=3) | 16 | 67.92% | 25.47% | 75.00% | 18.75% | 18.75% | 16.38% | 12.12% | 6.81% | 4.81% | 6.62 |
| ruler | Ours (FIX_TRIES=5) | 16 | 71.25% | 35.00% | 75.00% | 18.75% | 37.50% | 18.38% | 12.81% | 9.06% | 5.56% | 10.00 |
| ruler | Ours (gpt-4o, FIX_TRIES=5) | 16 | 91.79% | 53.85% | 93.75% | 31.25% | 68.75% | 29.00% | 22.31% | 21.75% | 17.00% | 12.19 |
| ruler | Authors mean | 16 | 100.00% | 70.76% | 100.00% | 53.75% | 87.50% | 33.90% | 26.20% | 26.45% | 19.21% | 7.21 |
| windward | Ours (FIX_TRIES=3) | 2 | 32.00% | 24.00% | 50.00% | 0.00% | 50.00% | 49.00% | 47.00% | 49.00% | 45.50% | 12.50 |
| windward | Ours (FIX_TRIES=5) | 2 | 64.44% | 64.44% | 50.00% | 50.00% | 50.00% | 45.50% | 44.00% | 45.50% | 44.00% | 22.50 |
| windward | Ours (gpt-4o, FIX_TRIES=5) | 2 | 100.00% | 62.16% | 100.00% | 0.00% | 50.00% | 43.00% | 43.00% | 0.00% | 0.00% | 18.50 |
| windward | Authors mean | 2 | 100.00% | 98.46% | 100.00% | 90.00% | 100.00% | 63.80% | 59.30% | 63.70% | 59.00% | 9.20 |
| Total | Ours (FIX_TRIES=3) | 111 | 82.19% | 51.80% | 83.78% | 29.73% | 63.06% | 47.77% | 43.40% | 37.59% | 33.95% | 10.52 |
| Total | Ours (FIX_TRIES=5) | 111 | 85.71% | 47.25% | 85.59% | 31.53% | 61.26% | 44.27% | 39.30% | 32.26% | 27.90% | 10.47 |
| Total | Ours (gpt-4o, FIX_TRIES=5) | 111 | 94.18% | 63.98% | 95.50% | 42.34% | 81.98% | 52.83% | 47.96% | 45.77% | 40.60% | 12.53 |
| Total | Authors mean | 111 | 100.00% | 78.81% | 100.00% | 60.72% | 94.95% | 63.94% | 55.46% | 55.52% | 47.21% | 8.06 |

Time per task: run A 83.96 s, run B 150.04 s, run C 194.37 s, authors' archive 152.68 s (paper 152.68 s). Run C's extra time is test-subprocess timeouts in gson (§8.4), not model latency.

Gap closure from B to C, measured as the share of the B-to-authors gap removed by C: TC compile 59%, TC execution 53%, task compile 69%, task all-pass 37%, task any-pass 62%, instruction coverage 44%, correct instruction coverage 58%. Tests per class moved away from the authors (10.47 to 12.53 versus 8.06).

Headline totals of run C against the authors' five-repetition range: TC execution 63.98% (authors 71.39–97.17%), instruction coverage 52.83% (59.00–70.14%), correct instruction coverage 45.77% (53.03–59.19%), tests per class 12.53 (6.75–10.55). All four are outside the range, the first three below and the last above.

### 8.2 Position relative to the authors' repetition range (correct instruction coverage, per task)

| Run | Inside [min, max] of 5 reps | Below | Above |
|---|---:|---:|---:|
| A (FIX_TRIES=3) | 80 | 24 | 7 |
| B (FIX_TRIES=5) | 75 | 33 | 3 |
| C (gpt-4o, FIX_TRIES=5) | 68 | 23 | 20 |

Run C has fewer tasks inside the range than A or B, but the tasks outside it are distributed differently. Runs A and B lie almost only below the range (24 vs 7, 33 vs 3); run C is nearly symmetric (23 below, 20 above). The one-sided bias disappears with gpt-4o and the number of tasks above the authors' best repetition rises from 3–7 to 20.

Projects where our gap to the authors' mean exceeds the authors' own max-minus-min spread on at least two of the four checked metrics (test-case compile, test-case execution, correct instruction, correct branch): run A: batch-processing-gateway, commons-collections, jdom2, windward, datafaker; run B: batch-processing-gateway, commons-codec, commons-csv, gson, jdom2, commons-collections, datafaker, windward; run C: batch-processing-gateway, commons-collections, gson, jdom2, windward. commons-cli is never flagged; in run C commons-csv and datafaker are no longer flagged either. Full tables: `results/stage5/gap_analysis.md`, `results/stage5/gap_analysis_fixtries5.md`, `results/stage5/gap_analysis_gpt4o.md`.

### 8.3 Final classes that did not compile

Run A: 18 of 111. Run B: 16 of 111 (10 of the 18 fixed, 8 remaining, 8 new). Run C: 5 of 111. Authors: 0 in every repetition (0 of 555 task/repetition pairs; 0 of 90 for the 18 run-A tasks).

Classification of run A's 18 by the last remaining error (`results/stage5/compile_failures.md`):

| Category | Count | Examples |
|---|---:|---|
| H1 hallucinated type or member | 7 | `Headers` collection type in commons-csv; type variable `M` in windward; `JDOMFactory.createElement(String)` |
| H2 wrong use of an existing API | 6 | private `Snake` type used directly; extra constructor argument to `AnythingButEqualsIgnoreCase`; protected methods called through a base-typed field |
| S syntax / structure / import | 5 | `org.w3c.dom.Node` imported instead of `LinkedTreeMap.Node`; long literals above `Long.MAX_VALUE`; wrong package for `AppConfig.SparkCluster` |

In 13 of the 18 the remaining error sits outside any `@Test` method (helper, field, setup), where KTester's rule-based repair (deleting failing test methods) cannot act; only LLM repair can, and in these cases it did not converge within the budget.

Run B follow-up (`results/stage5/compile_failures_fixtries5.md`): all 18 run-A failures used all five repair rounds in run B; 10 then compiled (both batch-processing-gateway tasks, MurmurHash3, AbstractPatriciaTrie, LinkedTreeMap_find, three jdom2 tasks, two ruler tasks). New run-B failures: ApplicationSubmissionHelper_getDriverSpec, `$Gson$Types_equals`, Excluder_excludeField, ReflectiveTypeAdapterFactory_getBoundFields, TypeToken_isAssignableFrom, StringBin_locate, ByteMachine_addEndOfMatch, JsonRuleCompiler_processMatchExpression.

Run C follow-up (`results/stage5/compile_failures_gpt4o.md`): of the 26 tasks that failed in run A or B, 24 compile under gpt-4o. The 5 run-C failures, all after five repair rounds:

| Project | Task | Category | Location | Remaining error |
|---|---|---|---|---|
| batch-processing-gateway | SubmissionSummary_copyFrom | H1 | import | non-existent `com.apple.spark.operator.Metadata` type |
| commons-codec | MurmurHash3_hash128x64Internal | S | field | byte array initialised with out-of-range integer literals |
| commons-codec | Rule_pattern | S | field | final `Pattern` field never initialised |
| gson | JsonTreeReader_peek | S | helpers and `@Test` methods | same helper class declared three times; invariant generic assignments |
| ruler | ByteMachine_addEndOfMatch | H2 | helper | abstract `InputCharacter` instantiated directly |

Two of the five (Rule_pattern, JsonTreeReader_peek) compiled in both runs A and B. Category counts: H1 1, H2 1, S 3. As in run A, all remaining errors are in code outside or around the test methods, where rule-based deletion cannot reach.

### 8.4 Environment and runtime effects

- No compile failure involves a missing JUnit, Mockito or project dependency; the classpath is complete. The `_JAVA_OPTIONS` notice appears in both our and the authors' repair prompts.
- Single-test timeout. `tools/execute_test.py` gives each test-class subprocess 600 s (the authors' value, unchanged). Run B: the limit fired 12 times, all for `commons-codec/Sha2Crypt_sha2Crypt_Test` (11 during generation, 1 during coverage); commons-codec took 2 h 23 min of run B's 4 h 11 min. Run C: the limit fired 12 times again, now all in gson (`JsonReader_nextUnquotedValue_Test` 10, `JsonReader_doPeek_Test` 2) and none in commons-codec; gson took 2 h 43 min. The count is the same and the affected classes changed, so the model change moved this problem rather than removing it.
- `OutOfMemoryError` (Java heap space). Run B: 9 occurrences, all jdom2 SAX-output tests, plus 1 during coverage. Run C: 14 occurrences, gson `JsonReader_skipValue_Test` 6 and jdom2 `AbstractSAXOutputProcessor_printContent_Test` 8. In every case the error persisted through all available repair rounds. The JUnit JVM runs with its default heap (about 3 GB for a 12 GB container; no `-Xmx` is set anywhere in KTester).
- Two uncontrolled variables follow from this. (1) The authors' initial-structure prompt carries `@Timeout(600)` and ours does not, so our generated methods have no per-method JUnit timeout; a hanging method is deleted by `check_timeout_cases` instead of being counted as a failed test. (2) Neither side sets `-Xmx`, so the heap ceiling is a quarter of each machine's memory, and the authors' machine is unknown. Both are recorded as limitations. The logs do not show that the affected tasks would pass with a larger heap, so no correction is applied.
- Time per task in run B (150 s) matches the authors' (153 s); run A's 84 s reflects the smaller repair budget, not faster hardware. Run C's 194 s is inflated by the gson timeouts.

### 8.4a Evaluator defect: correct coverage reported as zero for passing tests

Symptom: a task has passing test cases and non-zero plain instruction coverage but `correct_inst_cov = 0`. Cause, established on windward `AntPathMatcher_isMatch` in run C (23 of 26 cases passing, plain instruction coverage 0.86, correct 0.00; full trace in `docs/handover-stage4c.md`): when the generated class puts test methods in nested test classes, `deal_execution_feedback` records only the method names and drops the nested-class owner, `run_project_test` builds every JUnit selector against the outer class, ConsoleLauncher rejects the selectors and prints its usage text, `run_selected_mehods` treats any return code other than -1 as success and discards stderr, and JaCoCo then writes a "correct" report from an empty execution-data file. This is the authors' evaluation code, unchanged by our port.

Frequency (`results/stage5/correct_coverage_zero_check.md`), counting tasks with `passed_cases > 0`, `inst_cov > 0`, `correct_inst_cov = 0`:

| Run | Affected tasks (of 111) |
|---|---:|
| A (gpt-4o-mini, 3) | 2 |
| B (gpt-4o-mini, 5) | 0 |
| C (gpt-4o, 5) | 6 |
| Authors rep_1 / rep_2 / rep_3 / rep_4 / rep_5 | 0 / 4 / 0 / 5 / 2 (mean 2.2) |

The defect is present in the authors' own archive at a similar rate, so the published numbers contain the same zeros and the comparison is fair on both sides. Upper-bound correction (replace each affected task's correct coverage with its plain coverage; the true value is lower because correct coverage counts passing tests only): run C 45.77 → 48.33, authors' mean 55.52 → 56.63; the gap moves from 9.75 to 8.30 percentage points. The defect does not explain the gap. Run C's 6 affected tasks exceed the authors' worst repetition (5); gpt-4o may use nested test classes more often, but the counts overlap the repetition spread and no conclusion is drawn.

### 8.5 Response-format observations

- One exception-case response in the trial was `{"result": []}` (7 output tokens); KTester treats it as "no exception cases" without retry.
- gson `ObjectTypeAdapter_read` (run A): the io-case response was malformed JSON; KTester logs the parse failure and proceeds with the other perspectives, without retrying.
- Several repair responses could not be parsed as a class (QuotedPrintableCodec, AbstractSAXOutputProcessor, Verifier_isXMLExtender, SparkPodSpec_copyFrom, Ruler_matches); the previous version of the class is kept in those rounds.

## 9. Interpretation

1. Executability: the released package runs end to end once ported to Linux. Prompts, static-analysis inputs and pipeline structure are the same as the authors'. The port did not change behaviour: the trial prompts were compared byte-for-byte.
2. Reported numbers: the paper's CPR, LC and BC are reproduced exactly from the authors' own archive, confirming what the metrics are; EPR and tests-per-class are not, and the paper's 110 tasks versus the archive's 111 is unexplained.
3. Fresh runs: with the paper's model name and, in run B, the paper's repair budget, our results are consistently and substantially below the archive: test-case execution 47–52% vs 79%, instruction coverage 44–48% vs 64%, correct instruction coverage 32–38% vs 56%, and 16–18 non-compiling classes vs 0. The gap is not driven by the repair budget (run B vs A), not by environment (no classpath issue; timeouts and OOM affect execution only), and not by prompts (identical). It appears in every project but commons-cli.
4. The remaining candidate after runs A and B was the model. Three observations pointed that way: the archive directory is named `KTester-gpt4o`; the archive's tests are fewer per class (8.1) and more often correct than ours (10.5), a pattern typical of a stronger model; gpt-4o-mini has a single public snapshot (2024-07-18), so drift within gpt-4o-mini is unlikely. The paper text says gpt-4o-mini. Run C was made to test this.
5. Run C (gpt-4o, paper configuration): the model is the main factor in the gap. Every quality metric moved roughly halfway to the authors' mean (37–69% of the run-B gap removed), non-compiling classes fell from 16–18 to 5, and the per-task position relative to the authors' range changed from one-sided (below) to symmetric. Raising the repair budget had moved compile pass by 3.5 points; changing the model moved it by 8.5 points and execution pass by 16.7 points.
6. Run C does not reproduce the archive. Test-case execution pass is still 14.8 points below the authors' mean and outside their five-repetition range, as are instruction coverage and correct instruction coverage. Zero non-compiling classes was not reproduced (5 remain). Tests per class went the other way: 12.53 against 8.06, further from the authors than gpt-4o-mini's 10.5. If the archive had been produced by gpt-4o under this pipeline, tests per class would be expected to move toward 8, not away from it. The evidence therefore supports "a stronger model than gpt-4o-mini explains most of the gap" but not "the archive was produced by gpt-4o".
7. Candidates examined and closed: repair budget (run B), prompts (drift check), classpath and dependencies (§8.4), evaluator correct-coverage defect (§8.4a; present on both sides, corrects the gap by 1.5 points at most). Candidates that remain uncontrolled: the `@Timeout(600)` annotation, the JVM heap ceiling, and any difference between the model that produced the archive and the models we ran.
8. What the replication yields for our own work: the repair loop's structure (one LLM call per round, otherwise scripted deletion and commenting) and a consistent failure class it cannot fix. In all three runs the final compile errors are hallucinated or misused project-local types and members in helper code, fields and setup, outside the reach of method-level deletion; and resource-exhausting tests (timeouts, out-of-memory) persist through every repair round with either model. Both observations bear directly on the repair stage of our stale-test experiments.

## 10. Operational record and deviations

- Stage 3 trial (2 tasks) was run before the full runs; its outputs are in `results/stage3-commons-cli/`.
- Run A, commons-cli: the first attempt failed within 15 s because the run-preparation step had removed `evaluation/commons-cli/`, whose per-task directory structure is created only by `preparation.set_file_structure()`. The structure was recreated and commons-cli rerun once in full; the failed attempt is preserved in git history. Metrics use the rerun.
- Run B: no reruns; `evaluation/` was cleaned file-by-file so directories survived.
- Mechanical fixes noted during runs: host `/tmp` not visible in the container (mounted explicitly for the API check); `lxml` added to the image after the first coverage attempt failed on `bs4.FeatureNotFound`; `evaluation/summary-openai/` created because `MODEL` contains a slash; archived `*.log` files force-added past a global gitignore rule.
- `preparation.py -W` must not be rerun after initial setup: it runs `mvn clean` on projects with a `target/` directory, and gson cannot be recompiled by the authors' goal-only Maven invocation.
- Run C: the only configuration change from run B was `MODEL`. The run finished at 01:59 KST; the container exited with a spurious Bash read error after the last project had already archived its outputs, so nothing was rerun. `aggregate_compare.py` gained a `--4way` mode and `analyze_stage5.py` a `--gpt4o` mode; neither alters earlier Stage 5 artifacts. Cost was tracked against a $70 ceiling and finished at $23.70.
- Correct-coverage check: counted directly from `summary.json` files with a short script; the authors' rep_1 summaries sit one level deeper (`reports/gpt4o/summary.json`).

## 11. Open questions

1. Which model produced the authors' archive? Partly resolved: the gap is mostly explained by a stronger model than gpt-4o-mini, but gpt-4o under the released pipeline does not match the archive either (tests per class diverges). The archive's provenance remains unknown.
2. Which aggregation yields the paper's EPR 76.41% and 7.33 tests/class, given the archive yields 78.81% and 8.06?
3. Why 110 tasks in the paper text but 111 in the released data and archive?
4. Was `@Timeout(600)` part of the prompt in the paper's runs (the archive says yes; the released template says no)?
5. The evaluator's nested-test-class selector defect (§8.4a) affects the published correct-coverage figures; it should be reported to the authors.

## 12. Status

The replication stops here. Three full runs establish that the released package is executable, that its released configuration differs from the paper's in the repair budget, and that the archived results are not reproduced with the paper's model and only partly approached with gpt-4o. Further runs (more repetitions per configuration, other models) would narrow the remaining uncertainty but are not needed for our purpose, which was to understand the pipeline and to obtain the prompts and repair-loop behaviour for our own experiments (§9.8).

## 13. Artifact index

- `results/stage3-commons-cli/` trial outputs
- `results/stage4/<project>/` run A: prompts, responses, repair rounds, final classes, JaCoCo CSV/summary, logs; `results/stage4/summary_table.md`
- `results/stage4b/<project>/` run B, same layout; `results/stage4b/summary_table.md`
- `results/stage4c/<project>/` run C, same layout; `results/stage4c/summary_table.md`, `results/stage4c/model_check.txt`
- `results/stage5/` extraction inventory, per-rep and mean aggregates of the authors' archive, two-, three- and four-way comparison tables (project and task level), gap analyses (`gap_analysis*.md`), compile-failure classifications (`compile_failures*.md`), prompt drift check, environment-effects note, correct-coverage zero check
- `docker/` Dockerfile, compose file, runner and analysis scripts
- `docs/` stage handovers and agent instruction files
