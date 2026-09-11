{
    "group": "decode method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 7]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": true,
                        "modulus": 1,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "Exception should be raised for invalid trailing characters.",
            "description": "Test decoding with modulus 1 and EOF set to true to ensure exception is thrown for invalid trailing characters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 7]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": true,
                        "modulus": 3,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "Exception should be raised for invalid trailing characters.",
            "description": "Test decoding with modulus 3 and EOF set to true to ensure exception is thrown for invalid trailing characters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 7]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": true,
                        "modulus": 6,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "Exception should be raised for invalid trailing characters.",
            "description": "Test decoding with modulus 6 and EOF set to true to ensure exception is thrown for invalid trailing characters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 7]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": true,
                        "modulus": 0,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "No exception, context.pos should remain unchanged.",
            "description": "Test decoding with modulus 0 and EOF set to true to ensure no changes occur."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 8]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": false,
                        "modulus": 0,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "Exception should be raised for invalid character in input.",
            "description": "Test decoding with an input that contains an invalid character (8) which is out of the valid range."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 1, 2, 3, 4, 5, 6, 61]
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": false,
                        "modulus": 1,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "Exception should be raised for invalid trailing characters.",
            "description": "Test decoding with modulus 1 to ensure exception is thrown for invalid trailing characters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": []
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 0
                },
                {
                    "parameter": "context",
                    "value": {
                        "eof": false,
                        "modulus": 0,
                        "lbitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "No exception, context.pos should remain unchanged.",
            "description": "Test decoding with empty input to ensure no processing occurs."
        }
    ]
}