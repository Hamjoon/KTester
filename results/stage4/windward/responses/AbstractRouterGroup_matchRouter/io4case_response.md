{
    "testCases": [
        {
            "group": "matchRouter - Path starts with groupPath but has no matching router",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/anotherPath"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath starts with groupPath but does not match any routers; should return null."
                }
            ]
        },
        {
            "group": "matchRouter - Path matches a router pattern without curly braces",
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
                    "expected": "Expected functionMetaInfo for /groupPath/resource",
                    "description": "Test when the relativePath matches a router pattern without curly braces; should return corresponding functionMetaInfo."
                }
            ]
        },
        {
            "group": "matchRouter - Path with multiple segments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/resource/123/details"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": "Expected functionMetaInfo with path variables for resource and details",
                    "description": "Test when the relativePath has multiple segments matching a router pattern; should return functionMetaInfo with populated path variables."
                }
            ]
        },
        {
            "group": "matchRouter - Path with unsupported HTTP method",
            "cases": [
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
                    "description": "Test when the method is not supported for the given relativePath; should return null."
                }
            ]
        },
        {
            "group": "matchRouter - Path with special characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/resource/!@#"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath contains special characters; should return null if no matching router exists."
                }
            ]
        },
        {
            "group": "matchRouter - Path with empty segments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath//resource"
                        },
                        {
                            "parameter": "method",
                            "value": "GET"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath has empty segments due to consecutive slashes; should return null."
                }
            ]
        },
        {
            "group": "matchRouter - Non-existing resource with POST method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "relativePath",
                            "value": "/groupPath/resource/999"
                        },
                        {
                            "parameter": "method",
                            "value": "POST"
                        }
                    ],
                    "expected": null,
                    "description": "Test when the relativePath points to a non-existing resource with POST method; should return null."
                }
            ]
        }
    ]
}