{
    "testCases": [
        {
            "group": "nextNonWhitespace - Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": true
                        }
                    ],
                    "expected": "EOFException",
                    "description": "Test case to verify that an EOFException is thrown when the end of input is reached and throwOnEof is true."
                },
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "-1",
                    "description": "Test case to verify that -1 is returned when the end of input is reached and throwOnEof is false."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Whitespace Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly skips over whitespace characters and returns the next non-whitespace character."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Comment Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly skips over single-line comments (//) and returns the next non-whitespace character."
                },
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly skips over multi-line comments (/* */) and returns the next non-whitespace character."
                },
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly skips over hash comments (#) and returns the next non-whitespace character."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": true
                        }
                    ],
                    "expected": "EOFException",
                    "description": "Test case to verify that an EOFException is thrown when an unexpected end of input is reached while processing comments."
                }
            ]
        }
    ]
}