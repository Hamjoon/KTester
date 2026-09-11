{
    "testCases": [
        {
            "group": "bothify with only letterify set to true and upper case",
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
                    "expected": "String with an uppercase letter replacing '?'",
                    "description": "Test case to verify that bothify replaces '?' with an uppercase letter when isUpper is true and letterify is also true."
                }
            ]
        },
        {
            "group": "bothify with only numerify set to true and upper case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Code #?"
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
                            "value": false
                        }
                    ],
                    "expected": "String with an uppercase letter and a digit replacing '#'",
                    "description": "Test case to verify that bothify replaces '#' with a digit and '?' with an uppercase letter when both numerify and isUpper are true."
                }
            ]
        },
        {
            "group": "bothify with only numerify set to true and letterify set to true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "#?"
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
                            "value": true
                        }
                    ],
                    "expected": "String with a digit replacing '#' and a lowercase letter replacing '?'",
                    "description": "Test case to check that bothify replaces '#' with a digit and '?' with a lowercase letter when both numerify and letterify are true."
                }
            ]
        },
        {
            "group": "bothify with boundary input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": ""
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
                    "expected": "",
                    "description": "Test case to ensure that bothify returns an empty string when the input is empty."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "###"
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
                    "expected": "String with three digits replacing '###'",
                    "description": "Test case to verify that bothify replaces all '#' with digits when numerify is true."
                }
            ]
        }
    ]
}