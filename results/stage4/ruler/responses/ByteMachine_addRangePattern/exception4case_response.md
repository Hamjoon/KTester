{
    "output": [
        {
            "group": "addRangePattern - Exception Scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 0, 2, 9],
                                "top": [0, 0, 2, 9],
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with a range where both bottom and top are equal and closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 2, 5],
                                "top": [0, 2, 5],
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with an invalid range where bottom is equal to top but bottom is open and top is closed."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 0],
                                "top": [0, 1, 0],
                                "openBottom": true,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with a range where both bottom and top are equal and both are open."
                },
                {
                    "input": [
                        {
                            "parameter": "range",
                            "value": {
                                "bottom": [0, 1, 9],
                                "top": [0, 2, 0],
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": "Exception or error indicating invalid range",
                    "description": "Test with an invalid range where bottom is less than top but the last digit of bottom is 9 and top is open."
                }
            ]
        }
    ]
}