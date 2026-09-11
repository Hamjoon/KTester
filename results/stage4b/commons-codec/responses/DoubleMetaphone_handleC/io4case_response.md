{
    "test cases": [
        {
            "group": "handleC method tests",
            "cases": [
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
                            "value": 1
                        }
                    ],
                    "expected": 1,
                    "description": "Test when the input value is 'C' at index 1, should append 'K' and return index 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIC"
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
                    "description": "Test when the input value is 'CIC' at index 0, should append 'S' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIE"
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
                    "description": "Test when the input value is 'CIE' at index 0, should append 'S' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIELO"
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
                    "description": "Test when the input value is 'CIELO' at index 0, should append 'S' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CC"
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
                    "description": "Test when the input value is 'CC' at index 0, should handle double 'CC' and return index 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CZECH"
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
                    "description": "Test when the input value is 'CZECH' at index 0, should append 'S' and 'X' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CQUICK"
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
                    "description": "Test when the input value is 'CQUICK' at index 0, should append 'K' and return index 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CIGAR"
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
                    "description": "Test when the input value is 'CIGAR' at index 0, should append 'S' and return index 2."
                }
            ]
        }
    ]
}