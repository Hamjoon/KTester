{
    "test cases": [
        {
            "group": "Locate Method Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 123456
                        },
                        {
                            "parameter": "value",
                            "value": "test"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["test", "example", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 0,
                    "description": "Test case where the input value matches the first element in the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 654321
                        },
                        {
                            "parameter": "value",
                            "value": "example"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["test", "example", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 1,
                    "description": "Test case where the input value matches the second element in the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 111111
                        },
                        {
                            "parameter": "value",
                            "value": "sample"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["test", "example", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 2,
                    "description": "Test case where the input value matches the last element in the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 222222
                        },
                        {
                            "parameter": "value",
                            "value": "notfound"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["test", "example", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": -4,
                    "description": "Test case where the input value does not match any element, should return negative insertion point."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 123456
                        },
                        {
                            "parameter": "value",
                            "value": "sample"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["sample", "sample", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 0,
                    "description": "Test case where the input value matches an element with the same hash but different string value."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 123456
                        },
                        {
                            "parameter": "value",
                            "value": "test"
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
                    "description": "Test case where the bucket is empty, should return -1 as no elements are present."
                }
            ]
        }
    ]
}