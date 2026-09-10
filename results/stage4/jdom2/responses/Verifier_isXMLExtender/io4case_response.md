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
                    "description": "Test with a character 0xb6, should return false as it is not an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12336
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x3030, should return false as it is not an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12331
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x3035, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12380
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x309c, should return false as it is not an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12397
                        }
                    ],
                    "expected": true,
                    "description": "Test with character 0x30fd, should return true as it is an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 12391
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x30ff, should return false as it is not an extender."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 0
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0, should return false as it is less than 0xb6."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 127999
                        }
                    ],
                    "expected": false,
                    "description": "Test with character 0x1F3FF, should return false as it is greater than 0x30fe."
                }
            ]
        }
    ]
}