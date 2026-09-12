{
    "group": "DoubleMetaphone Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": ""
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test when input string is empty, expecting empty string return as there is nothing to encode."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "A"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "A",
            "description": "Test when input string is a single vowel 'A', expecting 'A' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "?"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "S",
            "description": "Test when input string contains a character '?', expecting 'S' in primary encoding due to special handling."
        }
    ]
}