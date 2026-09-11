{
    "testCases": [
        {
            "group": "writeRules - Additional Valid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": [{}]
                        },
                        {
                            "parameter": "name",
                            "value": "testNumberPattern"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithNumberAndString"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rules contain both numeric and quoted string patterns",
                    "description": "Test case to verify that both numeric patterns and quoted string patterns are added correctly when valid data is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": [{}]
                        },
                        {
                            "parameter": "name",
                            "value": "testNullPattern"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithNullValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rules contain a pattern for null",
                    "description": "Test case to verify that null values are correctly processed and added to the rules."
                }
            ]
        },
        {
            "group": "writeRules - Additional Invalid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testInvalidString"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithInvalidString"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with a specific error message",
                    "description": "Test case to verify that an exception is thrown when an invalid string is encountered in the JSON."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testInvalidBoolean"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithInvalidBoolean"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with a specific error message",
                    "description": "Test case to verify that an exception is thrown when an invalid boolean value is encountered in the JSON."
                }
            ]
        },
        {
            "group": "writeRules - Additional Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testMixedTypes"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithMixedTypes"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rules contain patterns for mixed types",
                    "description": "Test case to verify that mixed types (strings, numbers, booleans) are correctly processed and added to the rules."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testEmptyRules"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithEmptyObject"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
                    "description": "Test case to verify that an exception is thrown when an empty object is provided."
                }
            ]
        }
    ]
}