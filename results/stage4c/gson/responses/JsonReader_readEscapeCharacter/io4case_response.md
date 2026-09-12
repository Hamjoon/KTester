{
    "group": "readEscapeCharacter Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "0", "0", "G", "1"]
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
            "description": "Test for invalid Unicode escape sequence '\\u00G1', expecting NumberFormatException"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "1", "2", "3"]
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 5
                }
            ],
            "expected": "Unterminated escape sequence",
            "description": "Test for unterminated Unicode escape sequence '\\u123', expecting syntax error"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "0", "0", "4", "1", "A"]
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
            "expected": "A",
            "description": "Test for valid Unicode escape sequence '\\u0041' followed by an additional character"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "A", "B", "C", "D"]
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
            "expected": "\u0000ABCD",
            "description": "Test for valid Unicode escape sequence '\\uABCD'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", " "]
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 2
                }
            ],
            "expected": "Invalid escape sequence",
            "description": "Test for invalid escape sequence '\\ ', expecting syntax error"
        }
    ]
}