{
    "testCases": [
        {
            "group": "isMatch method tests",
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
                    "description": "Test when both pattern and path are empty strings. The method should return true."
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
                    "description": "Test when pattern is '/*' and path is empty. The method should return false."
                },
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
                    "description": "Test when pattern is '/*' and path is '/'. The method should return true."
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
                    "description": "Test when pattern is '*' and path is 'anyPath'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/"
                        },
                        {
                            "parameter": "path",
                            "value": "anyPath/"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '*/' and path is 'anyPath/'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "??"
                        },
                        {
                            "parameter": "path",
                            "value": "ab"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '??' and path is 'ab'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "??"
                        },
                        {
                            "parameter": "path",
                            "value": "a"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is '??' and path is 'a'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*"
                        },
                        {
                            "parameter": "path",
                            "value": "abc"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a*' and path is 'abc'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*b"
                        },
                        {
                            "parameter": "path",
                            "value": "acb"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a*b' and path is 'acb'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*b"
                        },
                        {
                            "parameter": "path",
                            "value": "a"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is 'a*b' and path is 'a'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a?b"
                        },
                        {
                            "parameter": "path",
                            "value": "acb"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a?b' and path is 'acb'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a?b"
                        },
                        {
                            "parameter": "path",
                            "value": "ab"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is 'a?b' and path is 'ab'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*b*c"
                        },
                        {
                            "parameter": "path",
                            "value": "abc"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a*b*c' and path is 'abc'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*b*c"
                        },
                        {
                            "parameter": "path",
                            "value": "aXbYc"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a*b*c' and path is 'aXbYc'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*b*c"
                        },
                        {
                            "parameter": "path",
                            "value": "ab"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is 'a*b*c' and path is 'ab'. The method should return false."
                }
            ]
        }
    ]
}