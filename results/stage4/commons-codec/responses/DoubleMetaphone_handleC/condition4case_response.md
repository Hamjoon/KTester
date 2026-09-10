{
    "testCases": [
        {
            "group": "handleC method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CAESAR"
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
                    "description": "Test the case where the input string is 'CAESAR' and the index is 0, expecting it to append 'S' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CHICKEN"
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
                    "description": "Test the case where the input string contains 'CH' starting at index 0, expecting it to handle 'CH' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CZERNY"
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
                    "description": "Test the case where the input string contains 'CZ' but not 'WICZ', expecting it to append 'S' and 'X' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Focaccia"
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
                    "expected": 3,
                    "description": "Test the case where the input string contains 'CIA', expecting it to append 'X' and increment index by 3."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "McClelland"
                        },
                        {
                            "parameter": "result",
                            "value": "DoubleMetaphoneResult(maxLength=10)"
                        },
                        {
                            "parameter": "index",
                            "value": 1
                        }
                    ],
                    "expected": 2,
                    "description": "Test the case where the input string contains 'CC' at index 1, expecting it to skip handling due to 'M' and return index unchanged."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CK"
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
                    "description": "Test the case where the input string contains 'CK' at index 0, expecting it to append 'K' and increment index by 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIELO"
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
                            "value": "C"
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
                    "description": "Test the case where the input string is a single character 'C', expecting it to append 'K' and increment index by 1."
                }
            ]
        }
    ]
}