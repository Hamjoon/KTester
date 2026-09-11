{
    "testCases": [
        {
            "group": "containsValue with delegateMap",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "someValue"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue when delegateMap is not null and contains the value."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "nonExistentValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue when delegateMap is not null and does not contain the value."
                }
            ]
        },
        {
            "group": "containsValue with null value",
            "cases": [
                {
                    "input": [],
                    "expected": true,
                    "description": "Test containsValue with null value and size equals 3, where value3 is null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test containsValue with null value and size equals 2, where value2 is null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test containsValue with null value and size equals 1, where value1 is null."
                },
                {
                    "input": [],
                    "expected": false,
                    "description": "Test containsValue with null value and size equals 3, where value3 is not null."
                },
                {
                    "input": [],
                    "expected": false,
                    "description": "Test containsValue with null value and size equals 2, where value2 is not null."
                },
                {
                    "input": [],
                    "expected": false,
                    "description": "Test containsValue with null value and size equals 1, where value1 is not null."
                }
            ]
        },
        {
            "group": "containsValue with non-null value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value3"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value3 when size is 3."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value2"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value2 when size is 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "value1"
                        }
                    ],
                    "expected": true,
                    "description": "Test containsValue with value equal to value1 when size is 1."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "nonExistentValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue with a value that does not match any of the values when size is 3."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "nonExistentValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue with a value that does not match any of the values when size is 2."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "nonExistentValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test containsValue with a value that does not match any of the values when size is 1."
                }
            ]
        }
    ]
}