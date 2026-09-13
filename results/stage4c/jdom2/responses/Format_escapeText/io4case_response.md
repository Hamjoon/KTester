{
    "test cases": [
        {
            "group": "escapeText method boundary tests",
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
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string, expecting the original string (empty) to be returned."
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
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == '\\uFFFF'; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "Test with high surrogate \u0000\u0001"
                        }
                    ],
                    "expected": "IllegalDataException",
                    "description": "Test with a string containing a high surrogate character without a low surrogate, expecting an IllegalDataException."
                }
            ]
        },
        {
            "group": "escapeText method edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == '\\uFFFF'; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": "\\r\\n"
                        },
                        {
                            "parameter": "value",
                            "value": "Line1\nLine2"
                        }
                    ],
                    "expected": "Line1\\r\\nLine2",
                    "description": "Test with newline characters and a custom eol parameter, expecting newlines to be replaced by eol."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == '\\uFFFF'; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "Special char \u007F"
                        }
                    ],
                    "expected": "Special char &#x7F;",
                    "description": "Test with a string containing a special character that should be escaped using a custom strategy."
                }
            ]
        }
    ]
}