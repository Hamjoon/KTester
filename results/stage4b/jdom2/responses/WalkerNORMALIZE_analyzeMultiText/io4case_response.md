{
    "testCases": [
        {
            "group": "analyzeMultiText - Multiple Whitespace Text",
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
                            "value": 2
                        }
                    ],
                    "expected": "MultiText remains unchanged",
                    "description": "Tests the case where the Content consists of multiple whitespace Text elements, verifying that MultiText does not change."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Leading and Trailing Spaces",
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
                    "expected": "MultiText contains text with leading and trailing spaces handled correctly",
                    "description": "Tests the scenario where text elements have leading and trailing spaces, ensuring they are trimmed or managed appropriately."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Mixed Empty and Non-Empty Content",
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
                            "value": 4
                        }
                    ],
                    "expected": "MultiText contains non-empty text and ignores empty ones",
                    "description": "Tests the scenario where mixed Content includes both empty and non-empty elements, verifying that only non-empty elements are appended."
                }
            ]
        },
        {
            "group": "analyzeMultiText - EntityRef with Whitespace",
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
                            "value": 2
                        }
                    ],
                    "expected": "MultiText contains raw value of EntityRef and ignores whitespace",
                    "description": "Tests the scenario where an EntityRef is followed by whitespace, ensuring that the EntityRef is correctly appended while ignoring the whitespace."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Long Content Length",
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
                            "value": 10
                        }
                    ],
                    "expected": "MultiText contains all valid content up to the specified length",
                    "description": "Tests the scenario where the length exceeds the actual content size, ensuring that MultiText correctly handles the situation without errors."
                }
            ]
        }
    ]
}