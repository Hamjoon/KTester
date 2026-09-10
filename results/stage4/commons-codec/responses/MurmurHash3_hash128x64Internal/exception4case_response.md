{
    "output": [
        {
            "group": "hash128x64Internal - Invalid Length Cases",
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
                            "value": -1
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test case with a negative length to ensure the method handles invalid input correctly."
                },
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
                            "value": 20
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Test case with a length greater than the available data size to check for out-of-bounds access."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Invalid Offset Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8]
                        },
                        {
                            "parameter": "offset",
                            "value": 10
                        },
                        {
                            "parameter": "length",
                            "value": 5
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Test case with an offset greater than the data length to check for out-of-bounds access."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8]
                        },
                        {
                            "parameter": "offset",
                            "value": 8
                        },
                        {
                            "parameter": "length",
                            "value": 1
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Test case with an offset that points to the end of the data array to ensure it handles out-of-bounds correctly."
                }
            ]
        }
    ]
}