{
    "testCases": [
        {
            "group": "findRangePattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 2],
                                "top": [3, 4, 5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where the range has a valid bottom and top pattern without open boundaries."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 2],
                                "top": [3, 4, 5],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where the bottom range is open and the top range is closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 2],
                                "top": [3, 4, 5],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where the bottom range is closed and the top range is open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 2],
                                "top": [3, 4, 5],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where both bottom and top ranges are open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0],
                                "top": [0],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where the bottom and top range are the same single value and both are closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0],
                                "top": [1],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "NameState object",
                    "description": "Test case where the bottom range is less than the top range, both are closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [0],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the bottom range is greater than the top range, expecting null return."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [],
                                "top": [],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the bottom and top ranges are empty, expecting null return."
                }
            ]
        }
    ]
}