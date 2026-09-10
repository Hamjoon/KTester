{
    "test cases": [
        {
            "group": "nextUnquotedValue - Valid Input Scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "12345"
                        }
                    ],
                    "expected": "12345",
                    "description": "Test with a simple numeric value to ensure it returns the correct unquoted value."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "hello"
                        }
                    ],
                    "expected": "hello",
                    "description": "Test with a simple string to ensure it returns the correct unquoted value."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo bar"
                        }
                    ],
                    "expected": "foo bar",
                    "description": "Test with a string containing a space to ensure it returns the correct unquoted value."
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
                            "value": "foo/bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a non-literal character '/' to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo\\bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a backslash '\\' to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo;bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a semicolon ';' to ensure it stops at the correct position."
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
                            "value": "a long string that exceeds the buffer size limit which should be handled correctly"
                        }
                    ],
                    "expected": "a long string that exceeds the buffer size limit which should be handled correctly",
                    "description": "Test with a long string that exceeds the buffer size to ensure it is handled correctly using StringBuilder."
                },
                {
                    "input": [],
                    "expected": "EOFException",
                    "description": "Test for EOFException when there are no more characters to read."
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
                            "value": "foo#bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing a hash '#' to ensure it stops at the correct position."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "foo=bar"
                        }
                    ],
                    "expected": "foo",
                    "description": "Test with a string containing an equal sign '=' to ensure it stops at the correct position."
                }
            ]
        }
    ]
}