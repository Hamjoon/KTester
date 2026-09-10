{
    "group": "analyzeMultiText",
    "cases": [
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance with null Content"
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
            "expected": "NullPointerException is thrown",
            "description": "Test case where the Content at the specified offset is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
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
            "expected": "IndexOutOfBoundsException is thrown",
            "description": "Test case where the offset is negative, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 100
                }
            ],
            "expected": "IndexOutOfBoundsException is thrown",
            "description": "Test case where the length exceeds the available contents, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance with empty Content list"
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
            "expected": "IndexOutOfBoundsException is thrown",
            "description": "Test case where there are no contents in MultiText, expecting an IndexOutOfBoundsException when accessing content."
        }
    ]
}