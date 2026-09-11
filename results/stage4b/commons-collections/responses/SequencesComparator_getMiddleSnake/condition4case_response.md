{
    "testCases": [
        {
            "group": "getMiddleSnake - Edge Cases",
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
                    "description": "Test with both sequences empty; should return null."
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
                    "description": "Test with the first sequence empty and the second sequence non-empty; should return null."
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
                    "description": "Test with the second sequence empty and the first sequence non-empty; should return null."
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
                    "expected": "valid Snake object",
                    "description": "Test with two equal non-empty sequences; should return a valid Snake object."
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
                    "expected": "valid Snake object",
                    "description": "Test with a shorter first sequence and a longer second sequence; should return a valid Snake object."
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
                            "value": 2
                        },
                        {
                            "parameter": "end2",
                            "value": 7
                        }
                    ],
                    "expected": "valid Snake object",
                    "description": "Test with overlapping sequences; should return a valid Snake object."
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
                            "value": 0
                        },
                        {
                            "parameter": "end2",
                            "value": 6
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "Test with sequences that lead to an internal error; should throw IllegalStateException."
                }
            ]
        }
    ]
}