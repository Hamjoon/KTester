{
    "test cases": [
        {
            "group": "containsKey - Boundary Key Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key1."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key3."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": false,
                    "description": "Test if the method returns false when the key is null but all keys are non-null."
                }
            ]
        },
        {
            "group": "containsKey - Non-Existing Keys",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistentKey1"
                        }
                    ],
                    "expected": false,
                    "description": "Test if the method returns false when the key does not match any of the keys."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistentKey2"
                        }
                    ],
                    "expected": false,
                    "description": "Test if the method returns false when the key does not match any of the keys."
                }
            ]
        },
        {
            "group": "containsKey - Size Boundary Conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the map size is 1 and the key matches key1."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the map size is 2 and the key matches key2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the map size is 3 and the key matches key3."
                }
            ]
        }
    ]
}