{
    "testCases": [
        {
            "group": "matchRouter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/test/path"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath does not start with groupPath, should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/test"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath ends with a slash and is not equal to a single slash, should return the modified path without trailing slash."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/test"
                        },
                        {
                            "parameter": "method",
                            "value": "POST"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath is present in routers but the method is not available, should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/test/{id}"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": "FunctionMetaInfo instance",
                    "description": "Test when the relativePath matches a router with a path variable, should return the corresponding FunctionMetaInfo."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/test/1"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": "FunctionMetaInfo instance with path variables populated",
                    "description": "Test when the relativePath matches a regex pattern and populates path variables, should return the FunctionMetaInfo with populated path variables."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/test"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": "FunctionMetaInfo instance",
                    "description": "Test when the relativePath matches a direct router key with the correct method, should return the corresponding FunctionMetaInfo."
                },
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/unknown"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath does not match any router keys, should return null."
                }
            ]
        }
    ]
}