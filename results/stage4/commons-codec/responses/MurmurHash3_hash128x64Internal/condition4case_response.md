{
    "testCases": [
        {
            "group": "hash128x64Internal - Full Length Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 16
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with full length of 16 bytes to check hash output."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 32
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with length greater than 16 bytes to verify handling of multiple blocks."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Tail Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "length",
                            "value": 15
                        },
                        {
                            "parameter": "seed",
                            "value": 104729
                        }
                    ],
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with length of 15 bytes to validate tail processing."
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
                    "description": "Test case with length of 9 bytes to check tail processing with less than 10 bytes."
                }
            ]
        },
        {
            "group": "hash128x64Internal - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": []
                        },
                        {
                            "parameter": "offset",
                            "value": 0
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
                    "description": "Test case with empty data to ensure method handles zero-length input correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": [1]
                        },
                        {
                            "parameter": "offset",
                            "value": 0
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
                    "expected": [1234567890123456789, 9876543210987654321],
                    "description": "Test case with a single byte to test minimal input processing."
                }
            ]
        }
    ]
}