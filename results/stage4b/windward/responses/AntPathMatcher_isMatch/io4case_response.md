{
    "test cases": [
        {
            "group": "isMatch method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/*/*"
                        },
                        {
                            "parameter": "path",
                            "value": "/path/to/resource"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '/*/*' and path is '/path/to/resource'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "/*/*"
                        },
                        {
                            "parameter": "path",
                            "value": "/path"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is '/*/*' and path is '/path'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*/b"
                        },
                        {
                            "parameter": "path",
                            "value": "abc/b"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is 'a*/b' and path is 'abc/b'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "a*/b"
                        },
                        {
                            "parameter": "path",
                            "value": "ab/b"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is 'a*/b' and path is 'ab/b'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/"
                        },
                        {
                            "parameter": "path",
                            "value": "/"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '*/' and path is '/'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/"
                        },
                        {
                            "parameter": "path",
                            "value": "path/"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '*/' and path is 'path/'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "??/path"
                        },
                        {
                            "parameter": "path",
                            "value": "ab/path"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '??/path' and path is 'ab/path'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "??/path"
                        },
                        {
                            "parameter": "path",
                            "value": "a/path"
                        }
                    ],
                    "expected": false,
                    "description": "Test when pattern is '??/path' and path is 'a/path'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "*/**"
                        },
                        {
                            "parameter": "path",
                            "value": "any/path/here"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '*/**' and path is 'any/path/here'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "pattern",
                            "value": "**/path"
                        },
                        {
                            "parameter": "path",
                            "value": "any/path"
                        }
                    ],
                    "expected": true,
                    "description": "Test when pattern is '**/path' and path is 'any/path'. The method should return true."
                }
            ]
        }
    ]
}