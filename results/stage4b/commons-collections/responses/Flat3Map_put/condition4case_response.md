{
    "testCases": [
        {
            "group": "Test put method with delegateMap",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        },
                        {
                            "parameter": "value",
                            "value": "someValue"
                        }
                    ],
                    "expected": "someValue",
                    "description": "Test putting a key-value pair when delegateMap is not null. It should return the value associated with the key if it exists, otherwise null."
                }
            ]
        },
        {
            "group": "Test put method with null key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNullKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a value with a null key when size is 0. It should store the value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNullKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a value with a null key when size is 1 and key1 is null. It should store the value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNullKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a value with a null key when size is 2 and key2 is null. It should store the value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNullKey"
                        }
                    ],
                    "expected": "valueForNullKey",
                    "description": "Test putting a value with a null key when size is 3 and key3 is null. It should store the value and return the previous value."
                }
            ]
        },
        {
            "group": "Test put method with non-null key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        },
                        {
                            "parameter": "value",
                            "value": "value1"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a key-value pair when size is 0. It should store the key and value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        },
                        {
                            "parameter": "value",
                            "value": "value2"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a key-value pair when size is 1 and key1 is not equal to key2. It should store the new key and value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        },
                        {
                            "parameter": "value",
                            "value": "value3"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a key-value pair when size is 2 and neither key1 nor key2 match key3. It should store the new key and value and return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test putting a value for an existing key (key1) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test putting a value for an existing key (key2) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue3"
                        }
                    ],
                    "expected": "value3",
                    "description": "Test putting a value for an existing key (key3) when size is 3. It should update the value and return the old value."
                }
            ]
        },
        {
            "group": "Test put method with size exceeding limit",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key4"
                        },
                        {
                            "parameter": "value",
                            "value": "value4"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a value when size exceeds 3. It should call convertToMap and store the value in delegateMap, returning null."
                }
            ]
        }
    ]
}