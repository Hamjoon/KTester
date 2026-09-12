{
    "group": "Additional Flat3Map put(K key, V value) Method Test Cases",
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
            "description": "Update an existing entry with a non-null key to have a null value when the map has one entry."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Add a new entry with both a null key and a null value when the map is empty."
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
            "description": "Update an existing entry with a non-null key to have a null value when the map has two entries."
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
            "description": "Update an existing entry with a non-null key to have a null value when the map has three entries."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "key4"
                },
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Add a new entry with a non-null key and a null value causing the map to convert to a delegate map."
        }
    ]
}