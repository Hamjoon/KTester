{
    "test cases": [
        {
            "group": "escapeText with empty string",
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
                    "description": "Test with an empty string, expecting the same empty string as output."
                }
            ]
        },
        {
            "group": "escapeText with all characters needing escape",
            "cases": [
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
                            "value": "<>&\n\r"
                        }
                    ],
                    "expected": "&lt;&gt;&amp;&#xD;\n",
                    "description": "Test with a string containing all characters that need escaping, expecting all to be converted."
                }
            ]
        },
        {
            "group": "escapeText with mixed characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == 'a'; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "a <b> & c"
                        }
                    ],
                    "expected": "&amp;lt;b&amp;gt; &amp; c",
                    "description": "Test with a string containing a mix of characters, where only 'a' should be escaped."
                }
            ]
        },
        {
            "group": "escapeText with high surrogate character",
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
                            "value": "\u0000D\u0000E"
                        }
                    ],
                    "expected": "&#xD;&#xE;",
                    "description": "Test with a string containing high surrogate characters, expecting them to be escaped."
                }
            ]
        },
        {
            "group": "escapeText with illegal surrogate pair",
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
                            "value": "\u0000D\u0000F"
                        }
                    ],
                    "expected": "IllegalDataException",
                    "description": "Test with an illegal surrogate pair, expecting an IllegalDataException to be thrown."
                }
            ]
        },
        {
            "group": "escapeText with custom line separator",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": "---"
                        },
                        {
                            "parameter": "value",
                            "value": "Line1\nLine2"
                        }
                    ],
                    "expected": "Line1---Line2",
                    "description": "Test with a string containing line breaks and a custom line separator."
                }
            ]
        }
    ]
}