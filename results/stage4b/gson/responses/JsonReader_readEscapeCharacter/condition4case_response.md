{
    "test cases": [
        {
            "group": "readEscapeCharacter - Valid Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected a tab character '\\t'",
                    "description": "Test case for valid escape sequence '\\t', should return a tab character."
                },
                {
                    "input": [],
                    "expected": "Expected a newline character '\\n'",
                    "description": "Test case for valid escape sequence '\\n', should return a newline character."
                },
                {
                    "input": [],
                    "expected": "Expected a carriage return character '\\r'",
                    "description": "Test case for valid escape sequence '\\r', should return a carriage return character."
                },
                {
                    "input": [],
                    "expected": "Expected a form feed character '\\f'",
                    "description": "Test case for valid escape sequence '\\f', should return a form feed character."
                },
                {
                    "input": [],
                    "expected": "Expected a backslash character '\\\\'",
                    "description": "Test case for valid escape sequence '\\\\', should return a backslash character."
                },
                {
                    "input": [],
                    "expected": "Expected a single quote character '\\''",
                    "description": "Test case for valid escape sequence '\\'', should return a single quote character."
                },
                {
                    "input": [],
                    "expected": "Expected a double quote character '\"'",
                    "description": "Test case for valid escape sequence '\"', should return a double quote character."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Unicode Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected character corresponding to unicode '\\u0041' (A)",
                    "description": "Test case for valid unicode escape sequence '\\u0041', should return 'A'."
                },
                {
                    "input": [],
                    "expected": "Expected character corresponding to unicode '\\u03A9' (Ω)",
                    "description": "Test case for valid unicode escape sequence '\\u03A9', should return 'Ω'."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Invalid Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected NumberFormatException for invalid unicode '\\uXYZ1'",
                    "description": "Test case for invalid unicode escape sequence '\\uXYZ1', should throw NumberFormatException."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for invalid escape sequence '\\q'",
                    "description": "Test case for invalid escape sequence '\\q', should throw syntax error for invalid escape sequence."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Buffer Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected IOException for unterminated escape sequence",
                    "description": "Test case where buffer is empty, should throw IOException for unterminated escape sequence."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for unterminated unicode escape sequence",
                    "description": "Test case where buffer has insufficient characters for unicode escape, should throw IOException."
                }
            ]
        }
    ]
}