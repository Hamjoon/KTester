{
    "group": "deleteRangePattern Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [127],
                        "top": [128],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": null,
            "description": "Test deleting a range pattern where the bottom is a valid byte but the top exceeds the byte range."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [255],
                        "top": [255],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": null,
            "description": "Test deleting a range pattern where both bottom and top are the maximum byte value (255)."
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
            "expected": null,
            "description": "Test deleting a range pattern with empty bottom and top arrays."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1],
                        "top": [1],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": null,
            "description": "Test deleting a range pattern where both bottom and top are equal and both are open."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [2],
                        "top": [2],
                        "openBottom": false,
                        "openTop": true
                    }
                }
            ],
            "expected": null,
            "description": "Test deleting a range pattern where the bottom is equal to the top but the top is open."
        }
    ]
}