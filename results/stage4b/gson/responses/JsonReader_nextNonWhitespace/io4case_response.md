{
    "testCases": [
        {
            "group": "nextNonWhitespace - Additional Whitespace Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly returns the next non-whitespace character after multiple whitespace characters."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Handling Mixed Comments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly skips over multiple types of comments (single-line, multi-line, and hash) interspersed with whitespace and returns the next non-whitespace character."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - EOF Handling with Comments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": true
                        }
                    ],
                    "expected": "EOFException",
                    "description": "Test case to verify that an EOFException is thrown when the end of input is reached while processing comments and throwOnEof is true."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Handling Valid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "a valid non-whitespace character",
                    "description": "Test case to verify that the method returns a valid non-whitespace character when valid characters are present after whitespace and comments."
                }
            ]
        },
        {
            "group": "nextNonWhitespace - Multiple Consecutive Comments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": false
                        }
                    ],
                    "expected": "non-whitespace character",
                    "description": "Test case to verify that the method correctly handles multiple consecutive comments without throwing an exception and returns the next non-whitespace character."
                }
            ]
        }
    ]
}