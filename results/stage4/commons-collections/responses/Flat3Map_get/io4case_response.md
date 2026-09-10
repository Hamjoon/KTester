{
    "test cases": [
        {
            "group": "get method with null key",
            "cases": [
                {
                    "input": [],
                    "expected": "value3",
                    "description": "Test case where size is 3 and key3 is null, expecting value3 to be returned."
                },
                {
                    "input": [],
                    "expected": "value2",
                    "description": "Test case where size is 2 and key2 is null, expecting value2 to be returned."
                },
                {
                    "input": [],
                    "expected": "value1",
                    "description": "Test case where size is 1 and key1 is null, expecting value1 to be returned."
                },
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test case where size is 0, expecting null to be returned."
                }
            ]
        },
        {
            "group": "get method with non-null key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": "value3",
                    "description": "Test case where size is 3, key matches key3, expecting value3 to be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "anotherKey"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test case where size is 2, key matches key2, expecting value2 to be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "yetAnotherKey"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test case where size is 1, key matches key1, expecting value1 to be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonMatchingKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where size is greater than 0, but key does not match any keys, expecting null to be returned."
                }
            ]
        },
        {
            "group": "get method with boundary conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value3",
                    "description": "Test case where size is 3, key3 is null, expecting value3 to be returned when key is null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value2",
                    "description": "Test case where size is 2, key2 is null, expecting value2 to be returned when key is null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value1",
                    "description": "Test case where size is 1, key1 is null, expecting value1 to be returned when key is null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where size is 0, expecting null to be returned when key is null."
                }
            ]
        }
    ]
}