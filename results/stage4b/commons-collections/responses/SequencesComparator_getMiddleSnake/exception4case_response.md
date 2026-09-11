{
    "group": "getMiddleSnake - Internal Error",
    "cases": [
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
                    "value": 3
                },
                {
                    "parameter": "end2",
                    "value": 4
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test with sequences that lead to an internal error due to misalignment; should throw IllegalStateException."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 0
                },
                {
                    "parameter": "end1",
                    "value": 4
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
            "expected": "IllegalStateException",
            "description": "Test with sequences that lead to an internal error due to insufficient overlap; should throw IllegalStateException."
        },
        {
            "input": [
                {
                    "parameter": "start1",
                    "value": 1
                },
                {
                    "parameter": "end1",
                    "value": 3
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
            "expected": "IllegalStateException",
            "description": "Test with sequences that have a significant length difference, leading to an internal error; should throw IllegalStateException."
        }
    ]
}