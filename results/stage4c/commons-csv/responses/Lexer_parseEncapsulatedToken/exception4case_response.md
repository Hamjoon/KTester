{
    "group": "parseEncapsulatedToken Additional Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithUnescapedEOF"
                }
            ],
            "expected": "IOException with message '(startline 1) EOF reached before encapsulated token finished'",
            "description": "Test case where EOF is encountered without being properly escaped, leading to an IOException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithInvalidCharAfterQuote"
                }
            ],
            "expected": "IOException with message '(line 1) invalid char between encapsulated token and delimiter'",
            "description": "Test case where an invalid character is found immediately after a quote, leading to an IOException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithUnexpectedCharAfterEscape"
                }
            ],
            "expected": "token with content appended by escape character and unexpected character",
            "description": "Test case where an unexpected character follows an escape character, resulting in both characters being appended to the token content."
        }
    ]
}