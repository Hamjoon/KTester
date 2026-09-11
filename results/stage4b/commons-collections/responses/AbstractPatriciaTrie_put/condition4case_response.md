{
    "testCases": [
        {
            "group": "put method - key is null",
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
                    "description": "Test case where the key is null. Expecting a NullPointerException to be thrown."
                }
            ]
        },
        {
            "group": "put method - key length is zero",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": ""
                        },
                        {
                            "parameter": "value",
                            "value": "someValue"
                        }
                    ],
                    "expected": "someValue",
                    "description": "Test case where the key has zero length. If the root is empty, it should store the key-value pair."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": ""
                        },
                        {
                            "parameter": "value",
                            "value": "anotherValue"
                        }
                    ],
                    "expected": "anotherValue",
                    "description": "Test case where the key has zero length and the root is not empty. Expecting the method to update the value at the root."
                }
            ]
        },
        {
            "group": "put method - key found and empty",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "existingKey"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue"
                        }
                    ],
                    "expected": "newValue",
                    "description": "Test case where the key exists but is empty. Expecting the method to store the new key-value pair and increment size."
                }
            ]
        },
        {
            "group": "put method - key found and not empty",
            "cases": [
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
                    "expected": "updatedValue",
                    "description": "Test case where the key exists and is not empty. Expecting the method to update the value and increment mod count."
                }
            ]
        },
        {
            "group": "put method - new key with valid bit index",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "newKey"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a new key is added with a valid bit index. Expecting the method to add the entry and return null."
                }
            ]
        },
        {
            "group": "put method - new key with null bit key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithNullBit"
                        },
                        {
                            "parameter": "value",
                            "value": "value"
                        }
                    ],
                    "expected": "value",
                    "description": "Test case where a new key is added with a null bit key. Expecting the method to store the key-value pair at the root."
                }
            ]
        },
        {
            "group": "put method - new key with equal bit key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEqualBit"
                        },
                        {
                            "parameter": "value",
                            "value": "value"
                        }
                    ],
                    "expected": "value",
                    "description": "Test case where a new key is added with an equal bit key but not at the root. Expecting the method to update the value."
                }
            ]
        },
        {
            "group": "put method - invalid bit index",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "invalidKey"
                        },
                        {
                            "parameter": "value",
                            "value": "value"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test case where an invalid bit index is encountered. Expecting an IllegalArgumentException to be thrown."
                }
            ]
        }
    ]
}