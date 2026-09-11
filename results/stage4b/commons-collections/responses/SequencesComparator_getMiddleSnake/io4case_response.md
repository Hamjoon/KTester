{
    "testCases": [
        {
            "group": "getMiddleSnake - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "start1",
                            "value": 1
                        },
                        {
                            "parameter": "end1",
                            "value": 1
                        },
                        {
                            "parameter": "start2",
                            "value": 1
                        },
                        {
                            "parameter": "end2",
                            "value": 1
                        }
                    ],
                    "expected": null,
                    "description": "Test with both sequences having a single element that is not equal; should return null."
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
                    "expected": null,
                    "description": "Test with both sequences having a single element that is equal; should return a valid Snake object."
                }
            ]
        },
        {
            "group": "getMiddleSnake - Normal Operation",
            "cases": [
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
                    "expected": "valid Snake object",
                    "description": "Test with two equal non-empty sequences of length 10; should return a valid Snake object."
                },
                {
                    "input": [
                        {
                            "parameter": "start1",
                            "value": 2
                        },
                        {
                            "parameter": "end1",
                            "value": 5
                        },
                        {
                            "parameter": "start2",
                            "value": 1
                        },
                        {
                            "parameter": "end2",
                            "value": 6
                        }
                    ],
                    "expected": "valid Snake object",
                    "description": "Test with partial overlap between sequences; should return a valid Snake object."
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
                            "value": 1
                        },
                        {
                            "parameter": "end2",
                            "value": 5
                        }
                    ],
                    "expected": "valid Snake object",
                    "description": "Test with sequences that have a one-element overlap; should return a valid Snake object."
                }
            ]
        },
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
                            "value": 1
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
                            "value": 7
                        },
                        {
                            "parameter": "start2",
                            "value": 2
                        },
                        {
                            "parameter": "end2",
                            "value": 3
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "Test with sequences that have a significant length difference, leading to an internal error; should throw IllegalStateException."
                }
            ]
        }
    ]
}