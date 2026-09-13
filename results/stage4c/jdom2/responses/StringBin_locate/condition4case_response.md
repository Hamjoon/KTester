{
    "group": "locate method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'test'"
                },
                {
                    "parameter": "value",
                    "value": "test"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "def", "ghi", "test", "xyz"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": 3,
            "description": "Test case where the value 'test' exists in the bucket at index 3 with the same hash."
        },
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'notfound'"
                },
                {
                    "parameter": "value",
                    "value": "notfound"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "def", "ghi", "test", "xyz"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": -6,
            "description": "Test case where the value 'notfound' does not exist in the bucket and has a unique hash."
        },
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'abc'"
                },
                {
                    "parameter": "value",
                    "value": "abc"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "abc", "abc", "def", "ghi"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": 0,
            "description": "Test case where the value 'abc' appears multiple times and is found at the start of the bucket."
        },
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'ghi'"
                },
                {
                    "parameter": "value",
                    "value": "ghi"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "def", "ghi", "ghi", "ghi"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": 2,
            "description": "Test case where the value 'ghi' appears multiple times and is found in the middle of the bucket."
        },
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'xyz'"
                },
                {
                    "parameter": "value",
                    "value": "xyz"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "def", "ghi", "test", "xyz"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": 4,
            "description": "Test case where the value 'xyz' is found at the end of the bucket."
        },
        {
            "input": [
                {
                    "parameter": "hash",
                    "value": "hashCode of 'middle'"
                },
                {
                    "parameter": "value",
                    "value": "middle"
                },
                {
                    "parameter": "bucket",
                    "value": ["abc", "def", "ghi", "test", "xyz"]
                },
                {
                    "parameter": "length",
                    "value": 5
                }
            ],
            "expected": -4,
            "description": "Test case where the value 'middle' does not exist and should be placed in the middle of the bucket."
        }
    ]
}