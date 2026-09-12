{
    "group": "hash128x64Internal",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{}"
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
                    "value": "104729"
                }
            ],
            "expected": "[0, 0]",
            "description": "Test with empty data array to ensure method handles zero-length input gracefully."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}"
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
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array exactly one block long to verify single block processing."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}"
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
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array slightly longer than one block to verify tail processing."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}"
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
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array slightly less than one block to verify tail processing."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "32"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array exactly two blocks long to verify multiple block processing."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "33"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array slightly longer than two blocks to verify tail processing after multiple blocks."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "64"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array exactly four blocks long to verify multiple block processing."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "65"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with data array slightly longer than four blocks to verify tail processing after multiple blocks."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "100"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "Non-zero hash result",
            "description": "Test with a large data array to verify handling of multiple blocks and tail processing."
        }
    ]
}