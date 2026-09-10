{
    "test cases": [
        {
            "group": "escapeAttribute Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "Hello World"
                        }
                    ],
                    "expected": "Hello World",
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
                            "value": "Hello & Goodbye"
                        }
                    ],
                    "expected": "Hello &amp; Goodbye",
                    "description": "Test with a string containing '&' that should be escaped."
                }
            ]
        },
        {
            "group": "escapeAttribute Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string, should return an empty string."
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
                    "description": "Test with a null character, should be escaped."
                }
            ]
        },
        {
            "group": "escapeAttribute Special Characters",
            "cases": [
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
                    "description": "Test with a string containing '<' and '>', should escape both."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\"Quote\""
                        }
                    ],
                    "expected": "&quot;Quote&quot;",
                    "description": "Test with a string containing quotes, should escape both."
                }
            ]
        },
        {
            "group": "escapeAttribute Surrogate Pairs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u0000D800\u0000DC00"
                        }
                    ],
                    "expected": "throws IllegalDataException",
                    "description": "Test with an incomplete surrogate pair, should throw IllegalDataException."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u0000D800\u0000DC00"
                        }
                    ],
                    "expected": "&#x10000;",
                    "description": "Test with a valid surrogate pair, should return the encoded value."
                }
            ]
        }
    ]
}