{
    "testCases": [
        {
            "group": "addRangePattern - Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 3],
                                "top": [1, 2, 5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "nextNameState is not null",
                    "description": "Test case where the range is valid and both bottom and top are closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 3],
                                "top": [1, 3, 0],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "nextNameState is not null",
                    "description": "Test case where the top is open, allowing for matches below the top value."
                }
            ]
        },
        {
            "group": "addRangePattern - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 3],
                                "top": [1, 2, 3],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "nextNameState is not null and no matches are added",
                    "description": "Test case where the bottom and top are equal and closed, leading to no valid matches."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 3],
                                "top": [1, 2, 4],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "nextNameState is not null and includes matches for values greater than bottom",
                    "description": "Test case where the bottom is open and the top is closed, allowing for matches greater than the bottom value."
                }
            ]
        },
        {
            "group": "addRangePattern - Special Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0, 0],
                                "top": [0, 0, 0],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "nextNameState is not null and no matches are added",
                    "description": "Test case where both bottom and top are zero and open, leading to no valid matches."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [9, 9, 9],
                                "top": [1, 0, 0],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "nextNameState is not null and includes matches for values below top",
                    "description": "Test case where the bottom is at maximum and the top is lower, leading to valid matches."
                }
            ]
        }
    ]
}