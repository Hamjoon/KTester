{
    "test_cases": [
        {
            "group": "nextToken - Handling Mixed Content",
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
                        "content": "mixedContent"
                    },
                    "description": "Test when the input contains mixed content including tokens and comments, expecting token type to be TOKEN with the content of the mixed token."
                }
            ]
        },
        {
            "group": "nextToken - Handling Comments with Trailing Spaces",
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
                        "content": "Trailing comment    "
                    },
                    "description": "Test when the character read indicates a comment start with trailing spaces, expecting token type to be COMMENT with content from the line including trailing spaces."
                }
            ]
        },
        {
            "group": "nextToken - End of File with Content",
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
                    "description": "Test when the last character read is EOF with content before it, expecting token type to be EOF."
                }
            ]
        },
        {
            "group": "nextToken - Handling Escaped Characters",
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
                        "content": "escaped\\nToken"
                    },
                    "description": "Test when the character read indicates an escaped character, expecting token type to be TOKEN with the content of the escaped token."
                }
            ]
        },
        {
            "group": "nextToken - Handling Mixed Line Endings",
            "cases": [
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
                    "description": "Test when the input contains mixed line endings (CR, LF), expecting token type to be EORECORD."
                }
            ]
        },
        {
            "group": "nextToken - Handling Multiple Consecutive Delimiters",
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
                    "description": "Test when the input contains multiple consecutive delimiters, expecting token type to be TOKEN with empty content."
                }
            ]
        }
    ]
}