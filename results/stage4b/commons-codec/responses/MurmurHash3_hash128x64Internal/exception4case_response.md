{
    "group": "hash128x64Internal",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }"
                },
                {
                    "parameter": "offset",
                    "value": "-1"
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with a negative offset to verify that it throws an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }"
                },
                {
                    "parameter": "offset",
                    "value": "9"
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with an offset that exceeds the length of the array to verify that it throws an ArrayIndexOutOfBoundsException."
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
                    "value": "9"
                },
                {
                    "parameter": "seed",
                    "value": "MurmurHash3.DEFAULT_SEED"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with a length that exceeds the size of the data array to ensure it throws an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[] { 1, 2, 3, 4 }"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "5"
                },
                {
                    "parameter": "seed",
                    "value": "MurmurHash3.DEFAULT_SEED"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with a length that exceeds the data length to verify that it throws an ArrayIndexOutOfBoundsException."
        }
    ]
}