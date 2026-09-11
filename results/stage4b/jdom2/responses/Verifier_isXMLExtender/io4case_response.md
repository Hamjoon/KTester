{
    "test cases": [
        {
            "group": "isXMLExtender",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 182
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character less than 0xb6, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 184
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character between 0xb6 and 0xb7, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12500
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character between 0x30fe and 0x7fffffff, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12339
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x3036, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12340
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x3037, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12341
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x3038, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12342
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x3039, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12415
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x309f, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12416
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x30a0, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12429
                        }
                    ],
                    "expected": false,
                    "description": "Test with a character 0x30fb, should return false."
                }
            ]
        }
    ]
}