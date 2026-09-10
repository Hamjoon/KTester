{
    "test_cases": [
        {
            "group": "Test encode method with empty input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
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
                                "pos": 0,
                                "ibitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [],
                        "context": {
                            "pos": 0,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test with empty input array, should return without processing and keep context unchanged."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail = 1",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [65]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 1
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 0,
                                "ibitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0, 0, 0],
                        "context": {
                            "pos": 2,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test with inAvail = 1, should process 1 byte and update context accordingly."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail = 2",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [65, 66]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 2
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 0,
                                "pos": 0,
                                "ibitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0, 0, 0],
                        "context": {
                            "pos": 3,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test with inAvail = 2, should process 2 bytes and update context accordingly."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail = 3",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [65, 66, 67]
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
                                "ibitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0, 0, 0],
                        "context": {
                            "pos": 4,
                            "currentLinePos": 4
                        }
                    },
                    "description": "Test with inAvail = 3, should process all bytes and update context accordingly."
                }
            ]
        },
        {
            "group": "Test encode method with context at EOF and non-zero modulus",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [1, 2, 3]
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
                                "modulus": 1,
                                "pos": 0,
                                "ibitWorkArea": 64,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0, 0],
                        "context": {
                            "pos": 2,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test with inAvail < 0, context.eof is false, and modulus is 1, should encode 2 bits and add padding."
                }
            ]
        }
    ]
}