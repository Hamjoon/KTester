{
    "testCases": [
        {
            "group": "nextUnquotedValue - Valid Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "validJsonString"
                        }
                    ],
                    "expected": "validJsonString",
                    "description": "Test case where the buffer contains a valid unquoted string value."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Non-Literal Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1234, {"
                        }
                    ],
                    "expected": "1234",
                    "description": "Test case where the buffer contains numeric value followed by a comma and a non-literal character."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "abc;}"
                        }
                    ],
                    "expected": "abc",
                    "description": "Test case where the buffer contains a string followed by a semicolon and a closing brace."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Buffer Overflow Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "veryLongStringThatExceedsBufferSize"
                        }
                    ],
                    "expected": "veryLongStringThatExceedsBufferSize",
                    "description": "Test case where the buffer contains a string that exceeds the buffer size, checking if it is handled correctly."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Empty Buffer",
            "cases": [
                {
                    "input": [],
                    "expected": "EOFException",
                    "description": "Test case where the buffer is empty, expecting an EOFException to be thrown."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Mixed Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "mixedValue; {"
                        }
                    ],
                    "expected": "mixedValue",
                    "description": "Test case where the buffer contains a mixed value followed by a semicolon and a non-literal character."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "anotherValue, }"
                        }
                    ],
                    "expected": "anotherValue",
                    "description": "Test case where the buffer contains another value followed by a comma and a closing brace."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Check Lenient Mode",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "lenientValue#}"
                        }
                    ],
                    "expected": "lenientValue",
                    "description": "Test case to verify that in lenient mode, the method correctly processes a string followed by a hash character."
                }
            ]
        }
    ]
}