{
    "testCases": [
        {
            "group": "put method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "someValue"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test putting a null key should throw a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "testKey"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForRoot"
                        }
                    ],
                    "expected": "valueForRoot",
                    "description": "Test putting a key with zero bits when the root is empty should store the key and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "testKey"
                        },
                        {
                            "parameter": "value",
                            "value": "existingValue"
                        }
                    ],
                    "expected": "existingValue",
                    "description": "Test putting a key with zero bits when the root is not empty should update the root's value and return the existing value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "anotherKey"
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
                            "value": "existingKey"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValue"
                        }
                    ],
                    "expected": "oldValue",
                    "description": "Test putting a key that already exists should update its value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithNullBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNullBit"
                        }
                    ],
                    "expected": "null",
                    "description": "Test putting a key with a null bit index should store it in the root and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEqualBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForEqualBit"
                        }
                    ],
                    "expected": "existingValue",
                    "description": "Test putting a key with an equal bit index to an existing entry should update that entry and return its old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithOutOfBoundsBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForOutOfBounds"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test putting a key that results in an out-of-bounds bit index should throw an IllegalArgumentException."
                }
            ]
        }
    ]
}