{
    "output": [
        {
            "group": "escapeText with high surrogate character followed by low surrogate",
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
                    "description": "Test with a valid high surrogate followed by a low surrogate character, expecting them to be escaped correctly."
                }
            ]
        },
        {
            "group": "escapeText with truncated surrogate pair",
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
                            "value": "\u0000D" 
                        }
                    ],
                    "expected": "IllegalDataException",
                    "description": "Test with a string containing only a high surrogate character, expecting an IllegalDataException to be thrown due to a truncated surrogate pair."
                }
            ]
        }
    ]
}