{
    "test cases": [
        {
            "group": "analyzeMultiText - Text Content",
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
                    "expected": "mtext contains 'SampleText'",
                    "description": "Test with a single non-whitespace text element, expecting the text to be appended directly."
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
                            "value": 2
                        }
                    ],
                    "expected": "mtext contains 'Text1 Text2'",
                    "description": "Test with two consecutive non-whitespace text elements, expecting a space between them."
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
                            "value": 3
                        }
                    ],
                    "expected": "mtext contains 'Text1 Text2'",
                    "description": "Test with text elements where the last element is whitespace, expecting no trailing space."
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
                    "expected": "mtext contains 'SampleCDATA'",
                    "description": "Test with a single non-whitespace CDATA element, expecting the CDATA to be appended directly."
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
                            "value": 2
                        }
                    ],
                    "expected": "mtext contains 'CDATA1 CDATA2'",
                    "description": "Test with two consecutive non-whitespace CDATA elements, expecting a space between them."
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
                            "value": 3
                        }
                    ],
                    "expected": "mtext contains 'CDATA1 CDATA2'",
                    "description": "Test with CDATA elements where the last element is whitespace, expecting no trailing space."
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
                    "expected": "mtext contains 'Text1 CDATA1 Text2'",
                    "description": "Test with mixed content including text and CDATA elements, ensuring correct appending with spaces."
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
                            "value": 5
                        }
                    ],
                    "expected": "mtext contains 'Text1 CDATA1 Text2'",
                    "description": "Test with mixed content where the last element is whitespace, ensuring no trailing space."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Entity Reference Content",
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
                    "expected": "mtext contains the raw entity reference",
                    "description": "Test with an entity reference, ensuring it is appended as raw content."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Whitespace Handling",
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
                            "value": 2
                        }
                    ],
                    "expected": "mtext contains ' '",
                    "description": "Test with consecutive whitespace elements, expecting only a single space to be added."
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
                            "value": 3
                        }
                    ],
                    "expected": "mtext contains ' '",
                    "description": "Test with leading and trailing whitespace elements, expecting no text to be added."
                }
            ]
        }
    ]
}