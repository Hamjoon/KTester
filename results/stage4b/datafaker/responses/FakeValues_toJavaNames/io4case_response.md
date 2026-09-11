{
    "testCases": [
        {
            "group": "toJavaNames - Single Underscore",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "single_underscore"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "SingleUnderscore",
                    "description": "Test with a string containing a single underscore, expecting the output to be converted to camel case with the first letter uppercase."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "single_underscore"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "singleUnderscore",
                    "description": "Test with a string containing a single underscore for a method, expecting the output to be converted to camel case with the first letter lowercase."
                }
            ]
        },
        {
            "group": "toJavaNames - All Uppercase",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "ALLUPPERCASE"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "Alluppercase",
                    "description": "Test with an all-uppercase string, expecting the first letter to be uppercase and the rest to be lowercase."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "ALLUPPERCASE"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "alluppercase",
                    "description": "Test with an all-uppercase string for a method, expecting the first letter to be lowercase and the rest to be lowercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Mixed Case with Underscores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "Mixed_Case_With_Underscores"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "MixedCaseWithUnderscores",
                    "description": "Test with a mixed case string containing underscores, expecting the output to be converted to camel case with the first letter uppercase."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "Mixed_Case_With_Underscores"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "mixedCaseWithUnderscores",
                    "description": "Test with a mixed case string containing underscores for a method, expecting the output to be converted to camel case with the first letter lowercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "special@character_test"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "Special@characterTest",
                    "description": "Test with a string containing special characters, expecting the output to retain special characters with the first letter uppercase."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "special@character_test"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "special@characterTest",
                    "description": "Test with a string containing special characters for a method, expecting the output to retain special characters with the first letter lowercase."
                }
            ]
        },
        {
            "group": "toJavaNames - Consecutive Underscores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "consecutive__underscores"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "ConsecutiveUnderscores",
                    "description": "Test with a string containing consecutive underscores, expecting the output to remove underscores and capitalize the following character."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "consecutive__underscores"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "consecutiveUnderscores",
                    "description": "Test with a string containing consecutive underscores for a method, expecting the output to remove underscores and keep the first letter lowercase."
                }
            ]
        }
    ]
}