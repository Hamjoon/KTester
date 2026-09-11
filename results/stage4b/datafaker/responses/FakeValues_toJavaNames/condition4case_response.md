{
    "test cases": [
        {
            "group": "toJavaNames - Null Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": null
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input string, expecting null as output."
                }
            ]
        },
        {
            "group": "toJavaNames - Empty String Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": ""
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "",
                    "description": "Test with empty string input, expecting empty string as output."
                }
            ]
        },
        {
            "group": "toJavaNames - No Underscores and Uppercase First Character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "TestString"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "TestString",
                    "description": "Test with a string that has no underscores and starts with an uppercase letter, expecting the same string as output."
                }
            ]
        },
        {
            "group": "toJavaNames - No Underscores and Lowercase First Character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "testString"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "testString",
                    "description": "Test with a string that has no underscores and starts with a lowercase letter for a method, expecting the same string as output."
                }
            ]
        },
        {
            "group": "toJavaNames - With Underscores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "test_string_example"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "TestStringExample",
                    "description": "Test with a string that contains underscores, expecting the output to be converted to camel case with the first letter uppercase."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "test_string_example"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "testStringExample",
                    "description": "Test with a string that contains underscores for a method, expecting the output to be converted to camel case with the first letter lowercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Leading Underscore",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "_leadingUnderscore"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "LeadingUnderscore",
                    "description": "Test with a string starting with an underscore, expecting the underscore to be removed and the following character to be uppercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Trailing Underscore",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "trailingUnderscore_"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "TrailingUnderscore",
                    "description": "Test with a string ending with an underscore, expecting the underscore to be ignored and the first character to be uppercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Multiple Underscores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "multiple__underscores_test"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "MultipleUnderscoresTest",
                    "description": "Test with a string containing multiple underscores, expecting each underscore to be removed and the following letter to be uppercase."
                }
            ]
        }
    ]
}