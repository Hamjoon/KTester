{
    "group": "remove method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey"
                }
            ],
            "expected": "valueOfExistingKey",
            "description": "Test removal of a key when delegateMap is not null."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test removal of a key when size is 0."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value3",
            "description": "Test removal of a null key when size is 3 and key3 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value2",
            "description": "Test removal of a null key when size is 3 and key2 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value1",
            "description": "Test removal of a null key when size is 3 and key1 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "null",
            "description": "Test removal of a null key when size is 3 and no keys are null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value2",
            "description": "Test removal of a null key when size is 2 and key2 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value1",
            "description": "Test removal of a null key when size is 2 and key1 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "null",
            "description": "Test removal of a null key when size is 2 and no keys are null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                }
            ],
            "expected": "value1",
            "description": "Test removal of a null key when size is 1 and key1 is null."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key3"
                }
            ],
            "expected": "value3",
            "description": "Test removal of a key when size is 3 and key matches key3."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key2"
                }
            ],
            "expected": "value2",
            "description": "Test removal of a key when size is 3 and key matches key2."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key1"
                }
            ],
            "expected": "value1",
            "description": "Test removal of a key when size is 3 and key matches key1."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key2"
                }
            ],
            "expected": "value2",
            "description": "Test removal of a key when size is 2 and key matches key2."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key1"
                }
            ],
            "expected": "value1",
            "description": "Test removal of a key when size is 2 and key matches key1."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key1"
                }
            ],
            "expected": "value1",
            "description": "Test removal of a key when size is 1 and key matches key1."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistentKey"
                }
            ],
            "expected": "null",
            "description": "Test removal of a non-existent key when size is greater than 0."
        }
    ]
}