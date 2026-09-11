{
    "group": "isMatch method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": null
                },
                {
                    "parameter": "path",
                    "value": ""
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when pattern is null and path is an empty string. The method should throw a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": ""
                },
                {
                    "parameter": "path",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when pattern is an empty string and path is null. The method should throw a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": null
                },
                {
                    "parameter": "path",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when both pattern and path are null. The method should throw a NullPointerException."
        }
    ]
}