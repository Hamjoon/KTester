{
    "testCases": [
        {
            "group": "analyzeMultiText - Text Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "MultiText contains the text value from the first Content",
                    "description": "Tests the case where the Content is a non-whitespace Text, verifying that it is appended to MultiText."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "MultiText remains unchanged",
                    "description": "Tests the case where the Content is a whitespace Text, verifying that it does not modify MultiText."
                }
            ]
        },
        {
            "group": "analyzeMultiText - CDATA Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "MultiText contains the CDATA value from the first Content",
                    "description": "Tests the case where the Content is a non-whitespace CDATA, verifying that it is appended to MultiText."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "MultiText remains unchanged",
                    "description": "Tests the case where the Content is a whitespace CDATA, verifying that it does not modify MultiText."
                }
            ]
        },
        {
            "group": "analyzeMultiText - EntityRef Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "MultiText contains the raw value of the EntityRef",
                    "description": "Tests the case where the Content is an EntityRef, verifying that it is appended to MultiText."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Mixed Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 3
                        }
                    ],
                    "expected": "MultiText contains text, CDATA, and raw value without leading/trailing spaces",
                    "description": "Tests the scenario where mixed Content types are processed, ensuring proper handling of whitespace and text."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Whitespace Management",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with initial empty state"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 5
                        }
                    ],
                    "expected": "MultiText contains text with appropriate spaces inserted",
                    "description": "Tests the scenario where multiple text contents are interspersed with whitespace, ensuring spaces are added correctly."
                }
            ]
        }
    ]
}