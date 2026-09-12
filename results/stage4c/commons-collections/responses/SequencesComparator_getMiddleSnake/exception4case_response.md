{
    "group": "getMiddleSnake Method Exception Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": -1
                },
                {
                    "parameter": "end1",
                    "value": 5
                },
                {
                    "parameter": "start2",
                    "value": 0
                },
                {
                    "parameter": "end2",
                    "value": 5
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test case where start1 is negative, which should trigger an IndexOutOfBoundsException when accessing sequence1."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 5
                },
                {
                    "parameter": "start2",
                    "value": -1
                },
                {
                    "parameter": "end2",
                    "value": 5
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test case where start2 is negative, which should trigger an IndexOutOfBoundsException when accessing sequence2."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 10
                },
                {
                    "parameter": "start2",
                    "value": 0
                },
                {
                    "parameter": "end2",
                    "value": 5
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test case where end1 is beyond the length of sequence1, which should trigger an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 5
                },
                {
                    "parameter": "start2",
                    "value": 0
                },
                {
                    "parameter": "end2",
                    "value": 10
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test case where end2 is beyond the length of sequence2, which should trigger an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 5
                },
                {
                    "parameter": "end1",
                    "value": 0
                },
                {
                    "parameter": "start2",
                    "value": 0
                },
                {
                    "parameter": "end2",
                    "value": 5
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where start1 is greater than end1, which should trigger an IllegalArgumentException due to invalid range."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 5
                },
                {
                    "parameter": "start2",
                    "value": 5
                },
                {
                    "parameter": "end2",
                    "value": 0
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where start2 is greater than end2, which should trigger an IllegalArgumentException due to invalid range."
        }
    ]
}