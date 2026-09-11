{
    "group": "soundex",
    "cases": [
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "12345"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["0000"],
            "description": "Test with a numeric input to ensure it returns a soundex code of zeros."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": ""
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["0000"],
            "description": "Test with an empty string to ensure it returns a soundex code of zeros."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": " "
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["0000"],
            "description": "Test with a string containing only a space to ensure it returns a soundex code of zeros."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "A B"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["A000"],
            "description": "Test with a string containing a vowel and a space to check if it returns the appropriate soundex code."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "B C"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["B000"],
            "description": "Test with a string containing two consonants separated by a space to check if it returns the appropriate soundex code."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "Mann"
                },
                {
                    "parameter": "branching",
                    "value": true
                }
            ],
            "expected": ["M500"],
            "description": "Test with a name that has repeated characters to verify soundex handles it correctly with branching."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "Mann"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["M500"],
            "description": "Test with a name that has repeated characters to verify soundex handles it correctly without branching."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "John"
                },
                {
                    "parameter": "branching",
                    "value": true
                }
            ],
            "expected": ["J500"],
            "description": "Test with a simple name to ensure soundex code is generated correctly with branching."
        }
    ]
}