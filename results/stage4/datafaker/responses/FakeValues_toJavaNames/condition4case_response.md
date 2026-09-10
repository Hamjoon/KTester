{
    "testCases": [
        {
            "group": "toJavaNames Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": null
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test with null string input, should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": ""
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "",
                    "description": "Test with empty string input, should return empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "helloWorld"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "HelloWorld",
                    "description": "Test with a standard camel case string, should return string with first character capitalized."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "hello_world"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "HelloWorld",
                    "description": "Test with snake case string, should convert to camel case with first character capitalized."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "hello_world"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "helloWorld",
                    "description": "Test with snake case string and isMethod true, should convert to camel case with first character in lower case."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "HelloWorld"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "HelloWorld",
                    "description": "Test with already formatted camel case string, should return the same string."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "hello_world_test"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "HelloWorldTest",
                    "description": "Test with multiple underscores in snake case, should convert to camel case with first character capitalized."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "hello_world_test"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "helloWorldTest",
                    "description": "Test with multiple underscores in snake case and isMethod true, should convert to camel case with first character in lower case."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "_leadingUnderscore"
                        },
                        {
                            "parameter": "isMethod",
                            "value": false
                        }
                    ],
                    "expected": "LeadingUnderscore",
                    "description": "Test with leading underscore, should capitalize the first letter after the underscore."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "_leadingUnderscore"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "leadingUnderscore",
                    "description": "Test with leading underscore and isMethod true, should keep the first letter in lower case."
                },
                {
                    "input": [
                        {
                            "parameter": "string",
                            "value": "alreadyCamelCase"
                        },
                        {
                            "parameter": "isMethod",
                            "value": true
                        }
                    ],
                    "expected": "alreadyCamelCase",
                    "description": "Test with already formatted camel case string and isMethod true, should return the same string."
                }
            ]
        }
    ]
}