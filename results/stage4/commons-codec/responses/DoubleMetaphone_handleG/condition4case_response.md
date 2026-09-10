{
    "testCases": [
        {
            "group": "handleG Method Tests",
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
                    "description": "Tests the case where the next character is 'H', which should invoke handleGH."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "KNOT"
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
                    "description": "Tests the case where the next character is 'N', and the previous character is a vowel, without slavoGermanic."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "LINDA"
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
                    "description": "Tests the case where the next characters are 'LI', and slavoGermanic is false."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "YELLOW"
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
                    "description": "Tests the case where the index is 0 and the next character is 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GERMAN"
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
                    "description": "Tests the case where the next characters are 'ER', and the previous characters are not 'DANGER', 'RANGER', 'MANGER'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "BIAGGI"
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
                    "description": "Tests the Italian case where the next character is 'I', and checks for 'VAN ', 'VON ', or 'SCH'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GAG"
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
                    "description": "Tests the case where the next character is 'G', which should increment index by 2."
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
                    "description": "Tests the default case where no conditions are met, incrementing index by 1."
                }
            ]
        }
    ]
}