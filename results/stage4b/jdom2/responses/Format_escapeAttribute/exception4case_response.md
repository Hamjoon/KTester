{
    "group": "escapeAttribute",
    "cases": [
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "value",
                    "value": "surrogatePair\u0000D"
                }
            ],
            "expected": "Surrogate pair 0xD truncated",
            "description": "Test with a string containing a high surrogate character without a corresponding low surrogate character, expecting an IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "value",
                    "value": "surrogatePair\u0000E"
                }
            ],
            "expected": "Surrogate pair 0xE truncated",
            "description": "Test with a string containing a high surrogate character without a corresponding low surrogate character, expecting an IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "value",
                    "value": "stringWithHighSurrogate\u0010"
                }
            ],
            "expected": "Surrogate pair 0x10 truncated",
            "description": "Test with a string containing a high surrogate character without a corresponding low surrogate character, expecting an IllegalDataException."
        }
    ]
}