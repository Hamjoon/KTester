{
    "group": "isMatch method exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": null
                },
                {
                    "parameter": "path",
                    "value": "validPath"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where pattern is null. Expected to throw NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                },
                {
                    "parameter": "path",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where path is null. Expected to throw NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "invalid*pattern"
                },
                {
                    "parameter": "path",
                    "value": ""
                }
            ],
            "expected": "false",
            "description": "Test case where pattern is invalid but path is empty. Expected to return false."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "/*"
                },
                {
                    "parameter": "path",
                    "value": "nonEmptyPath"
                }
            ],
            "expected": "true",
            "description": "Test case where pattern is a wildcard with a leading separator and path is non-empty. Expected to return true."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "/*"
                },
                {
                    "parameter": "path",
                    "value": ""
                }
            ],
            "expected": "false",
            "description": "Test case where pattern is a wildcard with a leading separator and path is empty. Expected to return false."
        }
    ]
}