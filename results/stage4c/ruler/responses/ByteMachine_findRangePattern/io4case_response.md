{
    "group": "findRangePattern Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 9],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with a range where the top is significantly greater than the bottom, expecting a valid NameState."
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
                        "bottom": [0, 1, 2],
                        "top": [0, 2, 0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with a range where there's a difference in the second byte, expecting a valid NameState."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 2, 5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where the top has more bytes than the bottom, expecting null due to mismatch in length."
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
            "expected": "NameState instance",
            "description": "Test with an empty range, expecting a valid NameState as both bottom and top are empty and equal."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 2],
                        "openBottom": false,
                        "openTop": true
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with a range where bottom and top are equal and top is open, expecting a valid NameState as the range is inclusive."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [1, 0, 1],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with a range where the bottom is open and top is open, expecting a valid NameState as the open range is valid."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [1, 0, 0],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where bottom and top are equal but bottom is open, expecting null as the range is invalid."
        }
    ]
}