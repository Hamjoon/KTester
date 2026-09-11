{
    "testCases": [
        {
            "group": "escapeAttribute",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "normalString"
                        }
                    ],
                    "expected": "normalString",
                    "description": "Test with a normal string that does not require escaping."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "stringWithSpecialChar&"
                        }
                    ],
                    "expected": "stringWithSpecialChar&amp;",
                    "description": "Test with a string that contains a character that needs to be escaped according to the strategy."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "<tag>"
                        }
                    ],
                    "expected": "&lt;tag&gt;",
                    "description": "Test with a string containing XML special characters that should be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "stringWithNewline\nandTab\t"
                        }
                    ],
                    "expected": "stringWithNewline&#xA;andTab&#x9;",
                    "description": "Test with a string containing newline and tab characters that need to be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "surrogatePair\u0000D\u0000E"
                        }
                    ],
                    "expected": "Surrogate pair 0x0D truncated",
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
                            "value": "normalString"
                        }
                    ],
                    "expected": "&#x6E;ormalString",
                    "description": "Test with a string that has a character that needs to be escaped according to the strategy."
                }
            ]
        }
    ]
}