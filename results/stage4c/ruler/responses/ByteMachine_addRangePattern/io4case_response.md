{
    "group": "addRangePattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 1],
                        "top": [0, 0, 1],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top are the same, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 1],
                        "top": [0, 0, 1],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top are the same, and the range is open."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 5],
                        "top": [0, 0, 9],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom is less than top with no suffix of 9's or 0's, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 5],
                        "top": [0, 0, 9],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom is less than top with no suffix of 9's or 0's, and the range is open."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 0],
                        "top": [0, 1, 0],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom has a suffix of 0's, and the range is open at the bottom."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 0],
                        "top": [0, 1, 0],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom has a suffix of 0's, and the range is open at both ends."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [1, 0, 9],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top share a prefix but differ in the last digit, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [1, 0, 9],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top share a prefix but differ in the last digit, and the range is open."
        }
    ]
}