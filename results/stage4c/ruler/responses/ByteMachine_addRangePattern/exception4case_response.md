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
                        "openTop": true
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when bottom and top are the same, and the range is open at the top."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 1],
                        "top": [0, 0, 1],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when bottom and top are the same, and the range is open at the bottom."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 5],
                        "top": [0, 0, 5],
                        "openBottom": true,
                        "openTop": true
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when bottom and top are the same, and the range is open at both ends."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 9],
                        "top": [0, 0, 5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when bottom is greater than top, which should not be allowed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0],
                        "top": [0, 0, 5],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when bottom and top have different lengths, which should not be allowed."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 0, 5],
                        "top": [0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "java.lang.IllegalArgumentException",
            "description": "Test when top is shorter than bottom, which should not be allowed."
        }
    ]
}