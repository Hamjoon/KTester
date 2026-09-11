{
    "test cases": [
        {
            "group": "deleteRangePattern",
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
                    "expected": null,
                    "description": "Test case where the range to be deleted is a single value with open on both ends. It should not delete the value."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [5],
                                "top": [5],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted is a single value with an open top. It should not delete the value."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [2],
                                "top": [8],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted exists with an open top. It should delete the range from the state but not the top value."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [5],
                                "top": [15],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted exists with an open bottom. It should delete the range from the state but not the bottom value."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [20],
                                "top": [25],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted exists and is not open on the bottom but open on the top. It should delete the bottom value but not the top."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [10],
                                "top": [10],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted is a single value and both ends are closed. It should delete the single value from the state."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [10],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the range to be deleted exists with an open bottom and closed top. It should delete the range from the state but not the top value."
                }
            ]
        }
    ]
}