{
    "test cases": [
        {
            "group": "Test readEscapeCharacter with valid escape sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "\t",
                    "description": "Test with escape sequence '\\t' to ensure it returns the tab character."
                },
                {
                    "input": [],
                    "expected": "\b",
                    "description": "Test with escape sequence '\\b' to ensure it returns the backspace character."
                },
                {
                    "input": [],
                    "expected": "\n",
                    "description": "Test with escape sequence '\\n' to ensure it returns the newline character."
                },
                {
                    "input": [],
                    "expected": "\r",
                    "description": "Test with escape sequence '\\r' to ensure it returns the carriage return character."
                },
                {
                    "input": [],
                    "expected": "\f",
                    "description": "Test with escape sequence '\\f' to ensure it returns the form feed character."
                },
                {
                    "input": [],
                    "expected": "'",
                    "description": "Test with escape sequence '\\'' to ensure it returns the single quote character."
                },
                {
                    "input": [],
                    "expected": "\"",
                    "description": "Test with escape sequence '\"' to ensure it returns the double quote character."
                },
                {
                    "input": [],
                    "expected": "\\",
                    "description": "Test with escape sequence '\\\\' to ensure it returns the backslash character."
                },
                {
                    "input": [],
                    "expected": "/",
                    "description": "Test with escape sequence '\\/' to ensure it returns the forward slash character."
                }
            ]
        },
        {
            "group": "Test readEscapeCharacter with unicode escape",
            "cases": [
                {
                    "input": [],
                    "expected": "Ω",
                    "description": "Test with escape sequence '\\u03A9' to ensure it returns the character 'Ω'."
                }
            ]
        },
        {
            "group": "Test readEscapeCharacter with invalid escape sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message 'Invalid escape sequence'",
                    "description": "Test with an invalid escape sequence to ensure it throws an IOException with the correct message."
                },
                {
                    "input": [],
                    "expected": "IOException with message 'Unterminated escape sequence'",
                    "description": "Test with an unterminated escape sequence to ensure it throws an IOException with the correct message."
                },
                {
                    "input": [],
                    "expected": "NumberFormatException with message '\\uXXXX'",
                    "description": "Test with an invalid unicode escape sequence to ensure it throws a NumberFormatException with the correct message."
                }
            ]
        },
        {
            "group": "Test readEscapeCharacter with buffer filling",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message 'Unterminated escape sequence'",
                    "description": "Test when the buffer is empty and an escape character is expected, ensure it throws an IOException."
                }
            ]
        }
    ]
}