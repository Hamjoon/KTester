{
    "test cases": [
        {
            "group": "decode method tests",
            "cases": [
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
                            "value": [0, 1, 2, 3, 4, 5, 6, 7]
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
                    "description": "Test decoding with inAvail set to 0 to check that no processing occurs."
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
                    "description": "Test decoding with context.eof set to true and modulus 0 to ensure no changes occur."
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
                                "modulus": 7,
                                "lbitWorkArea": 0,
                                "pos": 0
                            }
                        }
                    ],
                    "expected": "No exception, context.pos should be updated and four bytes should be output.",
                    "description": "Test decoding with modulus 7 to ensure proper handling of valid trailing characters."
                }
            ]
        }
    ]
}