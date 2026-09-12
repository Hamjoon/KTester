{
    "group": "nextToken method test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type EOF",
            "description": "Test case where the reader's last character and next character are both EOF. This checks the branch where the method should return a token of type EOF when the end of the file is detected."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type COMMENT",
            "description": "Test case where the reader's last character is the start of a line and the next character is a comment start. This checks the branch where the method should return a token of type COMMENT after reading a comment line."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type TOKEN",
            "description": "Test case where the next character is a delimiter. This checks the branch where an empty token is returned with type TOKEN."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type EORECORD",
            "description": "Test case where the next character is end of line (EOL). This checks the branch where an empty token is returned with type EORECORD."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type EOF and isReady true",
            "description": "Test case where the next character is EOF and there is data at EOF. This checks the branch where the method should return a token of type EOF and set isReady to true."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with content from parseEncapsulatedToken",
            "description": "Test case where the next character is a quote character. This checks the branch where the method should parse an encapsulated token."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with content from parseSimpleToken",
            "description": "Test case where the next character is not a delimiter, EOL, EOF, or quote. This checks the branch where the method should parse a simple token."
        }
    ]
}