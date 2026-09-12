{
    "group": "nextToken method additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type EOF",
            "description": "Test case where ignoreEmptyLines is true, and the reader encounters an empty line at the end of the file. This checks the branch where the method should return a token of type EOF due to empty line detection."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type TOKEN and content stripped of leading spaces",
            "description": "Test case where ignoreSurroundingSpaces is true, and the next character is a whitespace followed by a delimiter. This checks the branch where leading spaces are ignored for a token."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type TOKEN and content stripped of leading spaces",
            "description": "Test case where ignoreSurroundingSpaces is true, and the next character is a whitespace followed by a non-delimiter character. This checks the branch where leading spaces are ignored for a simple token."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type EORECORD and content stripped of leading spaces",
            "description": "Test case where ignoreSurroundingSpaces is true, and the next character is a whitespace followed by an end of line character. This checks the branch where leading spaces are ignored for an end of record."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "Token with type COMMENT and content trimmed",
            "description": "Test case where the reader's last character is the start of a line, the next character is a comment start, and the line contains leading and trailing spaces. This checks the branch where the comment content is trimmed."
        }
    ]
}