{
    "group": "getMiddleSnake Method Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 1
                },
                {
                    "parameter": "end1",
                    "value": 2
                },
                {
                    "parameter": "start2",
                    "value": 1
                },
                {
                    "parameter": "end2",
                    "value": 2
                }
            ],
            "expected": "Snake object",
            "description": "Test case with minimal non-zero ranges for both sequences. The method should return a Snake object if there is a matching segment in the middle."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 1
                },
                {
                    "parameter": "start2",
                    "value": 0
                },
                {
                    "parameter": "end2",
                    "value": 1
                }
            ],
            "expected": "Snake object",
            "description": "Test case with a single element in both sequences. The method should return a Snake object if the single elements match."
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
                    "value": 10
                }
            ],
            "expected": "Snake object",
            "description": "Test case with larger equal-sized sequences. The method should return a Snake object indicating the middle snake found by the algorithm."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 7
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
            "expected": "Snake object",
            "description": "Test case where the first sequence is larger than the second by a margin but not double. The method should return a Snake object indicating the middle snake found by the algorithm."
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
                    "value": 7
                }
            ],
            "expected": "Snake object",
            "description": "Test case where the second sequence is larger than the first by a margin but not double. The method should return a Snake object indicating the middle snake found by the algorithm."
        }
    ]
}