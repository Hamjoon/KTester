{
    "testCases": [
        {
            "group": "escapeText with no characters needing escape",
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
                            "value": "Hello World"
                        }
                    ],
                    "expected": "Hello World",
                    "description": "Test with a string that does not contain any characters that need escaping."
                }
            ]
        },
        {
            "group": "escapeText with characters needing escape",
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
                            "value": "Hello <World>"
                        }
                    ],
                    "expected": "Hello &lt;World&gt;",
                    "description": "Test with a string that contains '<' and '>' which need to be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return ch == 'W'; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "Hello World"
                        }
                    ],
                    "expected": "Hello &amp;lt;W&amp;gt;orld",
                    "description": "Test with a string that contains a character 'W' which should be escaped based on strategy."
                }
            ]
        },
        {
            "group": "escapeText with line breaks",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": "\n"
                        },
                        {
                            "parameter": "value",
                            "value": "Line1\nLine2"
                        }
                    ],
                    "expected": "Line1\nLine2",
                    "description": "Test with a string that contains line breaks and a specified eol."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": "\r\n"
                        },
                        {
                            "parameter": "value",
                            "value": "Line1\rLine2"
                        }
                    ],
                    "expected": "Line1\r\nLine2",
                    "description": "Test with a string that contains carriage returns and a specified eol."
                }
            ]
        },
        {
            "group": "escapeText with surrogate pairs",
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
                            "value": "\u0001\u0002" 	}
                    ],
                    "expected": "&#x1;&#x2;",
                    "description": "Test with a string containing characters that should be escaped as they are not printable."
                }
            ]
        },
        {
            "group": "escapeText with illegal surrogate pairs",
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
                            "value": "\u007F\u007F" 	}
                    ],
                    "expected": "&#x7F;&#x7F;",
                    "description": "Test with a string containing illegal surrogate pairs that should throw an IllegalDataException."
                }
            ]
        }
    ]
}