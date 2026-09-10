{
    "output": [
        {
            "group": "nextNonWhitespace with EOF handling",
            "cases": []
        },
        {
            "group": "nextNonWhitespace with whitespace handling",
            "cases": []
        },
        {
            "group": "nextNonWhitespace with comments handling",
            "cases": []
        },
        {
            "group": "nextNonWhitespace with invalid input handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": true
                        }
                    ],
                    "expected": "IOException with message 'Unterminated comment'",
                    "description": "Test case where the input contains an unterminated multi-line comment, expecting a syntax error to be thrown."
                },
                {
                    "input": [
                        {
                            "parameter": "throwOnEof",
                            "value": true
                        }
                    ],
                    "expected": "IOException with message 'Invalid character'",
                    "description": "Test case where the input contains an invalid character, expecting a syntax error to be thrown."
                }
            ]
        },
        {
            "group": "nextNonWhitespace with valid character handling",
            "cases": []
        }
    ]
}