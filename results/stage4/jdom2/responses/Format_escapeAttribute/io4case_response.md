{
    "test cases": [
        {
            "group": "escapeAttribute Additional Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return true; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "Hello <World> & \"Everyone\""
                        }
                    ],
                    "expected": "Hello &lt;World&gt; &amp; &quot;Everyone&quot;",
                    "description": "Test with a string containing '<', '>', '&', and '\"', should escape all special characters."
                },
                {
                    "input": [
                        {
                            "parameter": "strategy",
                            "value": "new EscapeStrategy() { public boolean shouldEscape(char ch) { return false; } }"
                        },
                        {
                            "parameter": "value",
                            "value": "No escaping needed."
                        }
                    ],
                    "expected": "No escaping needed.",
                    "description": "Test with a string that does not require any escaping, should return the original string."
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
                            "value": "\u0000D800" 	}
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
                            "value": "\u0000DC00" 
                        }
                    ],
                    "expected": "throws IllegalDataException",
                    "description": "Test with a low surrogate without a preceding high surrogate, should throw IllegalDataException."
                }
            ]
        }
    ]
}