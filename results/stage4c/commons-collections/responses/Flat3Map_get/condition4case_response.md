{
    "group": "Flat3Map.get(Object) Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "value3",
            "description": "Test case where the key is null and size is 3, expecting value3 when key3 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "value2",
            "description": "Test case where the key is null and size is 2, expecting value2 when key2 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "value1",
            "description": "Test case where the key is null and size is 1, expecting value1 when key1 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "null",
            "description": "Test case where the key is null and none of the keys are null, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key3"
                }
            ],
            "expected": "value3",
            "description": "Test case where the size is 3, expecting value3 when key3 matches."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key2"
                }
            ],
            "expected": "value2",
            "description": "Test case where the size is 2, expecting value2 when key2 matches."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key1"
                }
            ],
            "expected": "value1",
            "description": "Test case where the size is 1, expecting value1 when key1 matches."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistentKey"
                }
            ],
            "expected": "null",
            "description": "Test case where the key does not match any keys in the map, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyInDelegateMap"
                }
            ],
            "expected": "valueInDelegateMap",
            "description": "Test case where the delegateMap is not null, expecting the value from delegateMap."
        }
    ]
}