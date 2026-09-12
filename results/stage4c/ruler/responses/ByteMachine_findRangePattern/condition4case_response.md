{
    "group": "findRangePattern Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 2],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test when range bottom and top are identical, expecting a valid NameState."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 3],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with a range where top is greater than bottom by one, expecting a valid NameState."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 1],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with a range where top is less than bottom, expecting null as they don't overlap."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 3],
                        "openBottom": true,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with open bottom range, expecting a valid NameState as bottom is inclusive."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0, 1, 2],
                        "top": [0, 1, 3],
                        "openBottom": false,
                        "openTop": true
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with open top range, expecting a valid NameState as top is inclusive."
        },
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
            "expected": "NameState instance",
            "description": "Test with single byte range where bottom and top are equal, expecting a valid NameState."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [0],
                        "top": [1],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "NameState instance",
            "description": "Test with single byte range where top is greater than bottom, expecting a valid NameState."
        },
        {
            "input": [
                {
                    "parameter": "range",
                    "value": {
                        "bottom": [1],
                        "top": [0],
                        "openBottom": false,
                        "openTop": false
                    }
                }
            ],
            "expected": "null",
            "description": "Test with single byte range where top is less than bottom, expecting null."
        }
    ]
}