{
    "testCases": [
        {
            "group": "matchRouter - Path does not start with groupPath",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/some/other/path"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath does not start with groupPath; should return null."
                }
            ]
        },
        {
            "group": "matchRouter - Path ends with slash",
            "cases": [
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
                    "expected": "Expected functionMetaInfo or null depending on router mapping",
                    "description": "Test when the relativePath ends with a slash; should remove the trailing slash and proceed."
                }
            ]
        },
        {
            "group": "matchRouter - Path contains curly braces",
            "cases": [
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
                    "expected": "Expected functionMetaInfo with path variables",
                    "description": "Test when the relativePath matches a router pattern with curly braces; should return functionMetaInfo with path variables populated."
                }
            ]
        },
        {
            "group": "matchRouter - No matching router found",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/nonexistent"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when no matching router is found; should return null."
                }
            ]
        },
        {
            "group": "matchRouter - GET request with resourceRouter enabled",
            "cases": [
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
                    "expected": "Expected functionMetaInfo based on matchedRouter",
                    "description": "Test when the method is GET and resourceRouter is enabled; should return functionMetaInfo if the path matches."
                }
            ]
        },
        {
            "group": "matchRouter - GET request with resourceRouter disabled",
            "cases": [
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
                    "expected": null,
                    "description": "Test when the method is GET and resourceRouter is disabled; should not return any functionMetaInfo."
                }
            ]
        },
        {
            "group": "matchRouter - Non-GET request",
            "cases": [
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
                    "expected": "Expected functionMetaInfo or null depending on router mapping",
                    "description": "Test when the method is not GET; should check for exact match in routers."
                }
            ]
        }
    ]
}