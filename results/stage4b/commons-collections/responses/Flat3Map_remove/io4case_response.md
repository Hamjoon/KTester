{
    "test cases": [
        {
            "group": "remove when key is null and size is 3 - key3 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value3",
                    "description": "Test removing null key when size is 3 and key3 is not null, should return value3."
                }
            ]
        },
        {
            "group": "remove when key is null and size is 3 - key2 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removing null key when size is 3 and key2 is not null, should return value2."
                }
            ]
        },
        {
            "group": "remove when key is null and size is 3 - key1 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing null key when size is 3 and key1 is not null, should return value1."
                }
            ]
        },
        {
            "group": "remove when key is null and size is 2 - key2 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removing null key when size is 2 and key2 is not null, should return value2."
                }
            ]
        },
        {
            "group": "remove when key is null and size is 2 - key1 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing null key when size is 2 and key1 is not null, should return value1."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 1 - invalid key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "invalidKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test removing an invalid key when size is 1, should return null."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 1 - valid key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing key1 when size is 1, should return value1."
                }
            ]
        },
        {
            "group": "remove non-existing key when size is 2",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistingKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test removing a non-existing key when size is 2, should return null."
                }
            ]
        },
        {
            "group": "remove non-existing key when size is 3",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistingKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test removing a non-existing key when size is 3, should return null."
                }
            ]
        }
    ]
}