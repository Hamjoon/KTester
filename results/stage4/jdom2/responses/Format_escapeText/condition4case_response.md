{
    "testCases": [
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
                            "value": "Hello World"
                        }
                    ],
                    "expected": "Hello World",
                    "description": "Test with no characters needing escape and no EOL specified."
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
                            "value": "Hello & World"
                        }
                    ],
                    "expected": "Hello &amp; World",
                    "description": "Test with a character that needs escape."
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
                            "value": "Hello < World"
                        }
                    ],
                    "expected": "Hello &lt; World",
                    "description": "Test with a '<' character that needs escape."
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
                            "value": "Hello > World"
                        }
                    ],
                    "expected": "Hello &gt; World",
                    "description": "Test with a '>' character that needs escape."
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
                            "value": "Hello \r\n World"
                        }
                    ],
                    "expected": "Hello &#xD;\n World",
                    "description": "Test with a carriage return and a newline character."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "eol",
                            "value": "\n"
                        },
                        {
                            "parameter": "value",
                            "value": "Hello\nWorld"
                        }
                    ],
                    "expected": "Hello\nWorld",
                    "description": "Test with EOL specified and shouldEscape always returning true."
                },
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
                            "value": "Hello World"
                        }
                    ],
                    "expected": "Hello World",
                    "description": "Test with no characters needing escape and EOL specified."
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
                    "description": "Test with a null character, expecting IllegalDataException."
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
                    "description": "Test with a character that is not normally escaped but should be escaped according to strategy."
                }
            ]
        }
    ]
}