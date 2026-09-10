{
    "group": "nextToken - Exception Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test when an IOException is thrown during character reading from the reader."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test when an IOException occurs during the parsing of encapsulated tokens."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test when an IOException occurs during the parsing of simple tokens."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test when an IOException is thrown during the end of line reading."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test when an IOException is thrown when checking if the character is a delimiter."
        }
    ]
}