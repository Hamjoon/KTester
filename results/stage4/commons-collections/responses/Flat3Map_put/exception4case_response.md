{
    "group": "Flat3Map put method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key1"
                },
                {
                    "parameter": "value",
                    "value": "Value1"
                }
            ],
            "expected": null,
            "description": "Test putting a new key-value pair when the map is empty. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key1"
                },
                {
                    "parameter": "value",
                    "value": "New Value1"
                }
            ],
            "expected": "Value1",
            "description": "Test updating an existing key with a new value when the map has one key-value pair. It should return the old value."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key2"
                },
                {
                    "parameter": "value",
                    "value": "Value2"
                }
            ],
            "expected": null,
            "description": "Test putting a new key-value pair when the map has one existing key. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key3"
                },
                {
                    "parameter": "value",
                    "value": "Value3"
                }
            ],
            "expected": null,
            "description": "Test putting a new key-value pair when the map has two existing key-value pairs. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key1"
                },
                {
                    "parameter": "value",
                    "value": "Another New Value1"
                }
            ],
            "expected": "Value1",
            "description": "Test updating an existing key with a new value when the map has three key-value pairs. It should return the old value."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key4"
                },
                {
                    "parameter": "value",
                    "value": "Value4"
                }
            ],
            "expected": null,
            "description": "Test putting a new key-value pair when the map is full (3 entries). It should convert to a delegate map and store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key5"
                },
                {
                    "parameter": "value",
                    "value": "Value5"
                }
            ],
            "expected": null,
            "description": "Test putting a new key-value pair when the map is full (3 entries) and should convert to a delegate map. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "Value for null key 4"
                }
            ],
            "expected": null,
            "description": "Test putting a value with a null key when the map is full (3 entries) and should replace the existing null key value."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key6"
                },
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Test putting a new key with a null value when the map is empty. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key7"
                },
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Test putting a new key with a null value when the map has one key-value pair. It should store the new mapping."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "Key8"
                },
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Test putting a new key with a null value when the map has two existing key-value pairs. It should store the new mapping."
        }
    ]
}