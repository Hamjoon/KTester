{
    "test cases": [
        {
            "group": "renderOptions Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 0
                        },
                        {
                            "parameter": "options",
                            "value": "new Options()"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 1
                        },
                        {
                            "parameter": "descPad",
                            "value": 3
                        }
                    ],
                    "expected": "StringBuffer with formatted options",
                    "description": "Test rendering with zero width to see how it handles minimal width."
                },
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 74
                        },
                        {
                            "parameter": "options",
                            "value": "optionsWithSingleOption"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 5
                        },
                        {
                            "parameter": "descPad",
                            "value": 10
                        }
                    ],
                    "expected": "StringBuffer with formatted single option with increased padding",
                    "description": "Test rendering with a single option and increased left and description padding."
                },
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 100
                        },
                        {
                            "parameter": "options",
                            "value": "optionsWithMultipleOptions"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 1
                        },
                        {
                            "parameter": "descPad",
                            "value": 1
                        }
                    ],
                    "expected": "StringBuffer with formatted multiple options",
                    "description": "Test rendering with multiple options in the options list with a larger width."
                },
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 74
                        },
                        {
                            "parameter": "options",
                            "value": "optionsWithArgs"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 1
                        },
                        {
                            "parameter": "descPad",
                            "value": 1
                        }
                    ],
                    "expected": "StringBuffer with formatted options including arguments",
                    "description": "Test rendering with options that require arguments with minimal description padding."
                },
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 74
                        },
                        {
                            "parameter": "options",
                            "value": "optionsWithLongOptOnly"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 1
                        },
                        {
                            "parameter": "descPad",
                            "value": 5
                        }
                    ],
                    "expected": "StringBuffer with formatted long options only",
                    "description": "Test rendering with options that have long names only and increased description padding."
                },
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": 74
                        },
                        {
                            "parameter": "options",
                            "value": "optionsWithNullDescription"
                        },
                        {
                            "parameter": "leftPad",
                            "value": 0
                        },
                        {
                            "parameter": "descPad",
                            "value": 0
                        }
                    ],
                    "expected": "StringBuffer with formatted options, some having null descriptions",
                    "description": "Test rendering with options that have null descriptions and no padding."
                }
            ]
        }
    ]
}