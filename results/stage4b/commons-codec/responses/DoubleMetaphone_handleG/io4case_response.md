{
    "test cases": [
        {
            "group": "handleG method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GEL"
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
                    "description": "Test when the next character is 'E' and the conditions for appending 'K' or 'J' are met. It should append 'K' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GIGI"
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
                    "description": "Test when the next character is 'I' and the substring 'GIGI' is present. It should append 'J' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GABRIEL"
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
                    "description": "Test when the next character is 'A' followed by 'B' and the conditions for handling are met. It should append 'K' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GANDHI"
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
                            "value": true
                        }
                    ],
                    "expected": 1,
                    "description": "Test with slavoGermanic set to true. It should increment the index by 1 for the next character and return index 1."
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
                            "value": true
                        }
                    ],
                    "expected": 1,
                    "description": "Test with a single character 'G' and slavoGermanic set to true. It should increment the index by 1 and return index 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GUSTAV"
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
                    "description": "Test when the next character is 'U' and the conditions for appending 'K' or 'J' are met. It should append 'K' and return index 2."
                }
            ]
        }
    ]
}