{
    "group": "Flat3Map.containsKey",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "true",
            "description": "Test when key is null and size is 1, 2, or 3, and one of the keys in the map is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "false",
            "description": "Test when key is null and size is 1, 2, or 3, but none of the keys in the map are null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey1"
                }
            ],
            "expected": "true",
            "description": "Test when key is not null, size is 1, and the key matches key1 with the same hash code."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey2"
                }
            ],
            "expected": "true",
            "description": "Test when key is not null, size is 2, and the key matches key2 with the same hash code."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey3"
                }
            ],
            "expected": "true",
            "description": "Test when key is not null, size is 3, and the key matches key3 with the same hash code."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistingKey"
                }
            ],
            "expected": "false",
            "description": "Test when key is not null, size is 1, 2, or 3, and the key does not match any of the keys in the map."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "anyKey"
                }
            ],
            "expected": "false",
            "description": "Test when size is 0, regardless of the key being null or not, should return false."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "delegateKey"
                }
            ],
            "expected": "delegateMap.containsKey(delegateKey)",
            "description": "Test when delegateMap is not null, should delegate the containsKey call to the delegateMap."
        }
    ]
}