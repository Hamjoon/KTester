{
    "group": "Test readEscapeCharacter with additional invalid escape sequences",
    "cases": [
        {
            "input": [
                {
                    "parameter": "escape_sequence",
                    "value": "\\x01"
                }
            ],
            "expected": "IOException with message 'Invalid escape sequence'",
            "description": "Test with an escape sequence '\\x01' to ensure it throws an IOException for an invalid escape sequence."
        },
        {
            "input": [
                {
                    "parameter": "escape_sequence",
                    "value": "\\xFF"
                }
            ],
            "expected": "IOException with message 'Invalid escape sequence'",
            "description": "Test with an escape sequence '\\xFF' to ensure it throws an IOException for an invalid escape sequence."
        }
    ]
}