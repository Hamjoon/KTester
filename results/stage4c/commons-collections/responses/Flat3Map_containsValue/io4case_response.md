{
    "group": "Flat3Map containsValue(Object) method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "false",
            "description": "Test when value is null and none of value1, value2, or value3 are null with size 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "false",
            "description": "Test when value is null and none of value1 or value2 are null with size 2."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "false",
            "description": "Test when value is null and value1 is not null with size 1."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "valueNotInMap"
                }
            ],
            "expected": "false",
            "description": "Test when value does not exist in the map with size 3 and delegateMap is null."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "valueNotInMap"
                }
            ],
            "expected": "false",
            "description": "Test when value does not exist in the map with size 2 and delegateMap is null."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "valueNotInMap"
                }
            ],
            "expected": "false",
            "description": "Test when value does not exist in the map with size 1 and delegateMap is null."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "value1"
                }
            ],
            "expected": "true",
            "description": "Test when value matches value1 with size 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "value2"
                }
            ],
            "expected": "true",
            "description": "Test when value matches value2 with size 3."
        }
    ]
}