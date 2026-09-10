{
    "testCases": [
        {
            "group": "parseEncapsulatedToken - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating invalid char between token and delimiter",
                    "description": "Test when a character that is neither whitespace nor a delimiter is found directly after a quote character, ensuring an IOException is thrown."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content is empty",
                    "description": "Test when an empty token is passed to the method, ensuring that the content remains empty and no exceptions are thrown."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains characters before EOF and token.type is EOF",
                    "description": "Test when characters are consumed until EOF is reached, ensuring that the token content is correctly populated and the type is set to EOF."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains special characters",
                    "description": "Test when special characters (e.g., newline, tab) are included in the token content, ensuring they are correctly appended."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating invalid escape sequence",
                    "description": "Test when an invalid escape sequence is encountered, ensuring an IOException is thrown with the correct message."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Multiple Quotes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "token.content contains all characters between quotes",
                    "description": "Test when multiple quote characters are used in succession, ensuring that the content between them is correctly captured."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": "IOException with message indicating unmatched quotes",
                    "description": "Test when an opening quote is present without a matching closing quote, ensuring an IOException is thrown."
                }
            ]
        }
    ]
}