{
    "testCases": [
        {
            "group": "soundex method tests",
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
                    "description": "Test with null input; should return null."
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
                    "description": "Test with empty string; should return an array with one element '0000'."
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
                    "description": "Test with a common name 'Smith'; should return the expected Soundex code."
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
                    "description": "Test with a name 'Miller' using branching; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Knott"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["K530"],
                    "description": "Test with a name 'Knott' using branching; should return the expected Soundex code."
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
                    "description": "Test with a name 'Mann' which has 'm' and 'n'; should return the expected Soundex code."
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
                    "description": "Test with a common name 'John'; should return the expected Soundex code."
                },
                {
                    "input": [
                        {
                            "parameter": "source",
                            "value": "Avery"
                        },
                        {
                            "parameter": "branching",
                            "value": true
                        }
                    ],
                    "expected": ["A610"],
                    "description": "Test with a name 'Avery' using branching; should return the expected Soundex code."
                }
            ]
        }
    ]
}