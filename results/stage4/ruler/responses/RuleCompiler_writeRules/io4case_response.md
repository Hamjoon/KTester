{
    "testCases": [
        {
            "group": "writeRules",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rule",
                            "value": {}
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockParserWithMultipleStringValues"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rule contains 'testRule' with Patterns for multiple string values",
                    "description": "Test case where the parser points to a JSON array with multiple string values, expecting the rule to contain patterns for each string."
                },
                {
                    "input": [
                        {
                            "parameter": "rule",
                            "value": {}
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockParserWithMixedTypes"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rule contains 'testRule' with Patterns for mixed types including quoted strings",
                    "description": "Test case where the parser points to a JSON array with mixed types (strings, numbers, booleans), expecting the rule to handle each type appropriately with quoted strings."
                },
                {
                    "input": [
                        {
                            "parameter": "rule",
                            "value": {}
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockParserWithEmptyObject"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "JsonParseException with message 'Match value must be String, number, true, false, or null'",
                    "description": "Test case where the parser points to a JSON array with an empty object, expecting a JsonParseException."
                },
                {
                    "input": [
                        {
                            "parameter": "rule",
                            "value": {}
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockParserWithLargeNumberValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rule contains 'testRule' with Patterns for large numeric match",
                    "description": "Test case where the parser points to a JSON array with a large numeric value, expecting the rule to contain a pattern for the large number."
                },
                {
                    "input": [
                        {
                            "parameter": "rule",
                            "value": {}
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockParserWithSpecialCharacters"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rule contains 'testRule' with Patterns that has quoted special characters",
                    "description": "Test case where the parser points to a JSON array with strings containing special characters, expecting the rule to handle them correctly with quotes."
                }
            ]
        }
    ]
}