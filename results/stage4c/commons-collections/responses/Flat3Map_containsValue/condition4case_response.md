{
    "group": "Flat3Map containsValue(Object) method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "someValue"
                }
            ],
            "expected": "true",
            "description": "Test when delegateMap is not null and contains the value."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "true",
            "description": "Test when value is null and value3 is null with size 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "true",
            "description": "Test when value is null and value2 is null with size 2."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                }
            ],
            "expected": "true",
            "description": "Test when value is null and value1 is null with size 1."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "value3"
                }
            ],
            "expected": "true",
            "description": "Test when value matches value3 with size 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "value2"
                }
            ],
            "expected": "true",
            "description": "Test when value matches value2 with size 2."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "value1"
                }
            ],
            "expected": "true",
            "description": "Test when value matches value1 with size 1."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "nonExistentValue"
                }
            ],
            "expected": "false",
            "description": "Test when value does not exist in the map and delegateMap is null."
        }
    ]
}