{
    "test cases": [
        {
            "group": "nextToken - Empty Lines Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF"
                    },
                    "description": "Test when the input contains only empty lines and ignoreEmptyLines is true."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF"
                    },
                    "description": "Test when the input contains only empty lines and ignoreEmptyLines is false."
                }
            ]
        },
        {
            "group": "nextToken - End of File Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF"
                    },
                    "description": "Test when the last read character is EOF."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF"
                    },
                    "description": "Test when the current character read is EOF."
                }
            ]
        },
        {
            "group": "nextToken - Comment Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "COMMENT",
                        "content": "This is a comment"
                    },
                    "description": "Test when the current character is a comment start and the line has a comment."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF"
                    },
                    "description": "Test when the line is empty after a comment."
                }
            ]
        },
        {
            "group": "nextToken - Token Parsing",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "TOKEN",
                        "content": "sampleToken"
                    },
                    "description": "Test when a valid token is read."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EORecord"
                    },
                    "description": "Test when an end of record is reached."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF",
                        "isReady": true
                    },
                    "description": "Test when EOF is reached with valid content."
                }
            ]
        },
        {
            "group": "nextToken - Whitespace Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "TOKEN",
                        "content": "trimmedToken"
                    },
                    "description": "Test when surrounding spaces are ignored and a valid token is read."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EORecord"
                    },
                    "description": "Test when only whitespace is encountered and eol is true."
                }
            ]
        }
    ]
}