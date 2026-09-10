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
