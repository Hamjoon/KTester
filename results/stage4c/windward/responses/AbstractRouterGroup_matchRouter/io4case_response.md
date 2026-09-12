{
    "group": "matchRouter Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when the relativePath matches groupPath exactly without trailing slash, but is not found in routers."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123/"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "FunctionMetaInfo object with path variables",
            "description": "Test when relativePath ends with a slash and matches a router key with path variables, returning FunctionMetaInfo with populated path variables."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/{id}"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when relativePath contains path variables syntax but does not match any router key, returning null."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123"
                },
                {
                    "parameter": "method",
                    "value": "DELETE"
                }
            ],
            "expected": null,
            "description": "Test when relativePath matches a router key but the HTTP method is DELETE and not supported, returning null."
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
            "description": "Test when relativePath matches a router key with path variables and the method is GET, returning FunctionMetaInfo with populated path variables. (Ensures consistency with existing tests)"
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/abc"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when relativePath contains non-numeric path variable and does not match numeric path variable pattern in router key, returning null."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/123/extra"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when relativePath has more segments than the router key pattern, returning null."
        }
    ]
}