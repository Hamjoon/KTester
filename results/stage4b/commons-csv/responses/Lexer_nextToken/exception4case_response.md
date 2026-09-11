{
    "result": [
        {
            "group": "nextToken - IOException Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "IOException"
                    },
                    "description": "Test when an IOException is thrown by the reader during character reading."
                },
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "IOException"
                    },
                    "description": "Test when an IOException is thrown while reading a line for comment."
                }
            ]
        },
        {
            "group": "nextToken - Invalid Token Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "token",
                            "value": "new Token()"
                        }
                    ],
                    "expected": {
                        "type": "INVALID"
                    },
                    "description": "Test when the input character is invalid and should be handled as an INVALID token."
                }
            ]
        }
    ]
}