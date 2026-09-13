{
    "group": "analyzeMultiText Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "null"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null MultiText instance, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MockMultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": -1
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test with negative offset, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MockMultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": -1
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test with negative length, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MockMultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 10
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test with offset beyond content list size, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "MultiText",
                    "value": "MockMultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 10
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test with length exceeding content list size, expecting an IndexOutOfBoundsException."
        }
    ]
}