{
    "testCases": [
        {
            "group": "isXMLExtender",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 0
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character less than 0xb6, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 183
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0xb7, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 720
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x2d0, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 721
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x2d1, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 903
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x387, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 1600
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x640, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 3654
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0xe46, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 6046
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0xec6, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12293
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x3005, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12337
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x3031, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12338
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x3035, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12413
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x309d, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12414
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x309e, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12430
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x30fc, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12431
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x30fe, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12432
                        }
                    ],
                    "expected": false,
                    "description": "Test with character greater than 0x30fe, should return false."
                }
            ]
        }
    ]
}