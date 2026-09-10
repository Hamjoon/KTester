{
    "testCases": [
        {
            "group": "addRangePattern - Normal Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0, 2, 9],
                                "top": [0, 0, 5, 0],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Valid NameState object",
                    "description": "Test with a normal range where bottom is less than top, and both ends are closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 0],
                                "top": [0, 1, 5],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Valid NameState object",
                    "description": "Test with a range where the bottom is open and the top is closed."
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
                                "bottom": [0, 0, 9],
                                "top": [0, 1, 0],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "Valid NameState object with no transitions for last digit",
                    "description": "Test with a range where the bottom is less than the top but the last digit of bottom is 9 and top is open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0, 0],
                                "top": [0, 0, 0],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Valid NameState object with no transitions",
                    "description": "Test with a range where both bottom and top are equal and closed."
                }
            ]
        },
        {
            "group": "addRangePattern - Invalid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 2, 0],
                                "top": [0, 1, 5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with an invalid range where bottom is greater than top."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 5],
                                "top": [0, 1, 5],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with a range where both bottom and top are equal and both are open."
                }
            ]
        }
    ]
}