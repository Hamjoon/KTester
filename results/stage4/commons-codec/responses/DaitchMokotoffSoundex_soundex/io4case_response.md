{
    "test cases": [
        {
            "group": "soundex method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "O'Connor"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["O250"],
                    "description": "Test with a name 'O'Connor'; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "McDonald"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["M350"],
                    "description": "Test with a name 'McDonald' using branching; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Aarons"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["A652"],
                    "description": "Test with a name 'Aarons'; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Nash"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["N020"],
                    "description": "Test with a name 'Nash' using branching; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Lee"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["L000"],
                    "description": "Test with a name 'Lee'; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Smith"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["S530"],
                    "description": "Test with a common name 'Smith' using branching; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Hannah"
                        },
                        {
                            "parameter": "branching",
                            "value": false
                        }
                    ],
                    "expected": ["H500"],
                    "description": "Test with a name 'Hannah'; should return the expected Soundex code."
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
                    "description": "Test with a name 'Mann' without branching; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Nolan"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["N450"],
                    "description": "Test with a name 'Nolan' using branching; should return the expected Soundex code."
                }
            ]
        }
    ]
}