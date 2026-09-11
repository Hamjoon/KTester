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
                    "description": "Test with null input value, expect the method to return null."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Smith"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "S530",
                    "description": "Test with a regular name 'Smith', expect primary encoding to be 'S530'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Smith"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "S530",
                    "description": "Test with a regular name 'Smith', expect alternate encoding to also return 'S530'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'Connor"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "A245",
                    "description": "Test with name containing an apostrophe 'O'Connor', expect primary encoding to be 'A245'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'Connor"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "A245",
                    "description": "Test with name containing an apostrophe 'O'Connor', expect alternate encoding to also return 'A245'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Bach"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "P200",
                    "description": "Test with name 'Bach', expect primary encoding to be 'P200'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Bach"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "P200",
                    "description": "Test with name 'Bach', expect alternate encoding to also return 'P200'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Xavier"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "S140",
                    "description": "Test with name 'Xavier', expect primary encoding to be 'S140'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Xavier"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "S140",
                    "description": "Test with name 'Xavier', expect alternate encoding to also return 'S140'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Knox"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "K520",
                    "description": "Test with name 'Knox', expect primary encoding to be 'K520'."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "Knox"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "K520",
                    "description": "Test with name 'Knox', expect alternate encoding to also return 'K520'."
                }
            ]
        }
    ]
}