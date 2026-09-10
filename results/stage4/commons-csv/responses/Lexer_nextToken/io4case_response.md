{
    "testCases": [
        {
            "group": "nextToken - Token Parsing with Encapsulated Tokens",
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
                        "content": "encapsulatedToken"
                    },
                    "description": "Test when an encapsulated token is read from the input."
                }
            ]
        },
        {
            "group": "nextToken - Handling of Escape Characters",
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
                        "content": "escapedToken"
                    },
                    "description": "Test when a token contains escape characters."
                }
            ]
        },
        {
            "group": "nextToken - Handling of Mixed Content",
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
                        "content": "mixedContentToken"
                    },
                    "description": "Test when the input contains mixed content including tokens and comments."
                }
            ]
        },
        {
            "group": "nextToken - Handling of Quotes",
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
                        "content": "quotedToken"
                    },
                    "description": "Test when the input contains a quoted token."
                }
            ]
        },
        {
            "group": "nextToken - End of Line Handling with Non-Empty Lines",
            "cases": [
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
                    "description": "Test when the end of line is reached with non-empty content."
                }
            ]
        },
        {
            "group": "nextToken - Handling of Initial Whitespace",
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
                        "content": "tokenWithLeadingWhitespace"
                    },
                    "description": "Test when the input starts with leading whitespace before a valid token."
                }
            ]
        }
    ]
}