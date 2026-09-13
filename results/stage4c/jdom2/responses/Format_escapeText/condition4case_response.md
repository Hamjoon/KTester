{
    "group": "escapeText method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "NoSpecialChars"
                }
            ],
            "expected": "NoSpecialChars",
            "description": "Test with a string that requires no escaping, expecting the original string to be returned."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "Contains<Special&Chars>"
                }
            ],
            "expected": "Contains&lt;Special&amp;Chars&gt;",
            "description": "Test with a string containing special characters <, >, &, expecting them to be escaped."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                },
                {
                    "parameter": "eol",
                    "value": "\\n"
                },
                {
                    "parameter": "value",
                    "value": "Line1\nLine2"
                }
            ],
            "expected": "Line1\\nLine2",
            "description": "Test with newline characters and a non-null eol parameter, expecting newlines to be replaced by eol."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == 'x'; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "Escape x character"
                }
            ],
            "expected": "Escape &#x78; character",
            "description": "Test with a custom escape strategy that escapes 'x', expecting 'x' to be replaced by its escaped form."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "\u0000"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing an invalid character, expecting an IllegalDataException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "\u0000\u0001"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing surrogate pairs without a valid high surrogate, expecting an IllegalDataException."
        }
    ]
}