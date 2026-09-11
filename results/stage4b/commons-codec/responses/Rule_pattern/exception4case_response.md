{
    "group": "Testing pattern method for invalid regex",
    "cases": [
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-z"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test for an invalid regex pattern that is missing a closing bracket. Should throw a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "(abc"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test for an invalid regex pattern that is missing a closing parenthesis. Should throw a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": ".*["
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test for an invalid regex pattern that is missing a closing bracket in a character class. Should throw a PatternSyntaxException."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": ".*\\"
                }
            ],
            "expected": "PatternSyntaxException",
            "description": "Test for an invalid regex pattern that ends with an escape character. Should throw a PatternSyntaxException."
        }
    ]
}