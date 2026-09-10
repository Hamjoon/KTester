{
    "test cases": [
        {
            "group": "find method with non-existing key and create false (boundary case)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "boundaryKey"
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
            "group": "find method with non-existing key and create true (boundary case)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "boundaryKey"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object for boundaryKey",
                    "description": "Test case where the key does not exist and create is true, expecting a new node to be created and returned."
                }
            ]
        },
        {
            "group": "find method with existing key and create true (boundary case)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "existingKeyBoundary"
                        },
                        {
                            "parameter": "create",
                            "value": true
                        }
                    ],
                    "expected": "Node object corresponding to existingKeyBoundary",
                    "description": "Test case where the key exists and create is true, expecting to retrieve the existing node instead of creating a new one."
                }
            ]
        },
        {
            "group": "find method with null key and allowNullValues true (boundary case)",
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
            "group": "find method with null key and allowNullValues false (boundary case)",
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
            "group": "find method with non-comparable key (boundary case)",
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
        }
    ]
}