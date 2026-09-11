{
    "group": "doubleMetaphone",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": " "
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with a string containing only whitespace, expect the method to return an empty string."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "  "
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "",
            "description": "Test with a string containing multiple whitespace characters, expect the alternate encoding to also return an empty string."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "12345"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with a string containing only numeric characters, expect the method to return an empty string."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "12345"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "",
            "description": "Test with a string containing only numeric characters, expect the alternate encoding to also return an empty string."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "!!@#$%"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with a string containing special characters, expect the method to return an empty string."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "!!@#$%"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "",
            "description": "Test with a string containing special characters, expect the alternate encoding to also return an empty string."
        }
    ]
}