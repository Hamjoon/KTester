{
    "testCases": [
        {
            "group": "parseEncapsulatedToken - Escape Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains escaped delimiter",
                    "description": "Test when an escape character is followed by an escape delimiter, ensuring the delimiter is added to the token content."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains the unescaped character",
                    "description": "Test when an escape character is followed by a valid character, ensuring that the unescaped character is added to the token content."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating unexpected char after escape",
                    "description": "Test when an escape character is followed by the end of stream to ensure an IOException is thrown with the correct message."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Quoting Logic",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains the character read after a quote character",
                    "description": "Test when a quote character is encountered followed by another quote character to ensure the character is added to the token content."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.type is TOKEN",
                    "description": "Test when a quote character is followed by a delimiter to ensure the token is correctly marked as finished."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.type is EOF and token.isReady is true",
                    "description": "Test when a quote character is followed by end of file to ensure the token marks EOF correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating invalid char between token and delimiter",
                    "description": "Test when a non-whitespace character is found between the token and the next delimiter to ensure an IOException is thrown."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - End of File Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating EOF reached before token finished",
                    "description": "Test when the end of file is reached before the encapsulated token is finished to ensure an IOException is thrown."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - General Character Consumption",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains the consumed characters",
                    "description": "Test the normal flow where characters are read and appended to the token content until a delimiter is reached."
                }
            ]
        }
    ]
}