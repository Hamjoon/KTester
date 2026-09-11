{
    "test cases": [
        {
            "group": "Test put method with null key and existing values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        },
                        {
                            "parameter": "value",
                            "value": "newValueForNullKey"
                        }
                    ],
                    "expected": "valueForNullKey",
                    "description": "Test putting a value with a null key when size is 3 and key3 is not null. It should update the value and return the previous value."
                }
            ]
        },
        {
            "group": "Test put method with existing key-value pairs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValue1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test putting a new value for an existing key (key1) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValue2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test putting a new value for an existing key (key2) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValue3"
                        }
                    ],
                    "expected": "value3",
                    "description": "Test putting a new value for an existing key (key3) when size is 3. It should update the value and return the old value."
                }
            ]
        },
        {
            "group": "Test put method with rehashing",
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
                    "description": "Test putting a new key-value pair when the map is full (size is 3). It should call convertToMap and store the value in delegateMap, returning null."
                }
            ]
        },
        {
            "group": "Test put method with null value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        },
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": "value1",
                    "description": "Test putting a null value for an existing key (key1) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        },
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": "value2",
                    "description": "Test putting a null value for an existing key (key2) when size is 3. It should update the value and return the old value."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        },
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": "value3",
                    "description": "Test putting a null value for an existing key (key3) when size is 3. It should update the value and return the old value."
                }
            ]
        },
        {
            "group": "Test put method with empty map",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "newKey"
                        },
                        {
                            "parameter": "value",
                            "value": "newValue"
                        }
                    ],
                    "expected": null,
                    "description": "Test putting a key-value pair when the map is empty (size is 0). It should store the key and value and return null."
                }
            ]
        }
    ]
}