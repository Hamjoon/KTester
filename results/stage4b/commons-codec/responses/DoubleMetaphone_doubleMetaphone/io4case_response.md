{
    "testCases": [
        {
            "group": "doubleMetaphone",
            "cases": [
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
                    "description": "Test with a single vowel 'A', expect primary encoding to be 'A'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "A"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "A",
                    "description": "Test with a single vowel 'A', expect alternate encoding to also return 'A'."
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
                    "description": "Test with a single consonant 'B', expect primary encoding to be 'P'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "B"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "P",
                    "description": "Test with a single consonant 'B', expect alternate encoding to also return 'P'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Aardvark"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "A634",
                    "description": "Test with a name 'Aardvark', expect primary encoding to be 'A634'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Aardvark"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "A634",
                    "description": "Test with a name 'Aardvark', expect alternate encoding to also return 'A634'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Schmidt"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "S353",
                    "description": "Test with a name 'Schmidt', expect primary encoding to be 'S353'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Schmidt"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "S353",
                    "description": "Test with a name 'Schmidt', expect alternate encoding to also return 'S353'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'Reilly"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "A643",
                    "description": "Test with a name containing an apostrophe 'O'Reilly', expect primary encoding to be 'A643'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'Reilly"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "A643",
                    "description": "Test with a name containing an apostrophe 'O'Reilly', expect alternate encoding to also return 'A643'."
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
                    "description": "Test with an empty string, expect the method to return an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": ""
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string, expect the alternate encoding to also return an empty string."
                }
            ]
        }
    ]
}