{
    "test cases": [
        {
            "group": "put method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithNegativeBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNegativeBit"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test putting a key that results in a negative bit index should throw an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithPositiveBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForPositiveBit"
                        }
                    ],
                    "expected": "null",
                    "description": "Test putting a key that results in a valid positive bit index should create a new entry and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithSameValue"
                        },
                        {
                            "parameter": "value",
                            "value": "existingValue"
                        }
                    ],
                    "expected": "existingValue",
                    "description": "Test putting a key with the same value as an existing entry should return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithDifferentValue"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue"
                        }
                    ],
                    "expected": "null",
                    "description": "Test putting a new key that is not in the trie should create a new entry and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithNullValue"
                        },
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test putting a key with a null value should throw an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithLargeBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForLargeBit"
                        }
                    ],
                    "expected": "null",
                    "description": "Test putting a key that results in a valid large bit index should create a new entry and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEmptyString"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForEmptyString"
                        }
                    ],
                    "expected": "null",
                    "description": "Test putting a key that is an empty string should create a new entry in the root and return null."
                }
            ]
        }
    ]
}