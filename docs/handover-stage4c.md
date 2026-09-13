# Stage 4c handover: gpt-4o with the paper configuration

## Model and provenance

The only KTester configuration change from Stage 4b was `MODEL = "openai/gpt-4o"`. The API check returned valid JSON and reported `openai/gpt-4o`. Gary confirmed the matching OpenRouter log entry showed Model = GPT-4o and Provider = OpenAI at 2026-09-12 20:07 KST.

`results/stage4c/model_check.txt` contains:

```text
settings (grep):
13:    MODEL = "openai/gpt-4o"
20:    TEMPERATURE = 0.5
24:    CASES_LIST = []
30:    MAX_WORKERS = 8
31:    FIX_TRIES = 5

api_check response model string: openai/gpt-4o
OpenRouter log confirmed by Gary: Model = GPT-4o, Provider = OpenAI, 2026-09-12 20:07 KST
```

## Run window, completeness, and API status

- First project start: 2026-09-12 11:24:11 UTC / 2026-09-12 20:24:11 KST.
- Last project completion: 2026-09-12 16:59:18 UTC / 2026-09-13 01:59:18 KST.
- Project run window: 5 h 35 m 07 s.
- All ten projects have `generate_rc=0`, `coverage_rc=0`, and every expected final test class: 111 of 111 task ids.
- No project restart or targeted rerun was needed. Therefore there are no rerun rate-limit classifications.
- The final audit found no HTTP 402, HTTP 429, `RateLimit`, or `insufficient credits` marker in any Stage 4c generate log.
- The global `evaluation/summary-openai/gpt-4o.json` covers the full run because no interruption/resume occurred.
- The container exited with code 2 at 2026-09-12 16:59:25 UTC after `DONE jdom2`, without printing `ALL DONE`, because Bash reported `/work/docker/run_all.sh: error reading input file: No such file or directory`. The host script still existed unchanged. Since the last project had already archived a zero/zero `run.json` and all 21 expected jdom2 classes, there was no unfinished project to restart.

## Cost checkpoint and final usage

- Gary read $4.41 of OpenRouter GPT-4o spend at about 2026-09-12 20:48 KST.
- At that moment 16 tasks were complete through `datafaker`, while the six-task `batch-processing-gateway` project was in progress. The reading therefore covered roughly 16-21 tasks, not only the 10 tasks complete when the checkpoint question was first asked.
- This is about $0.21-$0.28 per covered task and projects to about $23-$31 for 111 tasks on a simple linear basis. Because the first five projects were the smallest and commons-codec, gson, and jdom2 represented 59 of the 89 tasks then remaining, the operational expectation was $30-$45 total.
- The corrected projection was below the $70 ceiling, so the run continued. The $70 credit wall was monitored separately through the HTTP 402 rule and was not reached in the logs.
- TODO (Gary): Record OpenRouter GPT-4o input tokens, output tokens, and cost for 2026-09-12 20:24:11 KST through 2026-09-13 01:59:18 KST from the Activity dashboard filtered to GPT-4o.

## Four-way project comparison

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

### Total tests per class and time per task

| Side | Tests/class | Mean time/task (s) |
| --- | ---: | ---: |
| Ours (gpt-4o, FIX_TRIES=5) | 12.53 | 194.37 |
| Authors mean | 8.06 | 152.68 |

## Gap analysis

Correct-instruction-coverage position against the authors' five-repetition task-level range:

- gpt-4o-mini, FIX_TRIES=3: inside 80, below 24, above 7 of 111.
- gpt-4o-mini, FIX_TRIES=5: inside 75, below 33, above 3 of 111.
- gpt-4o, FIX_TRIES=5: inside 68, below 23, above 20 of 111.

Projects whose absolute gap exceeded the authors' repetition spread, counting test-case compile, test-case execution, correct instruction coverage, and correct branch coverage:

- batch-processing-gateway: 2 of 4.
- commons-cli: 0 of 4.
- commons-codec: 1 of 4.
- commons-collections: 3 of 4.
- commons-csv: 0 of 4.
- datafaker: 0 of 4.
- gson: 3 of 4.
- jdom2: 2 of 4.
- ruler: 1 of 4.
- windward: 3 of 4.

## Compile failures and 26-task follow-up

The gpt-4o run has 5 non-compiling final classes, compared with 18 in Stage 4, 16 in Stage 4b, and 0 of 555 task/repetition outcomes in the authors' archive.

| Project | Task | Category | Failing location | Final remaining error | Highest repair prompt |
| --- | --- | --- | --- | --- | --- |
| batch-processing-gateway | SubmissionSummary_copyFrom | H1 | import | Imports the non-existent `com.apple.spark.operator.Metadata` type. | repair_prompt_4.md |
| commons-codec | MurmurHash3_hash128x64Internal | S | field | Initializes a byte array with integer literals outside the byte range without casts. | repair_prompt_4.md |
| commons-codec | Rule_pattern | S | field | Declares a final `Pattern` field without initializing it in a constructor. | repair_prompt_4.md |
| gson | JsonTreeReader_peek | S | helper classes and `@Test` methods | Declares the same `CustomSubclass` helper three times; test methods also assign invariant `Iterator<JsonPrimitive/JsonNull>` values to `Iterator<JsonElement>`. | repair_prompt_4.md |
| ruler | ByteMachine_addEndOfMatch | H2 | helper | Attempts to instantiate the existing abstract `InputCharacter` type directly. | repair_prompt_4.md |

Category counts: H1 1, H2 1, G 0, S 3, O 0.

| Project | Task | Compiles under gpt-4o | Highest repair prompt | Final error type |
| --- | --- | --- | --- | --- |
| batch-processing-gateway | ApplicationSubmissionHelper_getDriverSpec | yes | repair_prompt_4.md | none |
| batch-processing-gateway | ApplicationSubmissionHelper_getExecutorSpec | yes | repair_prompt_4.md | none |
| batch-processing-gateway | SubmissionSummary_copyFrom | no | repair_prompt_4.md | compile error |
| commons-codec | MurmurHash3_hash128x64Internal | no | repair_prompt_4.md | compile error |
| commons-collections | AbstractPatriciaTrie_nextEntryImpl | yes | repair_prompt_4.md | none |
| commons-collections | SequencesComparator_getMiddleSnake | yes | repair_prompt_2.md | none |
| commons-csv | CSVParser_createHeaders | yes | repair_prompt_4.md | none |
| datafaker | SqlTransformer_handlePrimitivesInArray | yes | repair_prompt_4.md | none |
| gson | $Gson$Types_equals | yes | repair_prompt_0.md | none |
| gson | Excluder_excludeField | yes | repair_prompt_4.md | none |
| gson | LinkedTreeMap_find | yes | repair_prompt_4.md | none |
| gson | LinkedTreeMap_rebalance | yes | repair_prompt_4.md | none |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | yes | repair_prompt_4.md | none |
| gson | TypeToken_isAssignableFrom | yes | repair_prompt_4.md | none |
| jdom2 | AbstractXMLOutputProcessor_printContent | yes | repair_prompt_4.md | none |
| jdom2 | StAXStreamBuilder_processPrunableElement | yes | repair_prompt_4.md | none |
| jdom2 | StringBin_locate | yes | repair_prompt_4.md | none |
| jdom2 | WalkerTRIM_analyzeMultiText | yes | repair_prompt_4.md | none |
| jdom2 | XPathHelper_getSingleStep | yes | repair_prompt_4.md | none |
| ruler | ByteMachine_addEndOfMatch | no | repair_prompt_4.md | compile error |
| ruler | ByteMachine_addRangePattern | yes | repair_prompt_4.md | none |
| ruler | ByteMachine_findMatchPattern | yes | repair_prompt_4.md | none |
| ruler | JsonRuleCompiler_processMatchExpression | yes | repair_prompt_4.md | none |
| ruler | RuleCompiler_processMatchExpression | yes | repair_prompt_4.md | none |
| ruler | Ruler_matches | yes | repair_prompt_4.md | none |
| windward | AbstractRouterGroup_matchRouter | yes | repair_prompt_4.md | none |

Of the prior 26-task union, 24 compile under gpt-4o. Two tasks compiled in both earlier runs and fail now: `commons-codec/Rule_pattern` and `gson/JsonTreeReader_peek`.

## Headline range conclusion

None of the four requested gpt-4o headline totals falls inside the authors' five-repetition range:

- Test-case execution pass: 63.98%, below the authors' 71.39%-97.17% range.
- Instruction coverage: 52.83%, below the authors' 59.00%-70.14% range.
- Correct instruction coverage: 45.77%, below the authors' 53.03%-59.19% range.
- Tests per class: 12.53, above the authors' 6.75-10.55 range.

The archive's zero-non-compiling-final-classes property was not reproduced: Stage 4c has 5 non-compiling final classes.

## Helper changes and mechanical notes

- The pre-aggregation grep found no hardcoded `gpt-4o-mini` model or summary filename in the three helper scripts. `aggregate_stage4.py` already accepted the results directory and did not require a change.
- `aggregate_compare.py` now has a `--4way` mode, reads `results/stage4c`, writes the Stage 4c aggregate plus the new four-way project/task tables, and avoids rewriting pre-existing Stage 5 artifacts.
- `analyze_stage5.py` now has a `--gpt4o` mode that applies the same task-range and compile-error rules to the Stage 4c run.
- The evaluator correctly wrote `evaluation/summary-openai/gpt-4o.json`; it was archived as `results/stage4c/gpt-4o.json`.
- `aggregate_stage4.py` requires `time/time_records.json`, so the documented operations were mechanically reordered: run the time evaluator and archive its output before invoking the aggregate script.
- The first API-check command was mechanically attempted from the repository root, where Compose has no configuration; it made no API call. The unchanged command was rerun successfully from `docker/`.
- A generic `git diff --check` rejected generated prompt/response whitespace before the first project commit. The generated artifacts were not altered and were committed as produced.
- `cp -r` emitted nonfatal `.DS_Store` "File exists" warnings for several archives. Each affected archive's summary, run record, and expected final classes were verified before commit; ignored `.DS_Store` files were not committed.
- `commons-codec/Sha2Crypt_sha2Crypt`, Gson test timeouts, and their coverage counterparts were allowed to finish without intervention. Gson took 2 h 43 m, and the full run still completed all projects.

## Final `code/settings.py` state

```text
13:    MODEL = "openai/gpt-4o"
20:    TEMPERATURE = 0.5
24:    CASES_LIST = []
30:    MAX_WORKERS = 8
31:    FIX_TRIES = 5
```
