{
    "group": "readEscapeCharacter Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "t"]
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
            "expected": "\t",
            "description": "Test for tab escape sequence '\\t'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "b"]
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
            "expected": "\b",
            "description": "Test for backspace escape sequence '\\b'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "n"]
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
            "expected": "\n",
            "description": "Test for newline escape sequence '\\n'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "r"]
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
            "expected": "\r",
            "description": "Test for carriage return escape sequence '\\r'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "f"]
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
            "expected": "\f",
            "description": "Test for formfeed escape sequence '\\f'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "0", "0", "4", "1"]
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
            "expected": "A",
            "description": "Test for unicode escape sequence '\\u0041' which represents 'A'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "u", "0", "0", "6", "1"]
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
            "expected": "a",
            "description": "Test for unicode escape sequence '\\u0061' which represents 'a'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "'"]
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
            "expected": "'",
            "description": "Test for single quote escape sequence '\\''"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "\""]
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
            "expected": "\"",
            "description": "Test for double quote escape sequence '\\\"'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "\\"]
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
            "expected": "\\",
            "description": "Test for backslash escape sequence '\\\\'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "/"]
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
            "expected": "/",
            "description": "Test for forward slash escape sequence '\\/'"
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ["\\", "x"]
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
            "description": "Test for invalid escape sequence '\\x', expecting syntax error"
        },
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
                    "value": 1
                }
            ],
            "expected": "Unterminated escape sequence",
            "description": "Test for unterminated escape sequence due to buffer limit"
        }
    ]
}