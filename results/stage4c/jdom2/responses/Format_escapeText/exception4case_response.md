{
    "group": "escapeText method additional tests",
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
            "description": "Test with a string containing a null character, expecting an IllegalDataException to be thrown."
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
            "description": "Test with a string containing a null character, expecting an IllegalDataException to be thrown."
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
            "description": "Test with a string containing a null character, expecting an IllegalDataException to be thrown."
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