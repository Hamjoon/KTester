{
    "test cases": [
        {
            "group": "containsValue with empty delegateMap",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "someValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue when delegateMap is null and size is 0."
                }
            ]
        },
        {
            "group": "containsValue with values present",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value1"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value1 when size is 1 and value1 is set."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value2"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value2 when size is 2 and value2 is set."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value3"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value3 when size is 3 and value3 is set."
                }
            ]
        },
        {
            "group": "containsValue with boundary conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue with null value when size is 0."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with null value when size is 3 and value3 is null."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with null value when size is 2 and value2 is null."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with null value when size is 1 and value1 is null."
                }
            ]
        }
    ]
}