{
    "testCases": [
        {
            "group": "analyzeMultiText",
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
                    "expected": "no changes to mtext",
                    "description": "Test with zero length, should not modify mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with whitespace Text"
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
                    "expected": "no changes to mtext",
                    "description": "Test with all whitespace Text elements, should not append any text."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with non-whitespace Text"
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
                    "expected": "mtext contains non-whitespace Text",
                    "description": "Test with non-whitespace Text elements, should append text to mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with mixed Content"
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
                    "expected": "mtext contains mixed Content with appropriate handling",
                    "description": "Test with a mix of Text, CDATA, and other content types, should append correctly."
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
                            "value": 1
                        }
                    ],
                    "expected": "mtext contains both trimmed Text",
                    "description": "Test with length of 1, should apply BOTH trim."
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
                    "expected": "mtext contains left trimmed Text and right trimmed Text",
                    "description": "Test with length of 2, should apply LEFT trim to first and RIGHT trim to last."
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
                    "expected": "mtext contains left trimmed Text, raw Content, and right trimmed Text",
                    "description": "Test with length of 3, should apply LEFT trim to first, raw Content in the middle, and RIGHT trim to last."
                }
            ]
        }
    ]
}