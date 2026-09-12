{
    "group": "readEscapeCharacter Method Additional Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\"]
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 0
                }
            ],
            "expected": "Unterminated escape sequence",
            "description": "Test for unterminated escape sequence due to empty buffer"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "1", "2", "3", "4", "5"]
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 7
                }
            ],
            "expected": "Unterminated escape sequence",
            "description": "Test for invalid Unicode escape sequence with extra characters '\\u12345', expecting syntax error"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "G", "H", "I", "J"]
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 6
                }
            ],
            "expected": "Invalid escape sequence",
            "description": "Test for invalid Unicode escape sequence '\\uGHIJ', expecting NumberFormatException"
        }
    ]
}