{
    "group": "analyzeMultiText",
    "cases": [
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MultiText instance with Text Content starting and ending with whitespace"
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
            "expected": "MultiText instance should append trimmed text with whitespace normalized",
            "description": "Test with Text Content starting and ending with whitespace. Ensure that leading and trailing whitespace is handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MultiText instance with multiple Text Contents with interspersed whitespace"
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
            "expected": "MultiText instance should append text with spaces between non-whitespace contents",
            "description": "Test with multiple Text Contents with interspersed whitespace. Ensure spaces are added between non-whitespace contents."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MultiText instance with CDATA Content starting and ending with whitespace"
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
            "expected": "MultiText instance should append trimmed CDATA with whitespace normalized",
            "description": "Test with CDATA Content starting and ending with whitespace. Ensure that leading and trailing whitespace is handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MultiText instance with mixed Content types with leading whitespace"
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
            "expected": "MultiText instance should append content with leading whitespace handled correctly",
            "description": "Test with mixed Content types starting with whitespace. Ensure leading whitespace is handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MultiText instance with Text Content containing special XML characters"
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
            "expected": "MultiText instance should append text with special XML characters preserved",
            "description": "Test with Text Content containing special XML characters. Ensure special characters are preserved during appending."
        }
    ]
}