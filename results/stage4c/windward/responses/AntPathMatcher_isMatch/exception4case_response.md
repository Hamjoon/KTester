{
    "group": "isMatch method exception test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": null
                },
                {
                    "parameter": "path",
                    "value": "somePath"
                }
            ],
            "expected": "NullPointerException",
            "description": "Pattern is null, should throw NullPointerException as the pattern cannot be null."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "somePattern"
                },
                {
                    "parameter": "path",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Path is null, should throw NullPointerException as the path cannot be null."
        }
    ]
}