{
    "test cases": [
        {
            "group": "remove with delegateMap not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": "value associated with 'someKey' in delegateMap",
                    "description": "Test removal when delegateMap is not null; should return value associated with the key."
                }
            ]
        },
        {
            "group": "remove when size is 0",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "anyKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test removal when size is 0; should return null."
                }
            ]
        },
        {
            "group": "remove with key null and size 3",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value3",
                    "description": "Test removal with key null and size 3; should return value3 and reduce size to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removal with key null and size 2; should return value2 and reduce size to 1."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removal with key null and size 1; should return value1 and reduce size to 0."
                }
            ]
        },
        {
            "group": "remove with key null and size 1",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test removal with key null and size 1 when key1 is not null; should return null."
                }
            ]
        },
        {
            "group": "remove with key not null and size 3",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": "value3",
                    "description": "Test removal with a valid key matching key3; should return value3 and reduce size to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removal with a valid key matching key2; should return value2 and reduce size to 2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removal with a valid key matching key1; should return value1 and reduce size to 2."
                }
            ]
        },
        {
            "group": "remove with key not null and size 2",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removal with a valid key matching key2; should return value2 and reduce size to 1."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removal with a valid key matching key1; should return value1 and reduce size to 1."
                }
            ]
        },
        {
            "group": "remove with key not null and size 1",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removal with a valid key matching key1; should return value1 and reduce size to 0."
                }
            ]
        },
        {
            "group": "remove with non-existent key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistentKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test removal with a key that does not exist; should return null."
                }
            ]
        }
    ]
}