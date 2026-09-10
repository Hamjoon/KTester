{
    "test cases": [
        {
            "group": "bothify method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Hello ??"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                    "expected": "Expected output with uppercase letters replacing both '?'",
                    "description": "Test bothify with input containing two '?' with isUpper set to true and letterify set to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Good #??"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                    "expected": "Expected output with lowercase letters and digits replacing '#' and letters replacing '?'",
                    "description": "Test bothify with input containing '#' and two '?' with isUpper set to false, numerify and letterify set to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "###"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                    "expected": "Expected output with digits replacing all '#' characters",
                    "description": "Test bothify with input consisting of three '#' characters with numerify set to true and letterify set to false."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "Test ?#"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                            "value": false
                        }
                    ],
                    "expected": "Expected output with uppercase letter replacing '?' and '#' remaining unchanged",
                    "description": "Test bothify with input containing '?' and '#' with isUpper set to true, and both numerify and letterify set to false."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": ""
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                    "description": "Test bothify with an empty input string, expecting an empty output."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "No replacements here"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
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
                    "expected": "No replacements here",
                    "description": "Test bothify with input containing no placeholders, expecting the same string as output."
                }
            ]
        }
    ]
}