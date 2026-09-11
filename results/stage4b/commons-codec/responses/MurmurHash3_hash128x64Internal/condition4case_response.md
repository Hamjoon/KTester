{
    "testCases": [
        {
            "group": "hash128x64Internal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 }"
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
                    "description": "Test with a complete block of 16 bytes, verifying the hash output."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 1, 2, 3 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "3"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with less than one block (3 bytes), checking tail processing."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] { 0 }"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "1"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with a single byte input to verify handling of minimal input."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[] {}"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "0"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with an empty byte array to verify behavior with no input data."
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
                            "value": "15"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with 15 bytes to ensure proper handling of the case statement for tail."
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
                            "value": "8"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "long[] { expectedH1, expectedH2 }",
                    "description": "Test with exactly one block (8 bytes), verifying the correct hash output."
                }
            ]
        }
    ]
}