{
    "test_cases": [
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
                    "description": "Test with character 0xb7, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 720
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x2d0, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 721
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x2d1, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 903
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x387, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 1600
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x640, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 3662
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0xe46, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 6046
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0xec6, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12293
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x3005, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12337
                        }
                    ],
                    "expected": false,
                    "description": "Test with character less than 0x3031, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12345
                        }
                    ],
                    "expected": true,
                    "description": "Test with character between 0x3031 and 0x3035, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12350
                        }
                    ],
                    "expected": false,
                    "description": "Test with character less than 0x309d, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12381
                        }
                    ],
                    "expected": true,
                    "description": "Test with character between 0x309d and 0x309e, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12383
                        }
                    ],
                    "expected": false,
                    "description": "Test with character less than 0x30fc, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12386
                        }
                    ],
                    "expected": true,
                    "description": "Test with character between 0x30fc and 0x30fe, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12390
                        }
                    ],
                    "expected": false,
                    "description": "Test with character greater than 0x30fe, should return false."
                }
            ]
        }
    ]
}