{
    "testCases": [
        {
            "group": "analyzeMultiText",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with Text only whitespace"
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
                    "expected": "mtext remains unchanged",
                    "description": "Test case where the input length is 0, expecting no changes to mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with Text and whitespace"
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
                    "expected": "mtext contains Text and whitespace as raw content",
                    "description": "Test case where the first content is Text and the second is whitespace, testing the handling of mixed content."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with all whitespace"
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
                    "expected": "mtext remains unchanged",
                    "description": "Test case where all contents are whitespace, expecting no changes to mtext."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with mixed content"
                        },
                        {
                            "parameter": "offset",
                            "value": 1
                        },
                        {
                            "parameter": "len",
                            "value": 2
                        }
                    ],
                    "expected": "mtext processes the second Content as Text and the third as raw content",
                    "description": "Test case where the offset starts at 1 and the content includes a Text and a raw content."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with multiple Text and CDATA"
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
                    "expected": "mtext contains Text, CDATA, and raw content in the order they are processed",
                    "description": "Test case with multiple content types including Text, CDATA, and raw content."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "MultiText instance with offset beyond size"
                        },
                        {
                            "parameter": "offset",
                            "value": 10
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "mtext remains unchanged",
                    "description": "Test case where the offset is beyond the size of contents, expecting no changes to mtext."
                }
            ]
        }
    ]
}