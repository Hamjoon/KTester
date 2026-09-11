{
    "output": [
        {
            "group": "readEscapeCharacter - Additional Invalid Escape Sequences",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected IOException for invalid escape sequence '\\x'",
                    "description": "Test case for invalid escape sequence '\\x', should throw syntax error for invalid escape sequence."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for invalid escape sequence '\\g'",
                    "description": "Test case for invalid escape sequence '\\g', should throw syntax error for invalid escape sequence."
                }
            ]
        },
        {
            "group": "readEscapeCharacter - Buffer Handling Edge Cases",
            "cases": [
                {
                    "input": [],
                    "expected": "Expected IOException for attempting to read past end of buffer",
                    "description": "Test case where reading attempts to go past the end of the buffer, should throw IOException."
                },
                {
                    "input": [],
                    "expected": "Expected IOException for reading when buffer is not filled",
                    "description": "Test case where buffer is not filled and an escape character is requested, should throw IOException."
                }
            ]
        }
    ]
}