{
    "group": "Flat3Map.containsKey",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey1"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 2, and the key does not match key2 or key3."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey1"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 3, and the key does not match key2 or key3."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey2"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 1, and the key does not match key1."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey2"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 3, and the key does not match key1 or key3."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey3"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 1, and the key does not match key1."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey3"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 2, and the key does not match key1 or key2."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "false",
            "description": "Test when key is null and size is 0, should return false."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistingKey"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null and size is 0, should return false."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey1"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 0, should return false."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey2"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 0, should return false."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey3"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 0, should return false."
        }
    ]
}