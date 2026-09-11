{
    "testCases": [
        {
            "group": "handleG method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GHOST"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the next character is 'H'. It should call handleGH and return the updated index."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "KNIGHT"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the next character is 'N' and index is 1 with a vowel at index 0. It should append 'KN' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "NIGHT"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the next character is 'N' and the conditions for appending 'N' are met. It should append 'N' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "LION"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the next characters are 'LI' and slavoGermanic is false. It should append 'KL' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "YARD"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the next character is 'Y' at the start. It should append 'K' and 'J' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "DANGER"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the next character is 'E' and the condition for appending 'K' or 'J' is met. It should append 'K' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "AGGI"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 1
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the substring 'AGGI' is at index 1. It should append 'J' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GO"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the next character is 'G'. It should increment the index by 2 and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "G"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the next character is not 'G'. It should increment the index by 1 and return index 1."
                }
            ]
        }
    ]
}