| Project | Tasks | Test classes | Compile pass | Execution pass | Mean instruction coverage | Mean branch coverage | Mean correct instruction coverage | Mean correct branch coverage | Mean time/task (s) |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| batch-processing-gateway | 6 | 6 | 41/47 (87.23%) | 29/47 (61.70%) | 34.00% | 31.50% | 30.33% | 24.67% | 102.72 |
| commons-cli | 2 | 2 | 20/20 (100.00%) | 14/20 (70.00%) | 97.50% | 86.50% | 60.00% | 55.50% | 47.26 |
| commons-codec | 18 | 18 | 210/210 (100.00%) | 91/210 (43.33%) | 58.33% | 50.28% | 38.39% | 33.78% | 467.15 |
| commons-collections | 14 | 14 | 136/140 (97.14%) | 126/140 (90.00%) | 49.21% | 43.14% | 49.21% | 42.93% | 68.02 |
| commons-csv | 6 | 6 | 58/74 (78.38%) | 18/74 (24.32%) | 49.50% | 46.83% | 23.83% | 19.50% | 87.24 |
| datafaker | 6 | 6 | 56/67 (83.58%) | 25/67 (37.31%) | 72.50% | 68.83% | 52.83% | 45.50% | 67.64 |
| gson | 20 | 20 | 180/221 (81.45%) | 109/221 (49.32%) | 40.55% | 36.45% | 35.40% | 30.90% | 79.24 |
| jdom2 | 21 | 21 | 152/178 (85.39%) | 52/178 (29.21%) | 40.38% | 36.90% | 23.57% | 21.14% | 119.18 |
| ruler | 16 | 16 | 114/160 (71.25%) | 56/160 (35.00%) | 18.38% | 12.81% | 9.06% | 5.56% | 88.99 |
| windward | 2 | 2 | 29/45 (64.44%) | 29/45 (64.44%) | 45.50% | 44.00% | 45.50% | 44.00% | 70.95 |
| **Total** | 111 | 111 | 996/1162 (85.71%) | 549/1162 (47.25%) | 44.27% | 39.30% | 32.26% | 27.90% | 150.04 |

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
- batch-processing-gateway: ApplicationSubmissionHelper_getDriverSpec
- commons-cli: none
- commons-codec: none
- commons-collections: SequencesComparator_getMiddleSnake
- commons-csv: CSVParser_createHeaders
- datafaker: SqlTransformer_handlePrimitivesInArray
- gson: Excluder_excludeField, TypeToken_isAssignableFrom, LinkedTreeMap_rebalance, ReflectiveTypeAdapterFactory_getBoundFields, $Gson$Types_equals
- jdom2: StringBin_locate, XPathHelper_getSingleStep
- ruler: ByteMachine_addEndOfMatch, JsonRuleCompiler_processMatchExpression, ByteMachine_addRangePattern, ByteMachine_findMatchPattern
- windward: AbstractRouterGroup_matchRouter
