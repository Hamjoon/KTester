{
    "group": "getMiddleSnake Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
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
                    "value": 0
                }
            ],
            "expected": null,
            "description": "Test case where both sequences are empty. The method should return null since there are no elements to compare."
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
                    "value": 0
                }
            ],
            "expected": null,
            "description": "Test case where the second sequence is empty. The method should return null since there are no elements in the second sequence to compare."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
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
            "expected": null,
            "description": "Test case where the first sequence is empty. The method should return null since there are no elements in the first sequence to compare."
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
                    "value": 5
                }
            ],
            "expected": "Snake object",
            "description": "Test case where both sequences have elements. The method should return a Snake object indicating the middle snake found by the algorithm."
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
                    "value": 3
                }
            ],
            "expected": "Snake object",
            "description": "Test case where the first sequence is longer than the second. The method should return a Snake object indicating the middle snake found by the algorithm."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 3
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
            "description": "Test case where the second sequence is longer than the first. The method should return a Snake object indicating the middle snake found by the algorithm."
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
                    "value": 5
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test case to simulate a condition that should never happen, forcing the method to throw an IllegalStateException. This might require specific setup of the sequences and equator to trigger this condition."
        }
    ]
}