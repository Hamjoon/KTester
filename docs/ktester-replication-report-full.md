# KTester Replication Report (full)

Replication of *Knowledge Matters: Injecting Project and Testing Knowledge into LLM-based Unit Test Generation* (Li, Liu, Chen, Pei, Li, Dai, Wang, Zheng; ICSE 2026; arXiv 2511.14224; tool: KTester).
Period: 2026-09-10 to 2026-09-11. This is the authoritative record; the summary report is an excerpt of it. Every number here is produced by a script whose output is committed under `results/`.

Repository: KTester fork, branch `experiment/2026-09-week2-ktester-replication`. Stage handovers: `docs/handover-stage1-2.md`, `docs/handover-stage3.md`, `docs/handover-stage4.md`, `docs/handover-stage5.md`, `docs/handover-stage4b.md`. Agent instruction files: `docs/cc-instructions-stage*.md`.

## 1. Replication question and scope

Question: can the paper's main experiment (RQ1, Table 2, KTester row) be re-run from the released artifacts, and do the resulting numbers match the paper and the authors' own published result archive?

In scope: the KTester pipeline on the full dataset with the paper's model. Out of scope: re-running the four baseline tools (ChatUniTest, ChatTester, HITS, UTGen), the RQ2 ablations, and the other-model discussion.

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

LLM access: OpenRouter, model `openai/gpt-4o-mini`, provider pinned to OpenAI with fallbacks disabled (the first trial showed OpenRouter alternating between OpenAI and Azure). The response `model` field reported `openai/gpt-4o-mini`. JSON mode (`response_format={'type':'json_object'}`) was verified to pass through.

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

`code/settings.py` (gitignored): `MODEL = "openai/gpt-4o-mini"`, `TEMPERATURE = 0.5`, `PROMPT_LIST = ['condition','io','exception']`, `CASE_THEN_CODE = True`, `SIM_TOP_K = "10"`, `MAX_WORKERS = 8`, `REPETITION_NUM = 5` (unused by us). All are the template defaults except the model string and endpoint.

`FIX_TRIES`: the released template says 3. The paper says "We limited automatic repair iterations to 5 in KTester." The authors' archive contains `repair_prompt_4.md` files (a fifth round) for 380 task/repetition pairs. Run A used 3, run B used 5. `post_process.CodeRepairer` derives `half_tries` (the round from which failing test cases are commented out) from `max_tries`, so that threshold also moves from round 1 to round 2 between the runs; this is inherent to the paper's setting.

Prompt drift check (`results/stage5/prompt_drift.md`): for 30 tasks in commons-cli, windward, commons-csv and gson, all condition / io / exception prompts are byte-identical to the authors' after line-ending normalisation; generation prompts differ only in the order of list members; the authors' `init_prompt.md` contains `@Timeout(600)` in every case, ours never does (the released template lacks it).

## 6. Runs

| Run | FIX_TRIES | Window (KST) | Wall time | Tasks | Test classes | Reruns |
|---|---:|---|---|---:|---:|---|
| Trial (Stage 3) | 3 | 2026-09-10 19:32–19:34 | 99 s | 2 (commons-cli) | 2 | – |
| A (Stage 4) | 3 | 2026-09-10 20:02–22:16 | 2 h 14 min | 111 | 111 | commons-cli once (see §10) |
| B (Stage 4b) | 5 | 2026-09-11 12:21–16:32 | 4 h 11 min | 111 | 111 | none |

Projects were run in order commons-cli, windward, commons-csv, datafaker, batch-processing-gateway, commons-collections, ruler, commons-codec, gson, jdom2; each project's outputs were committed on completion (`results/stage4/<project>/`, `results/stage4b/<project>/`).

Token usage was read from the OpenRouter dashboard for the trial only: 13 generation calls, 34,533 input and 9,779 output tokens, $0.011. The full runs were not itemised; extrapolating by task count and prompt size puts each under $5.

## 7. Metric definitions

All numbers come from KTester's `reports/summary.json` (one per project), which has per-task fields `test_cases`, `passed_cases`, `inst_cov`, `bran_cov`, `correct_inst_cov`, `correct_bran_cov` and project-level `compile_pass_rate`, `execution_pass_rate`, `average_*`. The same script (`docker/aggregate_compare.py`) is applied to our runs and to each of the authors' five repetitions.

- Test-case level: compile pass = compiled test cases / generated; execution pass = passed / generated.
- Task level: a task compiles if its final class compiled; "all pass" if every generated case passed; "any pass" if at least one did.
- Coverage: focal-method instruction and branch coverage from JaCoCo; "correct" variants count only passing tests. Means are unweighted over tasks.

Matching to the paper (`results/stage5/comparison_project_table.md`): the authors' archive, mean over 5 repetitions, gives CPR 100.00, instruction 63.94, branch 55.46, i.e. the paper's CPR, LC and BC exactly. The paper's LC is therefore KTester's instruction coverage. EPR: test-case level 78.81 (paper 76.41), task all-pass 60.72, task any-pass 94.95; none matches exactly. Tests per class: archive 8.06, paper 7.33.

## 8. Results

### 8.1 Three-way comparison (verbatim from `results/stage5/comparison_project_table_3way.md`)

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

Time per task: run A 83.96 s, run B 150.04 s, authors' archive 152.68 s (paper 152.68 s).

### 8.2 Position relative to the authors' repetition range (correct instruction coverage, per task)

| Run | Inside [min, max] of 5 reps | Below | Above |
|---|---:|---:|---:|
| A (FIX_TRIES=3) | 80 | 24 | 7 |
| B (FIX_TRIES=5) | 75 | 33 | 3 |

Projects where our gap to the authors' mean exceeds the authors' own max-minus-min spread on at least two of the four checked metrics (test-case compile, test-case execution, correct instruction, correct branch): run A: batch-processing-gateway, commons-collections, jdom2, windward, datafaker; run B: batch-processing-gateway, commons-codec, commons-csv, gson, jdom2, commons-collections, datafaker, windward. commons-cli is never flagged. Full tables: `results/stage5/gap_analysis.md`, `results/stage5/gap_analysis_fixtries5.md`.

### 8.3 Final classes that did not compile

Run A: 18 of 111. Run B: 16 of 111 (10 of the 18 fixed, 8 remaining, 8 new). Authors: 0 in every repetition (0 of 90 task/repetition pairs for the 18 run-A tasks).

Classification of run A's 18 by the last remaining error (`results/stage5/compile_failures.md`):

| Category | Count | Examples |
|---|---:|---|
| H1 hallucinated type or member | 7 | `Headers` collection type in commons-csv; type variable `M` in windward; `JDOMFactory.createElement(String)` |
| H2 wrong use of an existing API | 6 | private `Snake` type used directly; extra constructor argument to `AnythingButEqualsIgnoreCase`; protected methods called through a base-typed field |
| S syntax / structure / import | 5 | `org.w3c.dom.Node` imported instead of `LinkedTreeMap.Node`; long literals above `Long.MAX_VALUE`; wrong package for `AppConfig.SparkCluster` |

In 13 of the 18 the remaining error sits outside any `@Test` method (helper, field, setup), where KTester's rule-based repair (deleting failing test methods) cannot act; only LLM repair can, and in these cases it did not converge within the budget.

Run B follow-up (`results/stage5/compile_failures_fixtries5.md`): all 18 run-A failures used all five repair rounds in run B; 10 then compiled (both batch-processing-gateway tasks, MurmurHash3, AbstractPatriciaTrie, LinkedTreeMap_find, three jdom2 tasks, two ruler tasks). New run-B failures: ApplicationSubmissionHelper_getDriverSpec, `$Gson$Types_equals`, Excluder_excludeField, ReflectiveTypeAdapterFactory_getBoundFields, TypeToken_isAssignableFrom, StringBin_locate, ByteMachine_addEndOfMatch, JsonRuleCompiler_processMatchExpression.

### 8.4 Environment and runtime effects

- No compile failure involves a missing JUnit, Mockito or project dependency; the classpath is complete. The `_JAVA_OPTIONS` notice appears in both our and the authors' repair prompts.
- `Sha2Crypt_sha2Crypt` (commons-codec): generated tests exceed the 600 s single-test timeout; in run B this repeated through all five repair rounds and again during coverage (commons-codec took 2 h 23 min of run B's 4 h 11 min). Counted as failed tests.
- jdom2 (run B): nine `OutOfMemoryError` occurrences during generation and one during coverage, in generated SAX-output tests; the JUnit JVM runs with default heap inside the 12 GB container. Counted as failed tests.
- The authors' `@Timeout(600)` in the initial-structure prompt may make generated classes fail fast on such cases; ours do not carry it.
- Time per task in run B (150 s) matches the authors' (153 s); run A's 84 s reflects the smaller repair budget, not faster hardware.

### 8.5 Response-format observations

- One exception-case response in the trial was `{"result": []}` (7 output tokens); KTester treats it as "no exception cases" without retry.
- gson `ObjectTypeAdapter_read` (run A): the io-case response was malformed JSON; KTester logs the parse failure and proceeds with the other perspectives, without retrying.
- Several repair responses could not be parsed as a class (QuotedPrintableCodec, AbstractSAXOutputProcessor, Verifier_isXMLExtender, SparkPodSpec_copyFrom, Ruler_matches); the previous version of the class is kept in those rounds.

## 9. Interpretation

1. Executability: the released package runs end to end once ported to Linux. Prompts, static-analysis inputs and pipeline structure are the same as the authors'. The port did not change behaviour: the trial prompts were compared byte-for-byte.
2. Reported numbers: the paper's CPR, LC and BC are reproduced exactly from the authors' own archive, confirming what the metrics are; EPR and tests-per-class are not, and the paper's 110 tasks versus the archive's 111 is unexplained.
3. Fresh runs: with the paper's model name and, in run B, the paper's repair budget, our results are consistently and substantially below the archive: test-case execution 47–52% vs 79%, instruction coverage 44–48% vs 64%, correct instruction coverage 32–38% vs 56%, and 16–18 non-compiling classes vs 0. The gap is not driven by the repair budget (run B vs A), not by environment (no classpath issue; timeouts and OOM affect execution only), and not by prompts (identical). It appears in every project but commons-cli.
4. The remaining candidate is the model. Three observations point the same way: the archive directory is named `KTester-gpt4o`; the archive's tests are fewer per class (8.1) and more often correct than ours (10.5), a pattern typical of a stronger model; gpt-4o-mini has a single public snapshot (2024-07-18), so drift within gpt-4o-mini is unlikely. The paper text says gpt-4o-mini. The artifacts do not resolve this; it is reported as an open discrepancy, not a conclusion.

## 10. Operational record and deviations

- Stage 3 trial (2 tasks) was run before the full runs; its outputs are in `results/stage3-commons-cli/`.
- Run A, commons-cli: the first attempt failed within 15 s because the run-preparation step had removed `evaluation/commons-cli/`, whose per-task directory structure is created only by `preparation.set_file_structure()`. The structure was recreated and commons-cli rerun once in full; the failed attempt is preserved in git history. Metrics use the rerun.
- Run B: no reruns; `evaluation/` was cleaned file-by-file so directories survived.
- Mechanical fixes noted during runs: host `/tmp` not visible in the container (mounted explicitly for the API check); `lxml` added to the image after the first coverage attempt failed on `bs4.FeatureNotFound`; `evaluation/summary-openai/` created because `MODEL` contains a slash; archived `*.log` files force-added past a global gitignore rule.
- `preparation.py -W` must not be rerun after initial setup: it runs `mvn clean` on projects with a `target/` directory, and gson cannot be recompiled by the authors' goal-only Maven invocation.

## 11. Open questions

1. Which model produced the authors' archive (`KTester-gpt4o` label vs gpt-4o-mini in the paper)?
2. Which aggregation yields the paper's EPR 76.41% and 7.33 tests/class, given the archive yields 78.81% and 8.06?
3. Why 110 tasks in the paper text but 111 in the released data and archive?
4. Was `@Timeout(600)` part of the prompt in the paper's runs (the archive says yes; the released template says no)?

## 12. Proposed next step

Run KTester with gpt-4o on a subset (commons-cli, commons-csv, gson; 28 tasks; estimated cost a few dollars) under the paper's configuration and compare with the authors' per-task range. If those numbers fall inside the archive range while gpt-4o-mini's do not, the model label is the explanation; if not, the discrepancy remains open and should be raised with the authors.

## 13. Artifact index

- `results/stage3-commons-cli/` trial outputs
- `results/stage4/<project>/` run A: prompts, responses, repair rounds, final classes, JaCoCo CSV/summary, logs; `results/stage4/summary_table.md`
- `results/stage4b/<project>/` run B, same layout; `results/stage4b/summary_table.md`
- `results/stage5/` extraction inventory, per-rep and mean aggregates of the authors' archive, two-way and three-way comparison tables (project and task level), gap analyses, compile-failure classifications, prompt drift check, environment-effects note
- `docker/` Dockerfile, compose file, runner and analysis scripts
- `docs/` stage handovers and agent instruction files
