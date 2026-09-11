{
    "testCases": [
        {
            "group": "nextToken - Handling Empty Lines",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EOF",
                        "content": ""
                    },
                    "description": "Test when ignoreEmptyLines is true and the input contains only empty lines, expecting token type to be EOF."
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
                        "content": ""
                    },
                    "description": "Test when ignoreEmptyLines is false and the input contains only empty lines, expecting token type to be EOF."
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
                        "type": "EOF",
                        "content": ""
                    },
                    "description": "Test when the last character read is EOF, expecting token type to be EOF."
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
                        "content": ""
                    },
                    "description": "Test when the next character read is EOF, expecting token type to be EOF."
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
                    "description": "Test when the character read indicates a comment start, expecting token type to be COMMENT with content from the line."
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
                        "content": "simpleToken"
                    },
                    "description": "Test when the character read is a simple token, expecting token type to be TOKEN with the content of the simple token."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "EORecord",
                        "content": ""
                    },
                    "description": "Test when the character read indicates end of record, expecting token type to be EORECORD."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "TOKEN",
                        "content": "encapsulatedToken"
                    },
                    "description": "Test when the character read indicates the start of an encapsulated token, expecting token type to be TOKEN with the content of the encapsulated token."
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
                        "content": ""
                    },
                    "description": "Test when ignoreSurroundingSpaces is true and there are leading whitespaces, expecting token type to be TOKEN with trimmed content."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "TOKEN",
                        "content": "tokenWithSpaces"
                    },
                    "description": "Test when ignoreSurroundingSpaces is false and there are leading whitespaces, expecting token type to be TOKEN with spaces included in content."
                }
            ]
        }
    ]
}