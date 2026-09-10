{
    "test cases": [
        {
            "group": "isMatch method basic functionality",
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
                    "description": "Test case where both pattern and path are empty strings. Expected to return true."
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
                    "description": "Test case where pattern has a leading wildcard and path is empty. Expected to return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*"
                        },
                        {
                            "parameter": "path",
                            "value": "anyPath"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a single wildcard and path is a non-empty string. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "test"
                        },
                        {
                            "parameter": "path",
                            "value": "test"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern matches the path exactly. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "test"
                        },
                        {
                            "parameter": "path",
                            "value": "testing"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern does not match the path. Expected to return false."
                }
            ]
        },
        {
            "group": "isMatch method wildcard functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/"
                        },
                        {
                            "parameter": "path",
                            "value": "folder/"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern has a wildcard followed by a separator and path ends with a separator. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/file.txt"
                        },
                        {
                            "parameter": "path",
                            "value": "folder/file.txt"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern matches a path with a wildcard followed by a specific file name. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "file.txt"
                        },
                        {
                            "parameter": "path",
                            "value": "folder/file.txt"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern does not include any wildcards and path includes a folder. Expected to return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "**"
                        },
                        {
                            "parameter": "path",
                            "value": "any/path/to/somewhere"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a double wildcard. Expected to return true for any path."
                }
            ]
        },
        {
            "group": "isMatch method question mark functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "te?t"
                        },
                        {
                            "parameter": "path",
                            "value": "test"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern includes a question mark that matches any single character. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "te?t"
                        },
                        {
                            "parameter": "path",
                            "value": "teat"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern includes a question mark and path matches. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "te?t"
                        },
                        {
                            "parameter": "path",
                            "value": "te"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern requires a character to match but path is too short. Expected to return false."
                }
            ]
        },
        {
            "group": "isMatch method edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/*"
                        },
                        {
                            "parameter": "path",
                            "value": "/"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a wildcard with a leading separator and path is a single separator. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/**"
                        },
                        {
                            "parameter": "path",
                            "value": "/some/path"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a double wildcard with a leading separator and path has multiple segments. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/*"
                        },
                        {
                            "parameter": "path",
                            "value": "folder/test"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern is a wildcard with a leading separator but path has additional segments. Expected to return false."
                }
            ]
        }
    ]
}