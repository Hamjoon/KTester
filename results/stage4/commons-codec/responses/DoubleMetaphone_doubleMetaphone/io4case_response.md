{
    "testCases": [
        {
            "group": "doubleMetaphone",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "ALEX"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "ALEX",
                    "description": "Test with name 'ALEX' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "ALEX"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "ALEX",
                    "description": "Test with name 'ALEX' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "O'REILLY"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "O",
                    "description": "Test with name 'O'REILLY' for alternate should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "BROWN"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "P",
                    "description": "Test with name 'BROWN' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "BROWN"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "P",
                    "description": "Test with name 'BROWN' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "JONES"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "J",
                    "description": "Test with name 'JONES' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "JONES"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "J",
                    "description": "Test with name 'JONES' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CHURCH"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "C",
                    "description": "Test with name 'CHURCH' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "CHURCH"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "C",
                    "description": "Test with name 'CHURCH' should return appropriate alternate encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "XAVIER"
                        },
                        {
                            "parameter": "alternate",
                            "value": false
                        }
                    ],
                    "expected": "S",
                    "description": "Test with name 'XAVIER' should return appropriate primary encoding."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "XAVIER"
                        },
                        {
                            "parameter": "alternate",
                            "value": true
                        }
                    ],
                    "expected": "S",
                    "description": "Test with name 'XAVIER' should return appropriate alternate encoding."
                }
            ]
        }
    ]
}