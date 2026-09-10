{
    "test cases": [
        {
            "group": "handleS method test cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SHELTER"
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
                    "expected": "result.getPrimary() == 'X' && result.getAlternate() == ''",
                    "description": "Test case for 'SH' followed by a non-germanic suffix. The result should append 'X'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SIEG"
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
                    "expected": "result.getPrimary() == 'S' && result.getAlternate() == ''",
                    "description": "Test case for 'SIEG' with slavoGermanic true. The primary should append 'S'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SILVER"
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
                    "expected": "result.getPrimary() == 'S' && result.getAlternate() == 'X'",
                    "description": "Test case for 'SILVER'. The result should append 'S' and 'X' for the anglicised case."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SCAR"
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
                    "description": "Test case for 'SC'. The handleSC method should be called, and index should be updated accordingly."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SNAKE"
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
                    "expected": "result.getPrimary() == 'S' && result.getAlternate() == 'X'",
                    "description": "Test case for 'SNAKE'. The result should append 'S' and 'X' for the anglicised case."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "S"
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
                    "expected": 1,
                    "description": "Test case for single character 'S' at index 1. The index should increment by 1."
                }
            ]
        }
    ]
}