{
    "group": "parseEncapsulatedToken Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "emptyToken"
                }
            ],
            "expected": "IOException with message '(startline 1) EOF reached before encapsulated token finished'",
            "description": "Test case where EOF is reached before the encapsulated token is finished, leading to an IOException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "escapedDelimiterToken"
                }
            ],
            "expected": "token with content appended by delimiter and type TOKEN",
            "description": "Test case where the escape character is followed by a delimiter, resulting in the delimiter being appended to the token content."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "escapedCharacterToken"
                }
            ],
            "expected": "token with content appended by unescaped character",
            "description": "Test case where the escape character is followed by a non-delimiter character, resulting in the unescaped character being appended to the token content."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "doubleQuoteToken"
                }
            ],
            "expected": "token with content appended by a single quote character",
            "description": "Test case where the quote character is followed by another quote character, resulting in a single quote character being appended to the token content."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "endOfTokenWithWhitespaceToken"
                }
            ],
            "expected": "token with type TOKEN",
            "description": "Test case where the token finishes with a quote character, followed by whitespace and a delimiter, resulting in a TOKEN type."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "endOfTokenWithEOFToken"
                }
            ],
            "expected": "token with type EOF and isReady set to true",
            "description": "Test case where the token finishes with a quote character, followed by EOF, resulting in EOF type and isReady set to true."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "endOfTokenWithEOLToken"
                }
            ],
            "expected": "token with type EORECORD",
            "description": "Test case where the token finishes with a quote character, followed by an end of line, resulting in EORECORD type."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "invalidCharBetweenTokenAndDelimiter"
                }
            ],
            "expected": "IOException with message '(line 1) invalid char between encapsulated token and delimiter'",
            "description": "Test case where a non-whitespace character is found between the token and the next delimiter, leading to an IOException."
        }
    ]
}