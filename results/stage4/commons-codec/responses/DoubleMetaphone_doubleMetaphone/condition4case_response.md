{
    "testCases": [
        {
            "group": "doubleMetaphone",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input value should return null."
                },
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
                    "description": "Test with empty string should return empty primary result."
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
                    "description": "Test with single vowel character 'A' should return 'A' as primary."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "B"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "P",
                    "description": "Test with single consonant character 'B' should return 'P' as primary."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "C"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "C",
                    "description": "Test with single consonant character 'C' for alternate should return 'C'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SCHMIDT"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "S",
                    "description": "Test with name 'SCHMIDT' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "SCHMIDT"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "S",
                    "description": "Test with name 'SCHMIDT' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "KNIGHT"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "N",
                    "description": "Test with name 'KNIGHT' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "KNIGHT"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "N",
                    "description": "Test with name 'KNIGHT' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'REILLY"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "O",
                    "description": "Test with name 'O'REILLY' should return appropriate primary encoding."
                }
            ]
        }
    ]
}