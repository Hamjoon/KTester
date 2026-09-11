{
    "test cases": [
        {
            "group": "find method with null key and create false",
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
                    "expected": null,
                    "description": "Test case where the key is null and create is false. The method should return null as no node can be found for a null key."
                }
            ]
        },
        {
            "group": "find method with existing key and create false on empty map",
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
                    "expected": null,
                    "description": "Test case where the key 'existingKey' does not exist in an empty map and create is false. The method should return null."
                }
            ]
        },
        {
            "group": "find method with existing key and create true on empty map",
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
                    "description": "Test case where the key 'existingKey' does not exist in an empty map and create is true. The method should create and return a new node associated with 'existingKey'."
                }
            ]
        },
        {
            "group": "find method with existing key and create true with duplicate",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "duplicateKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object associated with 'duplicateKey'",
                    "description": "Test case where the key 'duplicateKey' is created when it already exists. The method should return the existing node associated with 'duplicateKey' without creating a new one."
                }
            ]
        },
        {
            "group": "find method with non-comparable key and create false",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonComparableKey"
                        },
                        {
                            "parameter": "create",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the key 'nonComparableKey' is not comparable and create is false. The method should return null as the key cannot be compared."
                }
            ]
        },
        {
            "group": "find method with non-comparable key and create true",
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
        }
    ]
}