{
    "test cases": [
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
                    "expected": "Node object associated with 'existingKey'",
                    "description": "Test case where the key 'existingKey' already exists in the map and create is false. The method should return the existing node."
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
                    "expected": null,
                    "description": "Test case where the key 'nonExistingKey' does not exist in the map and create is false. The method should return null."
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
                            "value": "nonExistingKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object associated with 'nonExistingKey'",
                    "description": "Test case where the key 'nonExistingKey' does not exist in the map and create is true. The method should create and return a new node associated with 'nonExistingKey'."
                }
            ]
        },
        {
            "group": "find method with null key and create true",
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
                    "expected": "Node object associated with null key",
                    "description": "Test case where the key is null and create is true. The method should create and return a new node associated with the null key."
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
                    "description": "Test case where the key 'nonComparableKey' is not comparable and create is true. The method should throw a ClassCastException."
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
                    "expected": "Node object associated with 'existingKey'",
                    "description": "Test case where the key 'existingKey' already exists in the map and create is true. The method should return the existing node without creating a new one."
                }
            ]
        }
    ]
}