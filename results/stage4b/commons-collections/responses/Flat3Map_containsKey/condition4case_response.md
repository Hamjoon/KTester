{
    "test cases": [
        {
            "group": "Test containsKey with delegateMap",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": true,
                    "description": "Test when delegateMap is not null and contains the key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test when delegateMap is not null and does not contain the key."
                }
            ]
        },
        {
            "group": "Test containsKey with null key",
            "cases": [
                {
                    "input": [],
                    "expected": true,
                    "description": "Test when size is 3 and key3 is null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test when size is 2 and key2 is null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test when size is 1 and key1 is null."
                },
                {
                    "input": [],
                    "expected": false,
                    "description": "Test when size is 0."
                }
            ]
        },
        {
            "group": "Test containsKey with non-null key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 3, hash3 matches and key3 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 2, hash2 matches and key2 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 1, hash1 matches and key1 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 3, hash3 does not match or key3 does not equal the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 2, hash2 does not match or key2 does not equal the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 1, hash1 does not match or key1 does not equal the provided key."
                }
            ]
        }
    ]
}