{
    "result": [
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
                            "value": "\u000000D800\u000000DC00"
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
                            "value": "\u000000D800\u000000DC00"
                        }
                    ],
                    "expected": "throws IllegalDataException",
                    "description": "Test with a valid surrogate pair, should throw IllegalDataException."
                }
            ]
        },
        {
            "group": "escapeAttribute Invalid Surrogate Pairs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u000000D800"
                        }
                    ],
                    "expected": "throws IllegalDataException",
                    "description": "Test with a high surrogate without a low surrogate, should throw IllegalDataException."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u000000DC00"
                        }
                    ],
                    "expected": "throws IllegalDataException",
                    "description": "Test with a low surrogate without a preceding high surrogate, should throw IllegalDataException."
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
            "group": "escapeAttribute Boundary Character Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u007F"
                        }
                    ],
                    "expected": "&#x7F;",
                    "description": "Test with a DEL character, should be escaped."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "\u001A"
                        }
                    ],
                    "expected": "&#x1A;",
                    "description": "Test with a control character (SUB), should be escaped."
                }
            ]
        }
    ]
}