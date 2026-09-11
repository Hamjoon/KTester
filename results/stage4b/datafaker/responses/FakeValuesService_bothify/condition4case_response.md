{
    "testCases": [
        {
            "group": "bothify with all flags set to true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello #?"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": true
                        },
                        {
                            "parameter": "numerify",
                            "value": true
                        },
                        {
                            "parameter": "letterify",
                            "value": true
                        }
                    ],
                    "expected": "String with uppercase letters and digits",
                    "description": "Test case to verify that bothify replaces '#' with a digit and '?' with an uppercase letter."
                }
            ]
        },
        {
            "group": "bothify with only numerify set to true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello #?"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": false
                        },
                        {
                            "parameter": "numerify",
                            "value": true
                        },
                        {
                            "parameter": "letterify",
                            "value": false
                        }
                    ],
                    "expected": "String with digits replacing '#' and '?' remains unchanged",
                    "description": "Test case to check that bothify replaces '#' with a digit and leaves '?' unchanged when only numerify is true."
                }
            ]
        },
        {
            "group": "bothify with only letterify set to true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello #?"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": false
                        },
                        {
                            "parameter": "numerify",
                            "value": false
                        },
                        {
                            "parameter": "letterify",
                            "value": true
                        }
                    ],
                    "expected": "String with lowercase letters replacing '?' and '#' remains unchanged",
                    "description": "Test case to verify that bothify replaces '?' with a lowercase letter and leaves '#' unchanged when only letterify is true."
                }
            ]
        },
        {
            "group": "bothify with no replacements",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello World"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": false
                        },
                        {
                            "parameter": "numerify",
                            "value": false
                        },
                        {
                            "parameter": "letterify",
                            "value": false
                        }
                    ],
                    "expected": "Hello World",
                    "description": "Test case to ensure that bothify returns the input unchanged when all flags are false."
                }
            ]
        },
        {
            "group": "bothify with upper case setting",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello ?"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": true
                        },
                        {
                            "parameter": "numerify",
                            "value": false
                        },
                        {
                            "parameter": "letterify",
                            "value": true
                        }
                    ],
                    "expected": "String with uppercase letter replacing '?'",
                    "description": "Test case to verify that bothify replaces '?' with an uppercase letter when isUpper is true."
                }
            ]
        },
        {
            "group": "bothify with numerify on '?'",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Your code is ?"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance with random service"
                        },
                        {
                            "parameter": "isUpper",
                            "value": false
                        },
                        {
                            "parameter": "numerify",
                            "value": true
                        },
                        {
                            "parameter": "letterify",
                            "value": false
                        }
                    ],
                    "expected": "String with a digit replacing '?'",
                    "description": "Test case to ensure that bothify replaces '?' with a digit when numerify is true."
                }
            ]
        }
    ]
}