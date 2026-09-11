{
    "test cases": [
        {
            "group": "handleS - Special Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "island"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test the special case for 'island' which should increment index to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "sugar"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test the special case for 'sugar' which should append 'X' and 'S' and increment index to 1."
                }
            ]
        },
        {
            "group": "handleS - Handling 'SH'",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "shelter"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test handling 'SH' followed by non-germanic suffix, should append 'X' and increment index to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "sholz"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": true
                        }
                    ],
                    "expected": 2,
                    "description": "Test handling 'SH' followed by a Germanic suffix, should append 'S' and increment index to 2."
                }
            ]
        },
        {
            "group": "handleS - Italian and Armenian Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "sio"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "expected": 3,
                    "description": "Test handling 'SIO' without slavoGermanic, should append 'S' and 'X' and increment index to 3."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "sian"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": true
                        }
                    ],
                    "expected": 3,
                    "description": "Test handling 'SIAN' with slavoGermanic, should append 'S' and increment index to 3."
                }
            ]
        },
        {
            "group": "handleS - German and Anglicisations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "smith"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test handling 'S' followed by 'M' (as in 'smith'), should append 'S' and increment index to 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "szmidt"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test handling 'SZ' leading to 'Z' (as in 'szmidt'), should append 'S' and increment index to 2."
                }
            ]
        },
        {
            "group": "handleS - Handling 'SC'",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "school"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test handling 'SC' which should call handleSC and increment index to 2."
                }
            ]
        },
        {
            "group": "handleS - Default Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "test"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test default case when no specific conditions are met, should append 'S' and increment index to 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "tests"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
                        },
                        {
                            "parameter": "index",
                            "value": 4
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": 5,
                    "description": "Test default case at the end of the string, should append alternate 'S' and increment index to 5."
                }
            ]
        }
    ]
}