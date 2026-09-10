{
    "testCases": [
        {
            "group": "deleteRangePattern Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [1],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern where the range does not exist."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern that exists and has matches to be deleted."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [5],
                                "top": [10],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting an open bottom range pattern where the bottom is not included."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0],
                                "top": [10],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern with an open top where the top is not included."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [10],
                                "top": [5],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern with invalid bottom and top where bottom exceeds top."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [3],
                                "top": [3],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern where the bottom and top are equal and exist."
                }
            ]
        }
    ]
}