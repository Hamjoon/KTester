{
    "group": "deleteRangePattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0],
                        "top": [0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "No deletion, as range does not exist",
            "description": "Test case where the range to be deleted does not exist in the ByteMachine."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 3],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of specific range pattern",
            "description": "Test case where the range exists and is exactly matched by bottom and top values."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1, 2, 3],
                        "top": [1, 2, 4],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "Deletion of range patterns between 1,2,3 and 1,2,4",
            "description": "Test case where the range exists and includes multiple byte values."
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
                        "top": [1, 2, 3],
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
        }
    ]
}