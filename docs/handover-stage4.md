# Stage 4 handover: full KTester run

## Run identity and scope

- Branch: `experiment/2026-09-week2-ktester-replication`
- Dataset scope: 10 projects, 111 focal methods
- Configured model: `openai/gpt-4o-mini`
- Temperature: `0.5`
- OpenRouter provider: OpenAI, pinned with fallbacks disabled
- Provider verification: Gary confirmed the OpenRouter log for the 07:56 PM KST smoke call showed Provider = OpenAI, with 26 input and 15 output tokens
- Main batch window: `2026-09-10T11:02:10Z` through `2026-09-10T13:08:43Z` (`2026-09-10 20:02:10` through `22:08:43` KST)
- Commons CLI recovery window: `2026-09-10T13:13:10Z` through `2026-09-10T13:15:49Z` (`2026-09-10 22:13:10` through `22:15:49` KST)
- Overall window including recovery: 2 hours, 13 minutes, 39 seconds
- Every final project run recorded `generate_rc=0` and `coverage_rc=0`
- Final completeness: 111 of 111 expected test classes were present

The full archived output is under `results/stage4/`. The JaCoCo HTML directories remain ignored, while prompts, responses, repairs, Java tests, CSV and JSON reports, timing data, and logs are committed. Each project archive was committed when its run completed. The one commons-cli rerun was committed separately, preserving its failed first attempt in Git history.

## Aggregate results

This table is copied verbatim from `results/stage4/summary_table.md`. Compile and execution pass counts are generated test cases, while coverage means are focal-method instruction and branch coverage from each `reports/summary.json`.

| Project | Tasks | Test classes | Compile pass | Execution pass | Mean instruction coverage | Mean branch coverage | Mean correct instruction coverage | Mean correct branch coverage | Mean time/task (s) |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| batch-processing-gateway | 6 | 6 | 40/60 (66.67%) | 25/60 (41.67%) | 49.33% | 45.83% | 31.33% | 28.83% | 77.09 |
| commons-cli | 2 | 2 | 41/41 (100.00%) | 26/41 (63.41%) | 98.50% | 96.00% | 82.00% | 69.00% | 86.53 |
| commons-codec | 18 | 18 | 211/218 (96.79%) | 105/218 (48.17%) | 61.94% | 57.50% | 47.72% | 45.33% | 132.24 |
| commons-collections | 14 | 14 | 126/153 (82.35%) | 110/153 (71.90%) | 47.43% | 40.43% | 41.93% | 36.29% | 53.15 |
| commons-csv | 6 | 6 | 37/53 (69.81%) | 17/53 (32.08%) | 48.17% | 47.50% | 40.50% | 36.67% | 85.96 |
| datafaker | 6 | 6 | 63/86 (73.26%) | 58/86 (67.44%) | 69.17% | 64.17% | 42.67% | 40.17% | 57.08 |
| gson | 20 | 20 | 215/232 (92.67%) | 152/232 (65.52%) | 59.75% | 54.45% | 55.55% | 50.40% | 86.11 |
| jdom2 | 21 | 21 | 147/194 (75.77%) | 79/194 (40.72%) | 36.71% | 33.43% | 26.57% | 23.67% | 85.56 |
| ruler | 16 | 16 | 72/106 (67.92%) | 27/106 (25.47%) | 16.37% | 12.13% | 6.81% | 4.81% | 65.13 |
| windward | 2 | 2 | 8/25 (32.00%) | 6/25 (24.00%) | 49.00% | 47.00% | 49.00% | 45.50% | 70.11 |
| **Total** | 111 | 111 | 960/1168 (82.19%) | 605/1168 (51.80%) | 47.77% | 43.40% | 37.59% | 33.95% | 83.96 |

### Missing test classes

- batch-processing-gateway: none
- commons-cli: none
- commons-codec: none
- commons-collections: none
- commons-csv: none
- datafaker: none
- gson: none
- jdom2: none
- ruler: none
- windward: none

### Final classes that did not compile

- batch-processing-gateway: SubmissionSummary_copyFrom, ApplicationSubmissionHelper_getExecutorSpec
- commons-cli: none
- commons-codec: MurmurHash3_hash128x64Internal
- commons-collections: SequencesComparator_getMiddleSnake, AbstractPatriciaTrie_nextEntryImpl
- commons-csv: CSVParser_createHeaders
- datafaker: SqlTransformer_handlePrimitivesInArray
- gson: LinkedTreeMap_find, LinkedTreeMap_rebalance
- jdom2: XPathHelper_getSingleStep, StAXStreamBuilder_processPrunableElement, AbstractXMLOutputProcessor_printContent, WalkerTRIM_analyzeMultiText
- ruler: Ruler_matches, ByteMachine_addRangePattern, RuleCompiler_processMatchExpression, ByteMachine_findMatchPattern
- windward: AbstractRouterGroup_matchRouter

## Per-project notes

- `batch-processing-gateway`: 6/6 test classes produced. `SubmissionSummary_copyFrom` and `ApplicationSubmissionHelper_getExecutorSpec` remained compile errors after KTester's normal repair processing. No project rerun.
- `commons-cli`: The first attempt ran from `11:02:10Z` to `11:02:25Z` with `generate_rc=1` and `coverage_rc=1`. Step 3 had removed `evaluation/commons-cli`, including the per-task directories normally created by `preparation.set_file_structure()`, so the attempt produced no completed test classes. Its logs contain missing-path errors and the coverage summary write traceback. After the main driver printed `ALL DONE`, the directory structure was recreated and the project received its one permitted full rerun. The rerun produced 2/2 classes, 41 test cases, no final compile error, and clean return codes.
- `commons-codec`: 18/18 classes produced. `MurmurHash3_hash128x64Internal` remained a compile error. Detached runner output also reported that two repair responses for `QuotedPrintableCodec_encodeQuotedPrintable` could not be parsed as classes; its final class was still produced and compiled. `Sha2Crypt_sha2Crypt` took much longer than other tasks, but no timeout was logged. No project rerun.
- `commons-collections`: 14/14 classes produced. `SequencesComparator_getMiddleSnake` and `AbstractPatriciaTrie_nextEntryImpl` remained compile errors. No project rerun.
- `commons-csv`: 6/6 classes produced. `CSVParser_createHeaders` remained a compile error, and `Lexer_parseEncapsulatedToken` was classified as an execution error. No project rerun.
- `datafaker`: 6/6 classes produced. `SqlTransformer_handlePrimitivesInArray` remained a compile error. No project rerun.
- `gson`: 20/20 classes produced. `LinkedTreeMap_find` and `LinkedTreeMap_rebalance` remained compile errors, and `ObjectTypeAdapter_read` was classified as an execution error. The `ObjectTypeAdapter_read` I/O case response was malformed JSON; parsing failed at line 10, column 28437. The pipeline saved the failed response, did not retry that JSON parse, and continued with the other case categories. No project rerun.
- `jdom2`: 21/21 classes produced. `XPathHelper_getSingleStep`, `StAXStreamBuilder_processPrunableElement`, `AbstractXMLOutputProcessor_printContent`, and `WalkerTRIM_analyzeMultiText` remained compile errors. `AbstractSAXOutputProcessor_printContent` was classified as an execution error. Detached runner output reported two repair responses for `AbstractSAXOutputProcessor_printContent` that could not be parsed as classes and one `Verifier_isXMLExtender` repair response without a class declaration; final classes were still produced for both tasks. No project rerun.
- `ruler`: 16/16 classes produced. `Ruler_matches`, `ByteMachine_addRangePattern`, `RuleCompiler_processMatchExpression`, and `ByteMachine_findMatchPattern` remained compile errors. No project rerun.
- `windward`: 2/2 classes produced. `AbstractRouterGroup_matchRouter` remained a compile error. No project rerun.

All projects logged intermediate `javac` failures while KTester attempted repairs. The list above records the final compile outcomes from the summary files. The log audit found no non-200 OpenRouter response, logged API transport retry, Maven failure, or actual JUnit timeout. Some failed-test stack traces include JUnit's `TimeoutExtension` frames, but no timeout exception or timeout return code was recorded.

## Seven-token Stage 3 response

The shortest Stage 3 response was `results/stage3-commons-cli/responses/PatternOptionBuilder_getValueType/exception4case_response.md`, at 20 bytes. It contains:

```json
{
    "result": []
}
```

This came from exception test-case generation. `FormattedTestcase.merge_test_cases` treated it as valid JSON representing no exception cases, added no group, and did not retry it.

## Mechanical fixes

1. Step 3 removed the two generated commons-cli test files left by Stage 3 so that all ten dataset project `src/test` paths began empty. The sibling Gson modules `gson/proto` and `gson/extras` were left unchanged.
2. Step 3 also removed `evaluation/time`, but generation writes `time/time_records.json` without recreating its parent. The empty `evaluation/time` directory was restored while the main driver continued; no generated project outputs or compiled classes were changed.
3. Removing `evaluation/commons-cli` also removed the project and per-task directory structure. After `run_all.sh` printed `ALL DONE`, the structure was recreated with `python -c "import preparation; preparation.set_file_structure()"`. `preparation.py -W` was not run, so it did not invoke Maven clean or wipe Gson's compiled classes. Commons CLI was then rerun in full once, as required.
4. The global Git ignore rule excludes `*.log`, so the required archived generation, coverage, and time logs were force-added to their result commits.
5. Incidental `.DS_Store` files created in the result tree were removed before committing artifacts.

## Code and experiment commits

- `df5992c` adds `lxml==6.1.3` to the Docker image.
- `9c055e1` pins OpenRouter routing to OpenAI with fallbacks disabled.
- `be05af0` adds the per-project runner.
- `6237900` adds the ordered full-run driver.
- `5c57d77` preserves the failed first commons-cli attempt.
- `0b93428`, `6c1d5bc`, `5350c14`, `899535a`, `58c820e`, `aa9cd01`, `e19bf8a`, `741ec72`, and `b2fced1` archive the successful main-run projects.
- `c3e7beb` archives the permitted commons-cli rerun.
- `ebb4313` adds the aggregation script.
- `c9283d4` archives the aggregate table and time records.

## Open questions for Gary

1. In Stage 5, should the commons-cli first-attempt infrastructure failure be included in the reproducibility narrative while the final metrics continue to use the permitted rerun?
2. Should the malformed Gson I/O-case JSON be categorized separately as an LLM response-format failure, since the pipeline did not retry the parse?
3. Should the final compile and execution errors remain unchanged as experimental outcomes for Stage 5 analysis, as required by the Stage 4 rerun rule?

The authors' RQ1 archive has not been opened or compared in this stage.
