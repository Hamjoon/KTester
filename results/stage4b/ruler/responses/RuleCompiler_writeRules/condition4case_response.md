{
    "testCases": [
        {
            "group": "writeRules - Valid Cases",
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
                            "value": "mockedJsonParserWithValidObject"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rule contains the processed patterns",
                    "description": "Test case where parser returns valid objects, should populate the rule map with patterns."
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
                            "value": "mockedJsonParserWithStringValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rule contains the patterns with quotes",
                    "description": "Test case where parser returns a string value and withQuotes is true, should add quoted patterns."
                }
            ]
        },
        {
            "group": "writeRules - Exception Cases",
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
                            "value": "mockedJsonParserWithNullValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
                    "description": "Test case where parser returns an unexpected token, should throw an exception."
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
                            "value": "mockedJsonParserWithEmptyArray"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
                    "description": "Test case where parser indicates an empty array, should throw an exception."
                }
            ]
        },
        {
            "group": "writeRules - Edge Cases",
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
                            "value": "mockedJsonParserWithNumericValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rule contains numeric patterns",
                    "description": "Test case where parser processes numeric values, should add numeric patterns."
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
                            "value": "mockedJsonParserWithInvalidNumber"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rule contains patterns for invalid number handling",
                    "description": "Test case where parser encounters an invalid number, should handle the exception gracefully."
                }
            ]
        }
    ]
}