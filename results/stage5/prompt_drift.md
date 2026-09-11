# Stage 5 prompt drift

Line endings were normalized and blank lines were removed before comparison. Files with the same multiset of normalized lines were classified as order-only differences.

## Summary

- Projects: 3.
- Tasks: 28.
- Prompt files compared: 112.
- Identical: 87.
- Order-only differences: 25.
- Substantive differences: 0.
- Authors' init prompts containing `@Timeout`: 28 of 28.
- Our init prompts containing `@Timeout`: 0 of 28.

All conditional, I/O, and exception prompts were identical. Every order-only difference was in a generation prompt and preserved the exact normalized line multiset, consistent with ordering changes inside dependency/member lists. There were no substantive differences to quote.

## Per-file results

| Project | Task | File | Result |
| --- | --- | --- | --- |
| windward | AbstractRouterGroup_matchRouter | condition4case_prompt.md | identical |
| windward | AbstractRouterGroup_matchRouter | io4case_prompt.md | identical |
| windward | AbstractRouterGroup_matchRouter | exception4case_prompt.md | identical |
| windward | AbstractRouterGroup_matchRouter | gencode_prompt.md | order-only differences |
| windward | AntPathMatcher_isMatch | condition4case_prompt.md | identical |
| windward | AntPathMatcher_isMatch | io4case_prompt.md | identical |
| windward | AntPathMatcher_isMatch | exception4case_prompt.md | identical |
| windward | AntPathMatcher_isMatch | gencode_prompt.md | order-only differences |
| commons-csv | CSVFormat_printWithQuotes | condition4case_prompt.md | identical |
| commons-csv | CSVFormat_printWithQuotes | io4case_prompt.md | identical |
| commons-csv | CSVFormat_printWithQuotes | exception4case_prompt.md | identical |
| commons-csv | CSVFormat_printWithQuotes | gencode_prompt.md | order-only differences |
| commons-csv | CSVFormat_toString | condition4case_prompt.md | identical |
| commons-csv | CSVFormat_toString | io4case_prompt.md | identical |
| commons-csv | CSVFormat_toString | exception4case_prompt.md | identical |
| commons-csv | CSVFormat_toString | gencode_prompt.md | order-only differences |
| commons-csv | CSVParser_createHeaders | condition4case_prompt.md | identical |
| commons-csv | CSVParser_createHeaders | io4case_prompt.md | identical |
| commons-csv | CSVParser_createHeaders | exception4case_prompt.md | identical |
| commons-csv | CSVParser_createHeaders | gencode_prompt.md | order-only differences |
| commons-csv | CSVParser_nextRecord | condition4case_prompt.md | identical |
| commons-csv | CSVParser_nextRecord | io4case_prompt.md | identical |
| commons-csv | CSVParser_nextRecord | exception4case_prompt.md | identical |
| commons-csv | CSVParser_nextRecord | gencode_prompt.md | order-only differences |
| commons-csv | Lexer_nextToken | condition4case_prompt.md | identical |
| commons-csv | Lexer_nextToken | io4case_prompt.md | identical |
| commons-csv | Lexer_nextToken | exception4case_prompt.md | identical |
| commons-csv | Lexer_nextToken | gencode_prompt.md | order-only differences |
| commons-csv | Lexer_parseEncapsulatedToken | condition4case_prompt.md | identical |
| commons-csv | Lexer_parseEncapsulatedToken | io4case_prompt.md | identical |
| commons-csv | Lexer_parseEncapsulatedToken | exception4case_prompt.md | identical |
| commons-csv | Lexer_parseEncapsulatedToken | gencode_prompt.md | order-only differences |
| gson | $Gson$Types_equals | condition4case_prompt.md | identical |
| gson | $Gson$Types_equals | io4case_prompt.md | identical |
| gson | $Gson$Types_equals | exception4case_prompt.md | identical |
| gson | $Gson$Types_equals | gencode_prompt.md | order-only differences |
| gson | $Gson$Types_getGenericSupertype | condition4case_prompt.md | identical |
| gson | $Gson$Types_getGenericSupertype | io4case_prompt.md | identical |
| gson | $Gson$Types_getGenericSupertype | exception4case_prompt.md | identical |
| gson | $Gson$Types_getGenericSupertype | gencode_prompt.md | order-only differences |
| gson | $Gson$Types_resolve | condition4case_prompt.md | identical |
| gson | $Gson$Types_resolve | io4case_prompt.md | identical |
| gson | $Gson$Types_resolve | exception4case_prompt.md | identical |
| gson | $Gson$Types_resolve | gencode_prompt.md | order-only differences |
| gson | ConstructorConstructor_newDefaultImplementationConstructor | condition4case_prompt.md | identical |
| gson | ConstructorConstructor_newDefaultImplementationConstructor | io4case_prompt.md | identical |
| gson | ConstructorConstructor_newDefaultImplementationConstructor | exception4case_prompt.md | identical |
| gson | ConstructorConstructor_newDefaultImplementationConstructor | gencode_prompt.md | order-only differences |
| gson | Excluder_excludeField | condition4case_prompt.md | identical |
| gson | Excluder_excludeField | io4case_prompt.md | identical |
| gson | Excluder_excludeField | exception4case_prompt.md | identical |
| gson | Excluder_excludeField | gencode_prompt.md | order-only differences |
| gson | JsonReader_doPeek | condition4case_prompt.md | identical |
| gson | JsonReader_doPeek | io4case_prompt.md | identical |
| gson | JsonReader_doPeek | exception4case_prompt.md | identical |
| gson | JsonReader_doPeek | gencode_prompt.md | order-only differences |
| gson | JsonReader_nextNonWhitespace | condition4case_prompt.md | identical |
| gson | JsonReader_nextNonWhitespace | io4case_prompt.md | identical |
| gson | JsonReader_nextNonWhitespace | exception4case_prompt.md | identical |
| gson | JsonReader_nextNonWhitespace | gencode_prompt.md | order-only differences |
| gson | JsonReader_nextUnquotedValue | condition4case_prompt.md | identical |
| gson | JsonReader_nextUnquotedValue | io4case_prompt.md | identical |
| gson | JsonReader_nextUnquotedValue | exception4case_prompt.md | identical |
| gson | JsonReader_nextUnquotedValue | gencode_prompt.md | order-only differences |
| gson | JsonReader_peek | condition4case_prompt.md | identical |
| gson | JsonReader_peek | io4case_prompt.md | identical |
| gson | JsonReader_peek | exception4case_prompt.md | identical |
| gson | JsonReader_peek | gencode_prompt.md | order-only differences |
| gson | JsonReader_peekNumber | condition4case_prompt.md | identical |
| gson | JsonReader_peekNumber | io4case_prompt.md | identical |
| gson | JsonReader_peekNumber | exception4case_prompt.md | identical |
| gson | JsonReader_peekNumber | gencode_prompt.md | order-only differences |
| gson | JsonReader_readEscapeCharacter | condition4case_prompt.md | identical |
| gson | JsonReader_readEscapeCharacter | io4case_prompt.md | identical |
| gson | JsonReader_readEscapeCharacter | exception4case_prompt.md | identical |
| gson | JsonReader_readEscapeCharacter | gencode_prompt.md | order-only differences |
| gson | JsonReader_skipValue | condition4case_prompt.md | identical |
| gson | JsonReader_skipValue | io4case_prompt.md | identical |
| gson | JsonReader_skipValue | exception4case_prompt.md | identical |
| gson | JsonReader_skipValue | gencode_prompt.md | order-only differences |
| gson | JsonTreeReader_peek | condition4case_prompt.md | identical |
| gson | JsonTreeReader_peek | io4case_prompt.md | identical |
| gson | JsonTreeReader_peek | exception4case_prompt.md | identical |
| gson | JsonTreeReader_peek | gencode_prompt.md | order-only differences |
| gson | LinkedTreeMap_find | condition4case_prompt.md | identical |
| gson | LinkedTreeMap_find | io4case_prompt.md | identical |
| gson | LinkedTreeMap_find | exception4case_prompt.md | identical |
| gson | LinkedTreeMap_find | gencode_prompt.md | order-only differences |
| gson | LinkedTreeMap_rebalance | condition4case_prompt.md | identical |
| gson | LinkedTreeMap_rebalance | io4case_prompt.md | identical |
| gson | LinkedTreeMap_rebalance | exception4case_prompt.md | identical |
| gson | LinkedTreeMap_rebalance | gencode_prompt.md | order-only differences |
| gson | ObjectTypeAdapter_read | condition4case_prompt.md | identical |
| gson | ObjectTypeAdapter_read | io4case_prompt.md | identical |
| gson | ObjectTypeAdapter_read | exception4case_prompt.md | identical |
| gson | ObjectTypeAdapter_read | gencode_prompt.md | identical |
| gson | Primitives_unwrap | condition4case_prompt.md | identical |
| gson | Primitives_unwrap | io4case_prompt.md | identical |
| gson | Primitives_unwrap | exception4case_prompt.md | identical |
| gson | Primitives_unwrap | gencode_prompt.md | identical |
| gson | Primitives_wrap | condition4case_prompt.md | identical |
| gson | Primitives_wrap | io4case_prompt.md | identical |
| gson | Primitives_wrap | exception4case_prompt.md | identical |
| gson | Primitives_wrap | gencode_prompt.md | identical |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | condition4case_prompt.md | identical |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | io4case_prompt.md | identical |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | exception4case_prompt.md | identical |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | gencode_prompt.md | order-only differences |
| gson | TypeToken_isAssignableFrom | condition4case_prompt.md | identical |
| gson | TypeToken_isAssignableFrom | io4case_prompt.md | identical |
| gson | TypeToken_isAssignableFrom | exception4case_prompt.md | identical |
| gson | TypeToken_isAssignableFrom | gencode_prompt.md | order-only differences |

## Init-prompt timeout annotation

| Project | Task | Authors `@Timeout` | Ours `@Timeout` |
| --- | --- | --- | --- |
| windward | AbstractRouterGroup_matchRouter | yes | no |
| windward | AntPathMatcher_isMatch | yes | no |
| commons-csv | CSVFormat_printWithQuotes | yes | no |
| commons-csv | CSVFormat_toString | yes | no |
| commons-csv | CSVParser_createHeaders | yes | no |
| commons-csv | CSVParser_nextRecord | yes | no |
| commons-csv | Lexer_nextToken | yes | no |
| commons-csv | Lexer_parseEncapsulatedToken | yes | no |
| gson | $Gson$Types_equals | yes | no |
| gson | $Gson$Types_getGenericSupertype | yes | no |
| gson | $Gson$Types_resolve | yes | no |
| gson | ConstructorConstructor_newDefaultImplementationConstructor | yes | no |
| gson | Excluder_excludeField | yes | no |
| gson | JsonReader_doPeek | yes | no |
| gson | JsonReader_nextNonWhitespace | yes | no |
| gson | JsonReader_nextUnquotedValue | yes | no |
| gson | JsonReader_peek | yes | no |
| gson | JsonReader_peekNumber | yes | no |
| gson | JsonReader_readEscapeCharacter | yes | no |
| gson | JsonReader_skipValue | yes | no |
| gson | JsonTreeReader_peek | yes | no |
| gson | LinkedTreeMap_find | yes | no |
| gson | LinkedTreeMap_rebalance | yes | no |
| gson | ObjectTypeAdapter_read | yes | no |
| gson | Primitives_unwrap | yes | no |
| gson | Primitives_wrap | yes | no |
| gson | ReflectiveTypeAdapterFactory_getBoundFields | yes | no |
| gson | TypeToken_isAssignableFrom | yes | no |
