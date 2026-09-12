{
    "group": "hash128x64Internal",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8}"
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with data array smaller than one block but with length parameter indicating a full block, expecting an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}"
                },
                {
                    "parameter": "offset",
                    "value": "5"
                },
                {
                    "parameter": "length",
                    "value": "10"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with offset and length that exceed the data array size, expecting an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}"
                },
                {
                    "parameter": "offset",
                    "value": "-1"
                },
                {
                    "parameter": "length",
                    "value": "5"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with negative offset, expecting an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "-5"
                },
                {
                    "parameter": "seed",
                    "value": "104729"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test with negative length, expecting an IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test with null data array, expecting a NullPointerException."
        }
    ]
}