{
    "group": "bothify method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 0 for digits and 'a' for letters"
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
            "expected": "000aaa",
            "description": "Test with mixed '#' and '?' characters, numerify and letterify enabled, lowercase letters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 0 for digits and 'a' for letters"
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
            "expected": "000AAA",
            "description": "Test with mixed '#' and '?' characters, numerify and letterify enabled, uppercase letters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 0 for digits"
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
            "expected": "000???",
            "description": "Test with mixed '#' and '?' characters, only numerify enabled, no letter conversion."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 'a' for letters"
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
            "expected": "###aaa",
            "description": "Test with mixed '#' and '?' characters, only letterify enabled, no digit conversion."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context"
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
            "expected": "###???",
            "description": "Test with mixed '#' and '?' characters, neither numerify nor letterify enabled, should return input unchanged."
        }
    ]
}