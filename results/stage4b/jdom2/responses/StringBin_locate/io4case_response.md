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
                            "value": ["test", "test", "test"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 0,
                    "description": "Test case where the input value matches multiple identical elements in the bucket."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": "sample"
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
                    "description": "Test case where the input hash is the string itself, testing how the hash code is handled with the same value."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 999999
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
                    "description": "Test case where the input value does not match any element in the bucket and has a unique hash."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 0
                        },
                        {
                            "parameter": "value",
                            "value": "zero"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["one", "two", "three"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": -1,
                    "description": "Test case where the input hash is zero and does not match any elements in the bucket."
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
                            "value": ["example", "sample"]
                        },
                        {
                            "parameter": "length",
                            "value": 2
                        }
                    ],
                    "expected": -1,
                    "description": "Test case where the input value matches no elements and is greater than the largest element in a smaller bucket."
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
                            "value": ["test", "test", "test"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": 0,
                    "description": "Test case where the input value matches the first element in a bucket of identical elements."
                },
                {
                    "input": [
                        {
                            "parameter": "hash",
                            "value": 123456
                        },
                        {
                            "parameter": "value",
                            "value": "example"
                        },
                        {
                            "parameter": "bucket",
                            "value": ["test", "test", "test"]
                        },
                        {
                            "parameter": "length",
                            "value": 3
                        }
                    ],
                    "expected": -1,
                    "description": "Test case where the input value does not match any element in a bucket of identical elements."
                }
            ]
        }
    ]
}