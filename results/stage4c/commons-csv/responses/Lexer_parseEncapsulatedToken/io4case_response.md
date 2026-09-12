{
    "group": "parseEncapsulatedToken Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "emptyTokenWithImmediateEOF"
                }
            ],
            "expected": "IOException with message '(startline 1) EOF reached before encapsulated token finished'",
            "description": "Test case where the token is empty and EOF is immediately encountered, leading to an IOException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithOnlyWhitespace"
                }
            ],
            "expected": "token with type TOKEN",
            "description": "Test case where the token consists solely of whitespace characters and is correctly terminated by a delimiter."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithMixedContentAndEscape"
                }
            ],
            "expected": "token with content appended by unescaped characters and type TOKEN",
            "description": "Test case where the token contains a mix of regular characters and escape sequences, resulting in the unescaped characters being appended to the token content, and correctly terminated by a delimiter."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithUnescapedEndOfStream"
                }
            ],
            "expected": "IOException with message '(startline 1) EOF reached before encapsulated token finished'",
            "description": "Test case where an unescaped END_OF_STREAM character is encountered, leading to an IOException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "tokenWithImmediateDelimiter"
                }
            ],
            "expected": "token with type TOKEN and empty content",
            "description": "Test case where the token starts and immediately encounters a delimiter, resulting in a TOKEN type with empty content."
        }
    ]
}