{
    "testCases": [
        {
            "group": "find method with existing key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "existingKey"
                        },
                        {
                            "parameter": "create",
                            "value": false
                        }
                    ],
                    "expected": "Node object corresponding to existingKey",
                    "description": "Test case where the key exists in the tree, expecting to retrieve the corresponding node."
                }
            ]
        },
        {
            "group": "find method with non-existing key and create false",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistingKey"
                        },
                        {
                            "parameter": "create",
                            "value": false
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the key does not exist and create is false, expecting a null return."
                }
            ]
        },
        {
            "group": "find method with non-existing key and create true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "newKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object for newKey",
                    "description": "Test case where the key does not exist and create is true, expecting a new node to be created and returned."
                }
            ]
        },
        {
            "group": "find method with null key and allowNullValues true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object for null key",
                    "description": "Test case where the key is null and allowNullValues is true, expecting a new node for the null key to be created and returned."
                }
            ]
        },
        {
            "group": "find method with null key and allowNullValues false",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "create",
                            "value": false
                        }
                    ],
                    "expected": "ClassCastException",
                    "description": "Test case where the key is null and allowNullValues is false, expecting a ClassCastException to be thrown."
                }
            ]
        },
        {
            "group": "find method with non-comparable key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonComparableKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "ClassCastException",
                    "description": "Test case where the key is not comparable and create is true, expecting a ClassCastException to be thrown."
                }
            ]
        },
        {
            "group": "find method with existing key and create true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "existingKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object corresponding to existingKey",
                    "description": "Test case where the key exists and create is true, expecting to retrieve the existing node instead of creating a new one."
                }
            ]
        }
    ]
}