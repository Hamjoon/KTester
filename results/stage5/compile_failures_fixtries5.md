# Stage 5 compile-failure follow-up: FIX_TRIES=5

Of the 18 Stage 4 non-compiling tasks, 10 compile in Stage 4b and 8 remain non-compiling.
The repair round is reported as the highest `repair_prompt_N.md` present; its corresponding response is the last model repair applied before the final class.

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

## New non-compiling tasks in Stage 4b

- Count: 8.
- batch-processing-gateway: ApplicationSubmissionHelper_getDriverSpec.
- gson: $Gson$Types_equals.
- gson: Excluder_excludeField.
- gson: ReflectiveTypeAdapterFactory_getBoundFields.
- gson: TypeToken_isAssignableFrom.
- jdom2: StringBin_locate.
- ruler: ByteMachine_addEndOfMatch.
- ruler: JsonRuleCompiler_processMatchExpression.
