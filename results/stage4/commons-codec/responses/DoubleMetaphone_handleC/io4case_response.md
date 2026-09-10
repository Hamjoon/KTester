{
    "test cases": [
        {
            "group": "handleC method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CICADA"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test the case where the input string contains 'CI' at index 0, expecting it to append 'S' since 'CIO' is not present."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CABBAGE"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test the case where the input string contains 'CC' at index 0, expecting it to append 'K' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CICERO"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test the case where the input string contains 'CI' at index 0, expecting it to append 'S' since 'CIO' is not present."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CATCH"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test the case where the input string contains 'C' followed by 'A' and 'T', expecting it to append 'K' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "C"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=1)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 1,
                    "description": "Test the case where the input string is a single character 'C' with max length 1, expecting it to append 'K' and increment index by 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CLOTH"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 1,
                    "description": "Test the case where the input string starts with 'C' and is followed by 'L', expecting it to append 'K' and increment index by 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CANDY"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 1,
                    "description": "Test the case where the input string starts with 'C' followed by a vowel, expecting it to append 'K' and increment index by 1."
                }
            ]
        }
    ]
}