{
    "group": "Pattern Method Exception Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test scenario where the regex parameter is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "(unclosed"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test scenario where the regex contains an unclosed parenthesis, expecting a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[unterminated"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test scenario where the regex contains an unterminated character class, expecting a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "\\"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test scenario where the regex ends with a single backslash, expecting a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "(?invalid)"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test scenario where the regex contains an invalid group syntax, expecting a PatternSyntaxException."
        }
    ]
}