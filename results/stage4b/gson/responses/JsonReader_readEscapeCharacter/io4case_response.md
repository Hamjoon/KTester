{
    "test cases": [
        {
            "group": "readEscapeCharacter - Additional Valid Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected a backspace character '\\b'",
                    "description": "Test case for valid escape sequence '\\b', should return a backspace character."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Edge Case for Buffer Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected IOException for reading beyond buffer limit",
                    "description": "Test case where reading goes beyond the buffer limit, should throw IOException."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for reading with empty buffer",
                    "description": "Test case where buffer is empty and an escape character is requested, should throw IOException."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Invalid Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected IOException for invalid escape sequence '\\x'",
                    "description": "Test case for invalid escape sequence '\\x', should throw syntax error for invalid escape sequence."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for invalid escape sequence '\\u123'",
                    "description": "Test case for invalid unicode escape sequence '\\u123', should throw IOException for incomplete unicode sequence."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Valid Unicode Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected character corresponding to unicode '\\u00A9' (©)",
                    "description": "Test case for valid unicode escape sequence '\\u00A9', should return '©'."
                },
                {
                    "input": [],
                    "expected": "Expected character corresponding to unicode '\\u20AC' (€)",
                    "description": "Test case for valid unicode escape sequence '\\u20AC', should return '€'."
                }
            ]
        }
    ]
}