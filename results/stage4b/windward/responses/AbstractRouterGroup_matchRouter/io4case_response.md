{"group": "matchRouter", "cases": [
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/"
            },
            {
                "parameter": "method",
                "value": "GET"
            }
        ],
        "expected": "FunctionMetaInfo instance",
        "description": "Test when the relativePath ends with a slash but is not equal to a single slash, should return the FunctionMetaInfo instance after removing the trailing slash."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/{id}"
            },
            {
                "parameter": "method",
                "value": "PUT"
            }
        ],
        "expected": null,
        "description": "Test when the relativePath matches a router with a path variable but the method is not available, should return null."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/1"
            },
            {
                "parameter": "method",
                "value": "POST"
            }
        ],
        "expected": null,
        "description": "Test when the relativePath matches a regex pattern but the method is not available, should return null."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/{id}/details"
            },
            {
                "parameter": "method",
                "value": "GET"
            }
        ],
        "expected": "FunctionMetaInfo instance",
        "description": "Test when the relativePath matches a router with additional path segments and a path variable, should return the corresponding FunctionMetaInfo."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/{id}/details"
            },
            {
                "parameter": "method",
                "value": "DELETE"
            }
        ],
        "expected": null,
        "description": "Test when the relativePath matches a router with additional path segments but the method is not available, should return null."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/1/details"
            },
            {
                "parameter": "method",
                "value": "GET"
            }
        ],
        "expected": "FunctionMetaInfo instance with path variables populated",
        "description": "Test when the relativePath matches a regex pattern and populates path variables with additional segments, should return the FunctionMetaInfo with populated path variables."
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
        "description": "Test when the relativePath is just the groupPath with a trailing slash, should return null."
    },
    {
        "input": [
            {
                "parameter": "relativePath",
                "value": "/groupPath/test/{id}/extra/{extraId}"
            },
            {
                "parameter": "method",
                "value": "GET"
            }
        ],
        "expected": "FunctionMetaInfo instance",
        "description": "Test when the relativePath matches a router with multiple path variables, should return the corresponding FunctionMetaInfo."
    }
]}