{
    "testCases": [
        {
            "group": "Test encode method with context at EOF",
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
                                "eof": true,
                                "modulus": 0,
                                "pos": 0,
                                "ibitWorkArea": 0,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test when context.eof is true, should return without processing."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail < 0 and context.modulus = 0",
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
                            "value": -1
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
                    "expected": null,
                    "description": "Test when inAvail is negative, modulus is 0, and no leftovers, should return without processing."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail < 0 and context.modulus = 1",
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
                    "description": "Test when inAvail is negative, modulus is 1, should encode 2 bits and add padding."
                }
            ]
        },
        {
            "group": "Test encode method with inAvail < 0 and context.modulus = 2",
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
                            "value": -1
                        },
                        {
                            "parameter": "context",
                            "value": {
                                "eof": false,
                                "modulus": 2,
                                "pos": 0,
                                "ibitWorkArea": 1024,
                                "currentLinePos": 0
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0],
                        "context": {
                            "pos": 1,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test when inAvail is negative, modulus is 2, should encode 4 bits and add one padding."
                }
            ]
        },
        {
            "group": "Test encode method with valid inAvail",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [72, 101, 108, 108, 111]
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
                    "description": "Test encoding with valid inAvail, should process all bytes and update context."
                }
            ]
        },
        {
            "group": "Test encode method with line length handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": [72, 101, 108, 108, 111]
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
                                "ibitWorkArea": 0,
                                "currentLinePos": 4
                            }
                        }
                    ],
                    "expected": {
                        "buffer": [0, 0, 0, 0, 0, 0],
                        "context": {
                            "pos": 6,
                            "currentLinePos": 0
                        }
                    },
                    "description": "Test encoding with line length, should insert line separator after reaching line length."
                }
            ]
        }
    ]
}