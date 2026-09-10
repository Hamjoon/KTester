{
    "testCases": [
        {
            "group": "encode method - EOF handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [1, 2, 3, 4, 5]
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
                                "pos": 0,
                                "lbitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": "context.eof remains false",
                    "description": "Test that when inAvail is 0, the context.eof remains false."
                }
            ]
        },
        {
            "group": "encode method - processing based on modulus",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [1, 2, 3, 4, 5]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 5
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 0,
                                "lbitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": "correctly encodes based on modulus 0",
                    "description": "Test encoding when modulus is 0, ensuring the correct bytes are written to the buffer."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [1, 2, 3]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 3
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 0,
                                "lbitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": "correctly encodes based on modulus 0 with fewer bytes",
                    "description": "Test encoding when modulus is 0 with fewer than 5 bytes, ensuring no output is produced."
                }
            ]
        },
        {
            "group": "encode method - line separator handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [1, 2, 3, 4, 5]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 5
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 8,
                                "lbitWorkArea": 0,
                                "currentLinePos": 8
                            }
                        }
                    ],
                    "expected": "line separator is added to the buffer",
                    "description": "Test that when currentLinePos exceeds lineLength, a line separator is added."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [1, 2, 3, 4, 5]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 5
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 8,
                                "lbitWorkArea": 0,
                                "currentLinePos": 7
                            }
                        }
                    ],
                    "expected": "no line separator added to the buffer",
                    "description": "Test that when currentLinePos is less than lineLength, no line separator is added."
                }
            ]
        }
    ]
}