{
    "testCases": [
        {
            "group": "Locate Method Test Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 5
                        },
                        {
                            "parameter": "value",
                            "value": "apple"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "cherry", "date", "fig", "grape"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": -1,
                    "description": "Test case where the hash does not match any element in the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 5
                        },
                        {
                            "parameter": "value",
                            "value": "grape"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "cherry", "date", "fig", "grape"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": 4,
                    "description": "Test case where the value 'grape' is found at the end of the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 3
                        },
                        {
                            "parameter": "value",
                            "value": "banana"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "banana", "banana", "cherry", "date"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": 0,
                    "description": "Test case where the value 'banana' is found at the start of the bucket with the same hash."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 3
                        },
                        {
                            "parameter": "value",
                            "value": "cherry"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "banana", "banana", "cherry", "date"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": 3,
                    "description": "Test case where the value 'cherry' is found in the middle of the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 3
                        },
                        {
                            "parameter": "value",
                            "value": "date"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "banana", "banana", "cherry", "date"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": 4,
                    "description": "Test case where the value 'date' is found at the end of the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 3
                        },
                        {
                            "parameter": "value",
                            "value": "fig"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["banana", "banana", "banana", "cherry", "date"]
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        }
                    ],
                    "expected": -5,
                    "description": "Test case where the value 'fig' is not found, and the insertion point is returned."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 0
                        },
                        {
                            "parameter": "value",
                            "value": "apple"
                        },
                        {
                            "parameter": "bucket",
                            "value": []
                        },
                        {
                            "parameter": "length",
                            "value": 0
                        }
                    ],
                    "expected": -1,
                    "description": "Test case where the bucket is empty, should return -1 as no values are present."
                }
            ]
        }
    ]
}