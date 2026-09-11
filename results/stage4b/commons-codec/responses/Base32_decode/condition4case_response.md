{
    "testCases": [
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
                                "eof": false,
                                "modulus": 0,
                                "lbitWorkArea": 0,
                                "pos": 0
                            }
                        }
                    ],
                    "expected": "No exception, context.pos should be updated",
                    "description": "Test decoding with a valid input that does not hit EOF and has available bytes."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [0, 1, 2, 3, 4, 5, 6, 61] 		} 	],
                    "expected": "context.eof should be true",
                    "description": "Test decoding with a valid input that ends with padding character '='."
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
                            "value": -1
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
                    "expected": "context.eof should be true",
                    "description": "Test decoding with inAvail set to -1 to check EOF condition."
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
                            "value": 8
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": true,
                                "modulus": 2,
                                "lbitWorkArea": 0,
                                "pos": 0
                            }
                        }
                    ],
                    "expected": "No exception, context.pos should be updated and one byte should be output.",
                    "description": "Test decoding with modulus 2 to ensure proper handling of valid trailing characters."
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
                    "description": "Test decoding with modulus 3 to ensure exception is thrown for invalid trailing characters."
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
                                "modulus": 4,
                                "lbitWorkArea": 0,
                                "pos": 0
                            }
                        }
                    ],
                    "expected": "No exception, context.pos should be updated and two bytes should be output.",
                    "description": "Test decoding with modulus 4 to ensure proper handling of valid trailing characters."
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
                                "modulus": 5,
                                "lbitWorkArea": 0,
                                "pos": 0
                            }
                        }
                    ],
                    "expected": "No exception, context.pos should be updated and three bytes should be output.",
                    "description": "Test decoding with modulus 5 to ensure proper handling of valid trailing characters."
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
                    "description": "Test decoding with modulus 6 to ensure exception is thrown for invalid trailing characters."
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