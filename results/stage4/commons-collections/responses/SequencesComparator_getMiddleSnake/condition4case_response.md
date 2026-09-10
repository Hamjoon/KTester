{
    "testCases": [
        {
            "group": "getMiddleSnake",
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
                    "description": "Test case where both sequences are empty. The method should return null."
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
                    "description": "Test case where the first sequence has elements and the second sequence is empty. The method should return null."
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
                    "description": "Test case where the first sequence is empty and the second sequence has elements. The method should return null."
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
                    "description": "Test case where both sequences have the same number of elements. The method should return a Snake object representing the middle snake."
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
                            "value": 6
                        }
                    ],
                    "expected": "Snake object",
                    "description": "Test case where the first sequence has 5 elements and the second sequence has 6 elements. The method should return a Snake object."
                },
                {
                    "input": [
                        {
                            "parameter": "start1",
                            "value": 0
                        },
                        {
                            "parameter": "end1",
                            "value": 6
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
                    "description": "Test case where the first sequence has 6 elements and the second sequence has 5 elements. The method should return a Snake object."
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
                    "description": "Test case where both sequences have identical elements. The method should return a Snake object."
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
                    "description": "Test case where both sequences have completely different elements. The method should return a Snake object."
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
                    "description": "Test case where both sequences have elements and the method should return a Snake object based on the comparison."
                }
            ]
        }
    ]
}