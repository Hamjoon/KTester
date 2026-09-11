{
    "testCases": [
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
                            "value": "stringWithNoSpecialChars"
                        }
                    ],
                    "expected": "stringWithNoSpecialChars",
                    "description": "Test with a string that contains no characters needing escape according to the strategy."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u0000"  
                        }
                    ],
                    "expected": "&#x0;",
                    "description": "Test with a string containing a null character, expecting it to be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "stringWithUnicode\u0010"
                        }
                    ],
                    "expected": "stringWithUnicode&#x10;",
                    "description": "Test with a string containing a Unicode character that needs to be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "stringWithMixedChars<>&\""
                        }
                    ],
                    "expected": "&lt;stringWithMixedChars&gt;&amp;&quot;",
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
                            "value": "stringWithSurrogatePair\u0000D\u0000E"
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
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string, expecting the output to also be an empty string."
                }
            ]
        }
    ]
}