{
    "group": "findRangePattern Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [],
                        "top": [0, 1, 2],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where bottom is empty and top is not, expecting null due to mismatch in length."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where top is empty and bottom is not, expecting null due to mismatch in length."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 2],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where bottom and top are equal but both open, expecting null as no valid range is defined."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2, 4],
                        "top": [0, 1, 2],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where bottom has more bytes than top, expecting null due to mismatch in length."
        }
    ]
}