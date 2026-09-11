{
    "test cases": [
        {
            "group": "analyzeMultiText",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with only CDATA"
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
                    "expected": "mtext contains CDATA without any trimming",
                    "description": "Test with only CDATA elements, should append CDATA to mtext without any trimming."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with mixed whitespace and non-whitespace Text"
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
                    "expected": "mtext contains non-whitespace Text, whitespace ignored",
                    "description": "Test with mixed whitespace and non-whitespace Text elements, should append only non-whitespace Text to mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with only EntityRef"
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
                    "expected": "mtext contains raw EntityRef content",
                    "description": "Test with only EntityRef content, should append raw EntityRef to mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with mixed Content types"
                        },
                        {
                            "parameter": "offset",
                            "value": 1
                        },
                        {
                            "parameter": "len",
                            "value": 4
                        }
                    ],
                    "expected": "mtext contains mixed Content with appropriate handling",
                    "description": "Test with mixed Content types starting from offset 1, should handle each type appropriately."
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
                            "value": 10
                        }
                    ],
                    "expected": "mtext contains trimmed Text and raw Content",
                    "description": "Test with length of 10, should apply LEFT trim to the first Text, handle raw Content in the middle, and RIGHT trim to the last Text."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText() with mixed whitespace and non-whitespace Text"
                        },
                        {
                            "parameter": "offset",
                            "value": 2
                        },
                        {
                            "parameter": "len",
                            "value": 3
                        }
                    ],
                    "expected": "mtext contains non-whitespace Text with appropriate trimming",
                    "description": "Test with mixed whitespace and non-whitespace Text starting from offset 2, should handle trimming correctly."
                }
            ]
        }
    ]
}