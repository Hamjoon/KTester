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
                                "bottom": [1, 2, 3],
                                "top": [1, 2, 4],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a basic range where the bottom and top patterns are within a valid range."
                },
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
                    "expected": "valid NameState object",
                    "description": "Tests a range where the bottom and top patterns are equal."
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
                                "top": [1, 2, 2],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a range where the top is strictly less than the bottom."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0, 0],
                                "top": [0, 0, 0],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests a range where all values are the same but the top is open."
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
                            "value": null
                        }
                    ],
                    "expected": "null",
                    "description": "Tests the behavior when the input range is null."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1, 2],
                                "top": [1],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "null",
                    "description": "Tests a range where the bottom is longer than the top, which is invalid."
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
                                "bottom": [0],
                                "top": [0],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests the boundary condition where both bottom and top are at the minimum possible values."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [127],
                                "top": [127],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "valid NameState object",
                    "description": "Tests the boundary condition where both bottom and top are at the maximum byte value."
                }
            ]
        }
    ]
}