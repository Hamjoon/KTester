{
    "group": "isMatch method test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": ""
                },
                {
                    "parameter": "path",
                    "value": ""
                }
            ],
            "expected": true,
            "description": "Both pattern and path are empty strings, should return true as they match."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": ""
                },
                {
                    "parameter": "path",
                    "value": "somePath"
                }
            ],
            "expected": false,
            "description": "Pattern is empty and path is not, should return false as they do not match."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "/"
                },
                {
                    "parameter": "path",
                    "value": ""
                }
            ],
            "expected": true,
            "description": "Pattern starts with pathSeparator and path is empty, should return true if matchStart is true."
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
            "expected": false,
            "description": "Pattern is '/*' and path is empty, should return false if matchStart is false."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "*"
                },
                {
                    "parameter": "path",
                    "value": "somePath"
                }
            ],
            "expected": true,
            "description": "Pattern is '*' and path is 'somePath', should return true as '*' matches any sequence of characters."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "*some"
                },
                {
                    "parameter": "path",
                    "value": "anysome"
                }
            ],
            "expected": true,
            "description": "Pattern is '*some' and path is 'anysome', should return true as '*' can match 'any'."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some*"
                },
                {
                    "parameter": "path",
                    "value": "somePath"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some*' and path is 'somePath', should return true as '*' can match 'Path'."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "so?e"
                },
                {
                    "parameter": "path",
                    "value": "some"
                }
            ],
            "expected": true,
            "description": "Pattern is 'so?e' and path is 'some', should return true as '?' matches any single character."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "so?e"
                },
                {
                    "parameter": "path",
                    "value": "sore"
                }
            ],
            "expected": true,
            "description": "Pattern is 'so?e' and path is 'sore', should return true as '?' matches 'r'."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "so?e"
                },
                {
                    "parameter": "path",
                    "value": "sooe"
                }
            ],
            "expected": true,
            "description": "Pattern is 'so?e' and path is 'sooe', should return true as '?' matches 'o'."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "so?e"
                },
                {
                    "parameter": "path",
                    "value": "soe"
                }
            ],
            "expected": false,
            "description": "Pattern is 'so?e' and path is 'soe', should return false as '?' cannot match zero characters."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "**"
                },
                {
                    "parameter": "path",
                    "value": "anyPath"
                }
            ],
            "expected": true,
            "description": "Pattern is '**' and path is 'anyPath', should return true as '**' matches any sequence of characters."
        }
    ]
}