{
    "group": "Pattern Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^test$"
                }
            ],
            "expected": "input -> input.equals(\"test\")",
            "description": "Exact match with both start and end anchors and non-empty content."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^$"
                }
            ],
            "expected": "input -> input.length() == 0",
            "description": "Exact match with both start and end anchors and empty content."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^"
                }
            ],
            "expected": "ALL_STRINGS_RMATCHER",
            "description": "Matches every string with start anchor and empty content."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "$"
                }
            ],
            "expected": "ALL_STRINGS_RMATCHER",
            "description": "Matches every string with end anchor and empty content."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^start"
                }
            ],
            "expected": "input -> startsWith(input, \"start\")",
            "description": "Matches strings that start with a specific substring."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "end$"
                }
            ],
            "expected": "input -> endsWith(input, \"end\")",
            "description": "Matches strings that end with a specific substring."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^[abc]$"
                }
            ],
            "expected": "input -> (input.length() == 1) && (contains(\"abc\", input.charAt(0)) == true)",
            "description": "Exact match with character class containing alternatives."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^[^abc]$"
                }
            ],
            "expected": "input -> (input.length() == 1) && (contains(\"abc\", input.charAt(0)) == false)",
            "description": "Exact match with negated character class."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^[abc]"
                }
            ],
            "expected": "input -> (input.length() > 0) && (contains(\"abc\", input.charAt(0)) == true)",
            "description": "Matches strings starting with a character from a set."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[abc]$"
                }
            ],
            "expected": "input -> (input.length() > 0) && (contains(\"abc\", input.charAt(input.length() - 1)) == true)",
            "description": "Matches strings ending with a character from a set."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": ".*"
                }
            ],
            "expected": "new RPattern() { Pattern pattern = Pattern.compile(\".*\"); ... }",
            "description": "General pattern compilation for any regex not covered by specific conditions."
        }
    ]
}