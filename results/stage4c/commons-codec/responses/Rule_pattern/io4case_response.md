{
    "group": "Additional Pattern Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^[abc]"
                }
            ],
            "expected": "input -> (input.length() > 0) && (contains(\"abc\", input.charAt(0)) == true)",
            "description": "Matches strings starting with a character from a set, already covered."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[^abc]$"
                }
            ],
            "expected": "input -> (input.length() > 0) && (contains(\"abc\", input.charAt(input.length() - 1)) == false)",
            "description": "Matches strings ending with a character not in a set."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[abc]"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"[abc]\"); ... }",
            "description": "General pattern compilation for character set without anchors."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^a.*b$"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"^a.*b$\"); ... }",
            "description": "Complex pattern with start and end anchors and wildcard in between."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-z]{3,}"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"[a-z]{3,}\"); ... }",
            "description": "Pattern with quantifiers for matching sequences of at least 3 lowercase letters."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "(abc|def)"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"(abc|def)\"); ... }",
            "description": "Pattern with alternation for matching either 'abc' or 'def'."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "\\d+"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"\\d+\"); ... }",
            "description": "Pattern for matching one or more digits."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^\\s*$"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\"^\\s*$\"); ... }",
            "description": "Pattern for matching strings that consist only of whitespace."
        }
    ]
}