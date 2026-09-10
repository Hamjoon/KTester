{
    "testCases": [
        {
            "group": "writeRules with valid input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser with START_OBJECT and VALUE_STRING tokens"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "rules contain testRule with expected patterns",
                    "description": "Test writeRules with valid rules list and JsonParser that returns START_OBJECT and VALUE_STRING tokens."
                }
            ]
        },
        {
            "group": "writeRules with empty rules",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser with END_ARRAY token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
                    "description": "Test writeRules with an empty rules list to ensure it throws an exception."
                }
            ]
        },
        {
            "group": "writeRules with null tokens",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser that returns VALUE_NULL token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "rules contain testRule with exact match for 'null'",
                    "description": "Test writeRules with a VALUE_NULL token to ensure it adds the correct pattern."
                }
            ]
        },
        {
            "group": "writeRules with boolean tokens",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser that returns VALUE_TRUE token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "rules contain testRule with exact match for 'true'",
                    "description": "Test writeRules with a VALUE_TRUE token to ensure it adds the correct pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser that returns VALUE_FALSE token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "rules contain testRule with exact match for 'false'",
                    "description": "Test writeRules with a VALUE_FALSE token to ensure it adds the correct pattern."
                }
            ]
        },
        {
            "group": "writeRules with number tokens",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser that returns VALUE_NUMBER_INT token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "rules contain testRule with numeric match and exact match for the number",
                    "description": "Test writeRules with a VALUE_NUMBER_INT token to ensure it adds both numeric and exact match patterns."
                }
            ]
        },
        {
            "group": "writeRules with invalid tokens",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "rules",
                            "value": "[{}]"
                        },
                        {
                            "parameter": "name",
                            "value": "testRule"
                        },
                        {
                            "parameter": "parser",
                            "value": "mocked JsonParser that returns an unexpected token"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": "false"
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
                    "description": "Test writeRules with an unexpected token to ensure it throws the appropriate exception."
                }
            ]
        }
    ]
}