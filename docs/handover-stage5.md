# Stage 5 handover: comparison with published KTester results

## Extraction inventory

The archive contains all 5 KTester repetitions and all 10 dataset projects. All 50 project summaries exist. Each repetition contains the same 111 focal-method keys as `data/dataset_info.json`, with no missing or extra keys, and 111 final test classes. The archive therefore supports 111 tasks; it does not explain the paper's lower task count.

The actual layout differs from the initial description:

- Repetition 1 uses the extra `gpt4o/` level for nine projects, but `batch-processing-gateway` uses `reports/`, `test_classes/`, and `fix/` directly.
- Repetitions 2 through 5 use the shallower layout without `gpt4o/` for every project.

The requested summaries, final test classes, and repair artifacts were extracted. No bulk responses, contexts, or JaCoCo HTML were extracted. One KTester timing file exists, with 111 task records and a published mean of 152.68 seconds per task. Its detailed timing statistics and the full 50-project inventory are saved in `results/stage5/extraction_inventory.json` and `extraction_inventory.md`.

The archive directory is labeled `KTester-gpt4o`, while the paper identifies the model as gpt-4o-mini. The extracted artifacts do not settle that naming conflict.

## Same-definition comparison

The following is copied verbatim from `results/stage5/comparison_project_table.md`.

| Project | Side | Tasks | TC compile | TC execution | Task compile | Task all pass | Task any pass | Mean inst cov | Mean branch cov | Mean correct inst cov | Mean correct branch cov | Tests/class |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| batch-processing-gateway | Ours | 6 | 66.67% | 41.67% | 66.67% | 16.67% | 33.33% | 49.33% | 45.83% | 31.33% | 28.83% | 10.00 |
| batch-processing-gateway | Authors mean | 6 | 100.00% | 83.44% | 100.00% | 63.33% | 96.67% | 79.33% | 65.03% | 76.83% | 60.70% | 6.23 |
| commons-cli | Ours | 2 | 100.00% | 63.41% | 100.00% | 50.00% | 100.00% | 98.50% | 96.00% | 82.00% | 69.00% | 20.50 |
| commons-cli | Authors mean | 2 | 100.00% | 79.04% | 100.00% | 60.00% | 100.00% | 95.20% | 87.10% | 74.80% | 66.40% | 8.40 |
| commons-codec | Ours | 18 | 96.79% | 48.17% | 94.44% | 16.67% | 83.33% | 61.94% | 57.50% | 47.72% | 45.33% | 12.11 |
| commons-codec | Authors mean | 18 | 100.00% | 77.85% | 100.00% | 53.33% | 95.56% | 72.56% | 63.78% | 64.93% | 56.50% | 8.53 |
| commons-collections | Ours | 14 | 82.35% | 71.90% | 85.71% | 50.00% | 71.43% | 47.43% | 40.43% | 41.93% | 36.29% | 10.93 |
| commons-collections | Authors mean | 14 | 100.00% | 89.26% | 100.00% | 78.57% | 97.14% | 60.96% | 51.24% | 56.71% | 47.63% | 7.50 |
| commons-csv | Ours | 6 | 69.81% | 32.08% | 83.33% | 16.67% | 66.67% | 48.17% | 47.50% | 40.50% | 36.67% | 8.83 |
| commons-csv | Authors mean | 6 | 100.00% | 62.13% | 100.00% | 50.00% | 96.67% | 67.23% | 59.77% | 56.47% | 48.20% | 7.40 |
| datafaker | Ours | 6 | 73.26% | 67.44% | 83.33% | 50.00% | 83.33% | 69.17% | 64.17% | 42.67% | 40.17% | 14.33 |
| datafaker | Authors mean | 6 | 100.00% | 88.06% | 100.00% | 66.67% | 100.00% | 77.70% | 71.73% | 63.57% | 57.77% | 9.63 |
| gson | Ours | 20 | 92.67% | 65.52% | 90.00% | 45.00% | 80.00% | 59.75% | 54.45% | 55.55% | 50.40% | 11.60 |
| gson | Authors mean | 20 | 100.00% | 80.40% | 100.00% | 62.00% | 99.00% | 72.50% | 63.51% | 63.46% | 54.30% | 8.94 |
| jdom2 | Ours | 21 | 75.77% | 40.72% | 80.95% | 23.81% | 57.14% | 36.71% | 33.43% | 26.57% | 23.67% | 9.24 |
| jdom2 | Authors mean | 21 | 100.00% | 73.94% | 100.00% | 57.14% | 91.43% | 61.07% | 53.80% | 49.94% | 43.43% | 7.97 |
| ruler | Ours | 16 | 67.92% | 25.47% | 75.00% | 18.75% | 18.75% | 16.38% | 12.12% | 6.81% | 4.81% | 6.62 |
| ruler | Authors mean | 16 | 100.00% | 70.76% | 100.00% | 53.75% | 87.50% | 33.90% | 26.20% | 26.45% | 19.21% | 7.21 |
| windward | Ours | 2 | 32.00% | 24.00% | 50.00% | 0.00% | 50.00% | 49.00% | 47.00% | 49.00% | 45.50% | 12.50 |
| windward | Authors mean | 2 | 100.00% | 98.46% | 100.00% | 90.00% | 100.00% | 63.80% | 59.30% | 63.70% | 59.00% | 9.20 |
| Total | Ours | 111 | 82.19% | 51.80% | 83.78% | 29.73% | 63.06% | 47.77% | 43.40% | 37.59% | 33.95% | 10.52 |
| Total | Authors mean | 111 | 100.00% | 78.81% | 100.00% | 60.72% | 94.95% | 63.94% | 55.46% | 55.52% | 47.21% | 8.06 |

### Definition comparison with paper Table 2

- Paper: CPR 100.00%, EPR 76.41%, LC 63.94%, BC 55.46%, tests/class 7.33.
- Authors' summaries, test-case definition: CPR 100.00%, EPR 78.81%.
- Authors' summaries, task definition with all tests passing: CPR 100.00%, EPR 60.72%.
- Authors' summaries, task definition with at least one test passing: CPR 100.00%, EPR 94.95%.
- Authors' summary coverage fields: instruction 63.94%, branch 55.46%, correct instruction 55.52%, correct branch 47.21%, tests/class 8.06.
- Closest EPR definition: test-case execution at 78.81%; its absolute gap from 76.41% is 2.40 percentage points.

The archive reproduces the paper's CPR and coverage figures when summary fields are aggregated at test-case and task-mean level. Its EPR and tests/class figures remain different. Neither task-level EPR variant is close enough to explain the published EPR, so no definition is forced into a match.

## Gap analysis

For correct instruction coverage, our result is inside the authors' five-repetition range for 80 of 111 tasks, below it for 24, and above it for 7. The complete ten-worst and ten-best rankings are in `results/stage5/gap_analysis.md`.

The project-level gap exceeds the authors' own repetition spread for these metrics:

- `batch-processing-gateway`: test-case compile, test-case execution, correct instruction, and correct branch coverage.
- `commons-cli`: none.
- `commons-codec`: test-case compile.
- `commons-collections`: all four metrics.
- `commons-csv`: test-case compile.
- `datafaker`: test-case compile and execution.
- `gson`: test-case compile.
- `jdom2`: test-case compile, correct instruction, and correct branch coverage.
- `ruler`: test-case compile.
- `windward`: test-case compile and execution.

## Non-compiling final classes

This classification table is copied from `results/stage5/compile_failures.md`.

| Project | Task | Category | Failing location | Final remaining error | Last repair prompt | Authors non-compile reps |
| --- | --- | --- | --- | --- | --- | ---: |
| batch-processing-gateway | ApplicationSubmissionHelper_getExecutorSpec | S | import and field | Imports AppConfig.SparkCluster from the wrong package, leaving SparkCluster unresolved. | repair_prompt_2.md | 0/5 |
| batch-processing-gateway | SubmissionSummary_copyFrom | H1 | import and helper | References a non-existent com.apple.spark.operator.Metadata type. | repair_prompt_2.md | 0/5 |
| commons-codec | MurmurHash3_hash128x64Internal | S | @Test methods | Uses decimal long literals larger than Long.MAX_VALUE. | repair_prompt_2.md | 0/5 |
| commons-collections | AbstractPatriciaTrie_nextEntryImpl | S | helper | Uses the nested TrieEntry type without its AbstractPatriciaTrie qualifier. | repair_prompt_2.md | 0/5 |
| commons-collections | SequencesComparator_getMiddleSnake | H2 | helper and @Test methods | Uses the existing private Snake type directly and also references an undefined helper parameter. | repair_prompt_2.md | 0/5 |
| commons-csv | CSVParser_createHeaders | H1 | helper and @Test methods | Treats Headers as a collection and calls non-existent Headers and CSVFormat members. | repair_prompt_2.md | 0/5 |
| datafaker | SqlTransformer_handlePrimitivesInArray | H1 | setup | Uses non-existent DEFAULT enum constants; the same setup also misuses a private constructor. | repair_prompt_2.md | 0/5 |
| gson | LinkedTreeMap_find | S | helper | Imports org.w3c.dom.Node, so the generic LinkedTreeMap.Node return type resolves incorrectly. | repair_prompt_2.md | 0/5 |
| gson | LinkedTreeMap_rebalance | S | field and helper | Imports org.w3c.dom.Node, so generic field and helper parameter types resolve incorrectly. | repair_prompt_2.md | 0/5 |
| jdom2 | AbstractXMLOutputProcessor_printContent | H2 | helper and @Test methods | Calls protected output methods through a base-typed field; one helper also lacks the Arrays import. | repair_prompt_2.md | 0/5 |
| jdom2 | StAXStreamBuilder_processPrunableElement | H1 | @Test methods | Calls a non-existent JDOMFactory.createElement(String) method. | repair_prompt_2.md | 0/5 |
| jdom2 | WalkerTRIM_analyzeMultiText | H2 | field and setup | Uses the non-static inner MultiText type without an enclosing instance and calls the wrong FormatStack constructor. | repair_prompt_2.md | 0/5 |
| jdom2 | XPathHelper_getSingleStep | H2 | @Test methods | Stubs Parent and Content with incompatible return types and calls methods absent from Parent. | repair_prompt_2.md | 0/5 |
| ruler | ByteMachine_addRangePattern | H2 | setup and @Test methods | Passes double arrays where the existing Range constructor requires scalar doubles. | repair_prompt_2.md | 0/5 |
| ruler | ByteMachine_findMatchPattern | H1 | helper class | Invents an overridable getValue() method and a non-existent InputCharacterType.CHARACTER constant. | repair_prompt_2.md | 0/5 |
| ruler | RuleCompiler_processMatchExpression | H1 | @Test methods | Calls a non-existent Patterns.getPatternValue() member. | repair_prompt_2.md | 0/5 |
| ruler | Ruler_matches | H2 | @Test method | Calls the existing AnythingButEqualsIgnoreCase constructor with an extra boolean argument. | repair_prompt_2.md | 0/5 |
| windward | AbstractRouterGroup_matchRouter | H1 | field | Declares a field with the non-existent type variable M. | repair_prompt_2.md | 0/5 |

The category counts are H1 7, H2 6, S 5, G 0, and O 0. Thirteen of the 18 classes have at least one remaining error outside an `@Test` method, beyond the reach of KTester's rule-based test-method deletion. Across the authors' five repetitions, none of the same 90 task/repetition outcomes ended as a compile error.

The last repair prompt is the feedback that triggers the final repair response. For some tasks, the archived final class therefore differs from the code shown in `repair_prompt_2.md`. The classification uses the final class and its coverage-time compiler result while also inspecting the last prompt.

## Prompt drift

The expanded sample covers 3 projects, 28 tasks, and 112 conditional, I/O, exception, and generation prompt files. After normalizing line endings and removing blank lines, 87 files are identical, 25 differ only in line order, and none differ substantively. Every conditional, I/O, and exception prompt is identical. All order-only cases are generation prompts with the same normalized line multiset, consistent with member/list ordering.

All 28 author init prompts contain `@Timeout`; none of our 28 do. The per-file results are in `results/stage5/prompt_drift.md`.

## Environment and configuration effects

The final compiler feedback does not show a missing JUnit or Mockito package. All 18 failures concern generated source involving project-local types, members, imports, visibility, constructors, or literals. The extracted artifacts provide no evidence that the authors had a dependency absent from our classpath.

Both sides' repair prompts include the same `_JAVA_OPTIONS` language, country, and UTF-8 settings. Our two batch project failures carry Java 17 annotation-processor source warnings, and the authors' repair prompts carry the same warning in all five repetitions, so neither signal explains the compile-rate gap.

The repair budget is a material configuration difference: 96 of our tasks reached `repair_prompt_2.md`, and none reached `repair_prompt_4.md`; 380 author task/repetition pairs reached `repair_prompt_4.md`. The additional published-run repair opportunities can improve final compilation independently of the initial model response.

Timing is also environment-sensitive. The authors' saved mean is 152.68 seconds per task and ours is 83.96 seconds, but the runs may differ in hardware, API latency, and concurrency. The difference cannot be assigned solely to the model. The `@Timeout` annotation difference could affect long-running tests, although it does not explain compilation failures.

## Open questions for Gary

1. Which unpublished aggregation or filtering rule produced the paper's EPR and tests/class figures when the five archived summaries produce different values?
2. Should the final report identify the published model as gpt-4o-mini, preserve the archive's `KTester-gpt4o` label, or state both without choosing?
3. Should the final report call the matching 63.94% field instruction coverage, even though the paper labels it LC?
4. How should the different repair budgets be handled when attributing the compile-rate gap?

No projects were rerun and no LLM or API calls were made in Stage 5. The final report remains for Stage 6.
