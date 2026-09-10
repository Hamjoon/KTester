{
    "test cases": [
        {
            "group": "isMatch method basic functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "test/*"
                        },
                        {
                            "parameter": "path",
                            "value": "test/folder"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern matches a path with a wildcard at the end. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "test/*"
                        },
                        {
                            "parameter": "path",
                            "value": "test/"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern matches a path with a wildcard at the end and path ends with a separator. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "test/*"
                        },
                        {
                            "parameter": "path",
                            "value": "testing/folder"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern does not match the path due to differing prefixes. Expected to return false."
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
                            "value": "*/**/file.txt"
                        },
                        {
                            "parameter": "path",
                            "value": "folder/subfolder/file.txt"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern includes a wildcard followed by a double wildcard and matches the path. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "**/file.txt"
                        },
                        {
                            "parameter": "path",
                            "value": "file.txt"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a double wildcard followed by a specific file name and the path is the file itself. Expected to return true."
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
                            "value": "te??t"
                        },
                        {
                            "parameter": "path",
                            "value": "text"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern includes two question marks that match any two characters. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "te??t"
                        },
                        {
                            "parameter": "path",
                            "value": "teat"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern includes two question marks and path matches. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "te??t"
                        },
                        {
                            "parameter": "path",
                            "value": "testt"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern requires two characters to match but path has extra characters. Expected to return false."
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
                            "value": "/folder/*"
                        },
                        {
                            "parameter": "path",
                            "value": "/folder/test"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern matches a path with a leading separator and a wildcard at the end. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/**/test"
                        },
                        {
                            "parameter": "path",
                            "value": "/some/folder/test"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where pattern is a double wildcard with a leading separator and path has multiple segments leading to the test. Expected to return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/*/test"
                        },
                        {
                            "parameter": "path",
                            "value": "/folder/test/extra"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where pattern matches a single segment but path has extra segments. Expected to return false."
                }
            ]
        }
    ]
}