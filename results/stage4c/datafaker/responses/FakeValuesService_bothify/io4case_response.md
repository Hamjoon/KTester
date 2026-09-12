{
    "group": "bothify method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "##??##"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 9 for digits and 'z' for letters"
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
            "expected": "99zz99",
            "description": "Test with alternating '#' and '?' characters, numerify and letterify enabled, lowercase letters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "?????"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 'm' for letters"
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
            "expected": "MMMMM",
            "description": "Test with only '?' characters, letterify enabled with uppercase, no numerify."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 5 for digits"
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
            "expected": "555",
            "description": "Test with only '#' characters, numerify enabled, no letterify."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "abc???xyz"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService returning 'k' for letters"
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
            "expected": "abckkkxyz",
            "description": "Test with non-placeholder characters mixed with '?', only letterify enabled, lowercase letters."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": ""
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
            "expected": "",
            "description": "Test with empty input string, neither numerify nor letterify enabled, should return empty string."
        }
    ]
}