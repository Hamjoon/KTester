{
    "test cases": [
        {
            "group": "analyzeMultiText Additional Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "MultiText",
                            "value": "MockMultiText instance with Text containing leading and trailing whitespaces"
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
                    "expected": "MultiText contains trimmed Text content with Trim.BOTH",
                    "description": "Test with a single Text content containing leading and trailing whitespaces, expecting it to be trimmed and appended with Trim.BOTH."
                },
                {
                    "input": [
                        {
                            "parameter": "MultiText",
                            "value": "MockMultiText instance with multiple Text contents, some with only whitespaces"
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
                    "expected": "MultiText contains only non-whitespace Text contents trimmed appropriately",
                    "description": "Test with multiple Text contents, some containing only whitespaces, expecting only non-whitespace contents to be appended with appropriate Trim."
                },
                {
                    "input": [
                        {
                            "parameter": "MultiText",
                            "value": "MockMultiText instance with Text and CDATA contents with leading whitespaces"
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
                    "expected": "MultiText contains both Text and CDATA contents trimmed, Text with Trim.LEFT and CDATA with Trim.RIGHT",
                    "description": "Test with a Text and a CDATA content both having leading whitespaces, expecting Text with Trim.LEFT and CDATA with Trim.RIGHT."
                },
                {
                    "input": [
                        {
                            "parameter": "MultiText",
                            "value": "MockMultiText instance with empty content list"
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
                    "expected": "MultiText remains unchanged",
                    "description": "Test with an empty content list, expecting no changes to MultiText."
                },
                {
                    "input": [
                        {
                            "parameter": "MultiText",
                            "value": "MockMultiText instance with mixed contents including nested EntityRef"
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
                    "expected": "MultiText contains all contents trimmed appropriately, nested EntityRef as raw",
                    "description": "Test with mixed contents including nested EntityRef, expecting each to be appended according to their type and position, with EntityRef as raw."
                }
            ]
        }
    ]
}