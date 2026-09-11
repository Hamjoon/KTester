{
    "group": "addRangePattern - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 2],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the bottom is greater than the top, which should throw an exception."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test case where the bottom and top arrays have different lengths, which should throw an exception."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [],
                        "top": [1, 2, 3],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the bottom array is empty, which should throw an exception."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the top array is empty, which should throw an exception."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 3],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where both bottom and top are equal and open, which should throw an exception."
        }
    ]
}