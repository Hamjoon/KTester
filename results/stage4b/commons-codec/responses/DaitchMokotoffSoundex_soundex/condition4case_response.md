{
    "test cases": [
        {
            "group": "soundex",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": null
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input to ensure the method returns null."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Smith"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["S530"],
                    "description": "Test with a standard name input to verify correct soundex code is generated without branching."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Miller"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["M460"],
                    "description": "Test with a name that requires branching to check if multiple soundex codes are generated."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Jackson"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["J250"],
                    "description": "Test with a name that includes vowels and consonants to validate soundex generation with branching."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "John"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["J500"],
                    "description": "Test with a simple name to ensure soundex code is generated correctly without branching."
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
                            "value": "  "
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["0000"],
                    "description": "Test with input that contains only whitespace to ensure it returns a soundex code of zeros."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "B"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["B000"],
                    "description": "Test with a single character input to check if it returns the appropriate soundex code."
                }
            ]
        }
    ]
}