{
    "testCases": [
        {
            "group": "nextUnquotedValue - Valid Input Scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "   leading spaces"
                        }
                    ],
                    "expected": "leading spaces",
                    "description": "Test with a string that has leading spaces to ensure it returns the correct unquoted value after trimming."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "trailing spaces   "
                        }
                    ],
                    "expected": "trailing spaces",
                    "description": "Test with a string that has trailing spaces to ensure it returns the correct unquoted value after trimming."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "mixed   spaces and \t tabs"
                        }
                    ],
                    "expected": "mixed   spaces and \t tabs",
                    "description": "Test with a string that contains both spaces and tabs to ensure it returns the correct unquoted value."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Non-Literal Characters Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo]bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a closing bracket ']' to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo{bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing an opening brace '{' to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo}bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a closing brace '}' to ensure it stops at the correct position."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Buffer Handling and Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "short"
                        }
                    ],
                    "expected": "short",
                    "description": "Test with a short string that fits within the buffer size to ensure it returns the correct unquoted value."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "a very long string that is exactly 1024 characters long to test the buffer limit handling. This string will be repeated multiple times to reach the required length."
                        }
                    ],
                    "expected": "a very long string that is exactly 1024 characters long to test the buffer limit handling. This string will be repeated multiple times to reach the required length.",
                    "description": "Test with a string that is exactly 1024 characters long to ensure it is handled correctly at the buffer limit."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Special Character Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo@bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing an '@' character to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo:bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a colon ':' to ensure it stops at the correct position."
                }
            ]
        }
    ]
}