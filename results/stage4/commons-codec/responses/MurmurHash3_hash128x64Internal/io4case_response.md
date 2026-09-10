{
    "test cases": [
        {
            "group": "hash128x64Internal - Partial Length Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 8
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with length of 8 bytes to verify hash output for a single block."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8, 9]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 9
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with length of 9 bytes to check processing of less than a full block."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Negative Offset Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8]
                        },
                        {
                            "parameter": "offset",
                            "value": -1
                        },
                        {
                            "parameter": "length",
                            "value": 8
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test case with a negative offset to ensure the method handles invalid input correctly."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Large Seed Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 10
                        },
                        {
                            "parameter": "seed",
                            "value": 999999999999999999
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with a very large seed value to check hash output consistency."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Zero Length with Non-zero Offset",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3]
                        },
                        {
                            "parameter": "offset",
                            "value": 3
                        },
                        {
                            "parameter": "length",
                            "value": 0
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [0, 0],
                    "description": "Test case with non-zero offset and zero length to ensure method handles this scenario correctly."
                }
            ]
        }
    ]
}