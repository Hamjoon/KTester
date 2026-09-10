{
    "testCases": [
        {
            "group": "analyzeMultiText - Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 0
                        }
                    ],
                    "expected": "mtext remains empty",
                    "description": "Test with no content to analyze, expecting mtext to remain empty."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Single Whitespace Element",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
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
                    "expected": "mtext contains ''",
                    "description": "Test with a single whitespace element, expecting no text to be added."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Mixed Whitespace and Text",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
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
                    "expected": "mtext contains 'Text1 Text2'",
                    "description": "Test with mixed content including leading whitespace and non-whitespace text, ensuring correct appending."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 6
                        }
                    ],
                    "expected": "mtext contains 'Text1 Text2'",
                    "description": "Test with mixed content including trailing whitespace and non-whitespace text, ensuring no trailing space."
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
                            "value": "new MultiText()"
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
                    "expected": "mtext contains 'Text1'",
                    "description": "Test with leading spaces before the first text element, expecting leading spaces to be ignored."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": 1
                        },
                        {
                            "parameter": "len",
                            "value": 5
                        }
                    ],
                    "expected": "mtext contains 'Text1'",
                    "description": "Test with trailing spaces after the last text element, expecting trailing spaces to be ignored."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Consecutive Entity References",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
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
                    "expected": "mtext contains the raw entity references without additional spaces",
                    "description": "Test with multiple consecutive entity references, ensuring they are appended correctly without spaces."
                }
            ]
        }
    ]
}