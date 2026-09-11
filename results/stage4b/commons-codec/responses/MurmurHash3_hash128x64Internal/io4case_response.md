{
    "testCases": [
        {
            "group": "hash128x64Internal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "17"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with 17 bytes to verify handling of input that exceeds one complete block and checks tail processing."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "14"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with 14 bytes to ensure proper handling of the case statement for tail processing."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "13"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with 13 bytes to verify the correct behavior of the method with input that requires tail processing."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "16"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with a complete block of 16 bytes consisting of zeros to verify hash output consistency with zero input."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "7"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with 7 bytes to ensure proper handling of input that is less than one block."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "1"
                        },
                        {
                            "parameter": "length",
                            "value": "9"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with offset of 1 and length of 9 bytes to verify correct processing of data starting from a non-zero offset."
                }
            ]
        }
    ]
}