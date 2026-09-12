{
    "group": "LinkedTreeMap_find_method_tests",
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
            "expected": "Node with key 'existingKey'",
            "description": "Test case where the key exists in the tree and create is false. Should return the existing node."
        },
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
            "description": "Test case where the key does not exist in the tree and create is false. Should return null."
        },
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
            "expected": "Newly created node with key 'nonExistingKey'",
            "description": "Test case where the key does not exist in the tree and create is true. Should create and return a new node."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "comparableKey"
                },
                {
                    "parameter": "create",
                    "value": true
                }
            ],
            "expected": "ClassCastException if key is not Comparable",
            "description": "Test case where the key is not comparable and create is true. Should throw ClassCastException."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "comparableKey"
                },
                {
                    "parameter": "create",
                    "value": false
                }
            ],
            "expected": "null",
            "description": "Test case where the key is not comparable and create is false. Should return null without exception."
        }
    ]
}