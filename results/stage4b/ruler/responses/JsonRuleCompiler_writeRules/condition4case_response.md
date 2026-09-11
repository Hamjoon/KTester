{
    "testCases": [
        {
            "group": "writeRules - Valid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithValidData"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rules contain the expected patterns",
                    "description": "Test case to verify that valid patterns are added to the rules list when valid JSON data is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithQuotedString"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": true
                        }
                    ],
                    "expected": "rules contain quoted patterns",
                    "description": "Test case to verify that patterns are correctly added with quotes when withQuotes is true."
                }
            ]
        },
        {
            "group": "writeRules - Invalid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithEmptyArray"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
                    "description": "Test case to verify that an exception is thrown when an empty array is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithInvalidToken"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with a specific error message",
                    "description": "Test case to verify that an exception is thrown when an invalid token is encountered in the JSON."
                }
            ]
        },
        {
            "group": "writeRules - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithNumber"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rules contain numeric patterns",
                    "description": "Test case to verify that numeric values are correctly processed and added to the rules."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": []
                        },
                        {
                            "parameter": "name",
                            "value": "testName"
                        },
                        {
                            "parameter": "parser",
                            "value": "mockJsonParserWithBoolean"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "rules contain boolean patterns",
                    "description": "Test case to verify that boolean values (true/false) are correctly processed and added to the rules."
                }
            ]
        }
    ]
}