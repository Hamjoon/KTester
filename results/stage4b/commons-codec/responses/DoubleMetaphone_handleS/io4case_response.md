{
    "test cases": [
        {
            "group": "handleS - Special Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "isle"
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
                    "description": "Test the special case for 'isle' which should increment index to 2."
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
                            "value": "shiny"
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
                    "description": "Test handling 'SH' followed by a non-germanic suffix, should append 'X' and increment index to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "shmidt"
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
                            "value": "sian"
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
                    "expected": 3,
                    "description": "Test handling 'SIAN' without slavoGermanic, should append 'S' and 'X' and increment index to 3."
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
                            "value": "snider"
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
                    "description": "Test handling 'S' followed by 'N' (as in 'snider'), should append 'S' and increment index to 1."
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
                            "value": "scary"
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
                            "value": "s"
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
                    "description": "Test default case when the input is a single 'S', should append 'S' and increment index to 1."
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
                            "value": 3
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