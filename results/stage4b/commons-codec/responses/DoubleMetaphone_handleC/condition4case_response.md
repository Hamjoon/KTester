{
    "testCases": [
        {
            "group": "handleC method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CAESAR"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the input value is 'CAESAR' at index 0, should append 'S' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Czerny"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the input value is 'Czerny' at index 0, should append 'S' and 'X' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "focaccia"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 3,
                    "description": "Test when the input value is 'focaccia' at index 0, should append 'X' and return index 3."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "McClelland"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 1
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the input value is 'McClelland' at index 1, should handle double 'CC' and return index 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIG"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 2,
                    "description": "Test when the input value is 'CIG' at index 0, should append 'S' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "C"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(10)"
                        },
                        {
                            "parameter": "index",
                            "value": 0
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the input value is 'C' at index 0, should append 'K' and return index 1."
                }
            ]
        }
    ]
}