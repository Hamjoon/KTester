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
                                "bottom": [2],
                                "top": [4],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern that exists and has matches where bottom is greater than 1."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [5],
                                "top": [5],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern where both bottom and top are equal and open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [10],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern with both open bottom and top where matches exist."
                },
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
                    "description": "Test deleting a range pattern where both bottom and top are 0 and both are open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [1],
                                "top": [255],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test deleting a range pattern with a large range from 1 to 255 where top is open."
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
                    "expected": null,
                    "description": "Test deleting a range pattern where both bottom and top are 127 (middle of byte range)."
                }
            ]
        }
    ]
}