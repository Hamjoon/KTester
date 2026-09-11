# Stage 5 compile-failure classification

The category describes the final class and its last compiler failure. Location records whether the remaining error was removable by KTester's rule-based deletion of a failing `@Test` method.

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

## Counts

- Total non-compiling final classes: 18.
- H1: 7.
- H2: 6.
- G: 0.
- S: 5.
- O: 0.
- Classes with at least one remaining error outside an `@Test` method: 13 of 18.
- Authors' non-compiling outcomes for these same task/repetition pairs: 0 of 90.

All final coverage-time compiler feedback included `_JAVA_OPTIONS=-Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8`. The two batch-processing-gateway failures also emitted annotation-processor source-level warnings, but those warnings were not the compile failures.

The final coverage compile output differs from some `repair_prompt_2.md` feedback because KTester applies `repair_response_2.md` after creating that prompt. Classifications above use the archived final class and the coverage evaluator's compile result; the last prompt was also inspected as required.
