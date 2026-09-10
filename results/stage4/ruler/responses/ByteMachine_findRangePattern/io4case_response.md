{
    "testCases": [
        {
            "group": "findRangePattern - Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 4],
                                "top": [1, 2, 5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a range where the bottom is less than the top by one unit in the last element."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1],
                                "top": [0, 2],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a range where the bottom is less than the top with an open range at the top."
                }
            ]
        },
        {
            "group": "findRangePattern - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2, 3],
                                "top": [1, 3, 2],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "null",
                    "description": "Tests a range where the top pattern is not in a valid order compared to the bottom."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [2],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a range where the bottom is open and the top is closed."
                }
            ]
        },
        {
            "group": "findRangePattern - Null and Invalid Inputs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {"bottom": [], "top": [], "openBottom": false, "openTop": false}
                        }
                    ],
                    "expected": "null",
                    "description": "Tests a range where both bottom and top are empty arrays."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [5],
                                "top": [3],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "null",
                    "description": "Tests a range where the bottom is greater than the top."
                }
            ]
        },
        {
            "group": "findRangePattern - Boundary Conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0],
                                "top": [0, 1],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests the boundary condition where the bottom is at the minimum possible value and the top is open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [126],
                                "top": [127],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests the boundary condition where the bottom is just below the maximum byte value and the top is open."
                }
            ]
        }
    ]
}