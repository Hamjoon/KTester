{
    "group": "Additional escapeAttribute Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Surrogate\u001DPair"
                }
            ],
            "expected": "Surrogate&#x1D;Pair",
            "description": "Test with a string containing a single surrogate character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u001D\u001E"
                }
            ],
            "expected": "&#x1D;&#x1E;",
            "description": "Test with a string containing multiple surrogate characters. Should escape these characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "HighSurrogate\u001D"
                }
            ],
            "expected": "HighSurrogate&#x1D;",
            "description": "Test with a string containing a high surrogate character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "LowSurrogate\u001E"
                }
            ],
            "expected": "LowSurrogate&#x1E;",
            "description": "Test with a string containing a low surrogate character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "CustomEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Hello !"
                }
            ],
            "expected": "Hello &#x21;",
            "description": "Test with a custom escape strategy that escapes '!' character. Should escape this character."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "CustomEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "Test !@#"
                }
            ],
            "expected": "Test &#x21;@#",
            "description": "Test with a custom escape strategy that escapes '!' character and leaves others. Should escape '!' only."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "MixedContent<&>\"'\n"
                }
            ],
            "expected": "MixedContent&lt;&amp;&gt;&quot;&#xA;",
            "description": "Test with a string containing multiple special characters. Should escape all special characters."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "NoEscapeNeeded"
                }
            ],
            "expected": "NoEscapeNeeded",
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
                    "value": "\u0000\u0001\u0002"
                }
            ],
            "expected": "&#x0;&#x1;&#x2;",
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
                    "value": "\u0000"
                }
            ],
            "expected": "&#x0;",
            "description": "Test with a string containing a null character. Should escape this character."
        }
    ]
}