{
    "group": "matchRouter Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/nonExistentPath"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when the relative path does not start with groupPath and is not found in routers."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when relativePath ends with a slash and matches groupPath, but is not found in routers."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object",
            "description": "Test when relativePath matches a router key without path variables, returning the associated FunctionMetaInfo."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object with path variables",
            "description": "Test when relativePath matches a router key with path variables, returning FunctionMetaInfo with populated path variables."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "POST"
                }
            ],
            "expected": null,
            "description": "Test when relativePath matches a router key but the HTTP method does not match, returning null."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object",
            "description": "Test when relativePath matches a router key and the method is GET, returning the associated FunctionMetaInfo."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object with path variables",
            "description": "Test when relativePath matches a router key with path variables and the method is GET, returning FunctionMetaInfo with populated path variables."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "PUT"
                }
            ],
            "expected": null,
            "description": "Test when relativePath matches a router key but the HTTP method is not supported, returning null."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object with path variables",
            "description": "Test when relativePath matches a router key with path variables and the method is GET, returning FunctionMetaInfo with populated path variables."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object",
            "description": "Test when relativePath matches a router key and the method is GET, returning the associated FunctionMetaInfo."
        }
    ]
}