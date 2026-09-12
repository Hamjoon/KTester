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
            "expected": "No deletion due to empty bottom and top values",
            "description": "Test case with both bottom and top values empty, ensuring that no deletion occurs."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1],
                        "top": [],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "No deletion due to empty top value",
            "description": "Test case with an empty top value, ensuring that no deletion occurs."
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
            "expected": "No deletion due to top value being shorter than bottom value",
            "description": "Test case where the top value is shorter than the bottom value, ensuring no deletion occurs."
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
            "expected": "No deletion as range is open and does not match any exact pattern",
            "description": "Test case with an open range that does not exactly match any pattern."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 4],
                        "openBottom": false,
                        "openTop": true
                    }
                }
            ],
            "expected": "Partial deletion due to open top",
            "description": "Test case with a partially open range, testing behavior when only one side is open."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 5],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of patterns from 1,2,4 to 1,2,5",
            "description": "Test case with open bottom and closed top, checking deletion behavior at boundary conditions."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0],
                        "top": [255],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of all patterns within the full byte range",
            "description": "Test case covering the full byte range from 0 to 255 to ensure all patterns are deleted."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [127],
                        "top": [128],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of patterns with byte value 128",
            "description": "Test case with an open bottom and closed top, ensuring only patterns with the byte value 128 are deleted."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 3, 4],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of range patterns between 1,2,3 and 1,2,3,4",
            "description": "Test case where the top value is longer than the bottom value, ensuring correct deletion of range patterns."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 3],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "No deletion as open bottom does not match any exact pattern",
            "description": "Test case with an open bottom and closed top, ensuring no deletion occurs when the bottom is open."
        }
    ]
}