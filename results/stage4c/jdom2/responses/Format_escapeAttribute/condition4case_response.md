{
    "group": "escapeAttribute Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Hello World"
                }
            ],
            "expected": "Hello World",
            "description": "Test with a string that contains no special characters requiring escape. Should return the original string."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "<tag>"
                }
            ],
            "expected": "&lt;tag&gt;",
            "description": "Test with a string containing '<' and '>'. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Hello & Goodbye"
                }
            ],
            "expected": "Hello &amp; Goodbye",
            "description": "Test with a string containing '&'. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Line1\rLine2"
                }
            ],
            "expected": "Line1&#xD;Line2",
            "description": "Test with a string containing carriage return '\\r'. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Line1\nLine2"
                }
            ],
            "expected": "Line1&#xA;Line2",
            "description": "Test with a string containing newline '\\n'. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Tab\tCharacter"
                }
            ],
            "expected": "Tab&#x9;Character",
            "description": "Test with a string containing a tab '\\t'. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Quote\"Here"
                }
            ],
            "expected": "Quote&quot;Here",
            "description": "Test with a string containing double quotes '\"'. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "CustomEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "CustomChar!"
                }
            ],
            "expected": "CustomChar&#x21;",
            "description": "Test with a custom escape strategy that escapes '!' character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F"
                }
            ],
            "expected": "&#x1F;",
            "description": "Test with a string containing a non-printable ASCII character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000"
                }
            ],
            "expected": "&#x0;",
            "description": "Test with a string containing a null character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters in succession. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters in succession. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F\u001F\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters in succession. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F\u001F\u001F\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters in succession. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001F\u001F\u001F\u001F\u001F\u001F\u001F"
                }
            ],
            "expected": "&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;&#x1F;",
            "description": "Test with a string containing multiple non-printable ASCII characters in succession. Should escape these characters."
        }
    ]
}