{
    "group": "deleteRangePattern",
    "cases": [
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
            "expected": "IllegalArgumentException",
            "description": "Test case where the range to be deleted is empty. It should throw an IllegalArgumentException due to invalid range."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [5],
                        "top": [],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the range to be deleted has an empty top value. It should throw an IllegalArgumentException due to invalid range."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [],
                        "top": [5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the range to be deleted has an empty bottom value. It should throw an IllegalArgumentException due to invalid range."
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
            "expected": "IllegalArgumentException",
            "description": "Test case where the bottom value is greater than the top value. It should throw an IllegalArgumentException due to invalid range."
        }
    ]
}