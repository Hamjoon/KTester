{
    "testCases": [
        {
            "group": "parseEncapsulatedToken - Test for Escape Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with escape character"
                        }
                    ],
                    "expected": "Token content should include the escaped delimiter",
                    "description": "Test when an escape character is encountered followed by an escape delimiter."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with escape character"
                        }
                    ],
                    "expected": "Token content should include the unescaped character",
                    "description": "Test when an escape character is encountered followed by a valid character."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with escape character"
                        }
                    ],
                    "expected": "IOException with message indicating EOF reached before token finished",
                    "description": "Test when an escape character is encountered, but the end of stream is reached."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Quote Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with quote character"
                        }
                    ],
                    "expected": "Token type should be TOKEN and token content should be correct",
                    "description": "Test when a quote character is encountered and a delimiter follows."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with quote character"
                        }
                    ],
                    "expected": "Token type should be EOF and isReady should be true",
                    "description": "Test when a quote character is encountered and end of file is reached."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with quote character"
                        }
                    ],
                    "expected": "Token type should be EORECORD",
                    "description": "Test when a quote character is encountered and a new line is read."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with quote character"
                        }
                    ],
                    "expected": "IOException indicating invalid character between token and delimiter",
                    "description": "Test when a quote character is encountered and an invalid character is found before the delimiter."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for End of File",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token before EOF"
                        }
                    ],
                    "expected": "IOException with message indicating EOF reached before token finished",
                    "description": "Test when end of file is reached before the encapsulated token is finished."
                }
            ]
        },
        {
            "group": "parseEncapsulatedToken - Test for Normal Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "Token with normal characters"
                        }
                    ],
                    "expected": "Token content should include all normal characters read",
                    "description": "Test when normal characters are read without encountering special conditions."
                }
            ]
        }
    ]
}