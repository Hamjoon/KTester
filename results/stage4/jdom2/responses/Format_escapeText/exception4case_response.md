{
    "group": "escapeText",
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
                    "value": "\u0000"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a null character (U+0000), expecting IllegalDataException."
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
                    "value": "\u007F"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a DEL character (U+007F), expecting IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "\u0001"
                }
            ],
            "expected": "&#x1;",
            "description": "Test with a control character (U+0001) that should be escaped according to the strategy."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "\u0010"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a character (U+0010) that should be escaped according to strategy, expecting IllegalDataException."
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
                    "value": "\u007F"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a DEL character (U+007F), expecting IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
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
            "description": "Test with a null character (U+0000), expecting IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                },
                {
                    "parameter": "eol",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "\u007F"
                }
            ],
            "expected": "&#x7F;",
            "description": "Test with a DEL character (U+007F), expecting it to be escaped according to the strategy."
        }
    ]
}