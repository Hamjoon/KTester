{
    "group": "addRangePattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 1],
                        "top": [0, 0, 5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top have a common prefix, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 1],
                        "top": [0, 0, 5],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom and top have a common prefix, and the range is open."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 9],
                        "top": [0, 1, 0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom has a suffix of 9's, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 9],
                        "top": [0, 1, 0],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when bottom has a suffix of 9's, and the range is open at the bottom."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 0],
                        "top": [0, 0, 5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when top has a suffix of 0's, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 0],
                        "top": [0, 0, 5],
                        "openBottom": false,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when top has a suffix of 0's, and the range is open at the top."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [2, 0, 0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when there is no common prefix between bottom and top, and the range is closed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 0, 0],
                        "top": [2, 0, 0],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "nextNameState",
            "description": "Test when there is no common prefix between bottom and top, and the range is open."
        }
    ]
}