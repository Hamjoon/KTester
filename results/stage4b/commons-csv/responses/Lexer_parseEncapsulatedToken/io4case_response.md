{
    "testCases": [
        {
            "group": "parseEncapsulatedToken - Test for Multiple Escape Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with multiple escape characters"
                        }
                    ],
                    "expected": "Token content should include all escaped delimiters correctly",
                    "description": "Test when multiple escape characters are encountered in sequence."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Unquoted Tokens",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token without quotes"
                        }
                    ],
                    "expected": "IOException indicating invalid character when token is not encapsulated",
                    "description": "Test when a token is not encapsulated and invalid characters are encountered."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Whitespace Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with leading whitespace"
                        }
                    ],
                    "expected": "Token content should ignore leading whitespace and include actual token",
                    "description": "Test when leading whitespace is present before the token."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with trailing whitespace"
                        }
                    ],
                    "expected": "Token content should ignore trailing whitespace before the delimiter",
                    "description": "Test when trailing whitespace is present after the token."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Invalid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with invalid characters"
                        }
                    ],
                    "expected": "IOException indicating invalid character between token and delimiter",
                    "description": "Test when an invalid character is encountered between a token and the delimiter."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Empty Token",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Empty token"
                        }
                    ],
                    "expected": "IOException indicating invalid character when token is empty",
                    "description": "Test when an empty token is processed."
                }
            ]
        }
    ]
}