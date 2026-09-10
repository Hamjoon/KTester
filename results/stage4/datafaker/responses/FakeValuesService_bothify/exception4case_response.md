{
    "group": "bothify method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": null
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
                    "value": true
                },
                {
                    "parameter": "letterify",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test bothify with null input string, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "Hello #?"
                },
                {
                    "parameter": "context",
                    "value": null
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
            "expected": "NullPointerException",
            "description": "Test bothify with null context, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "Hello #?"
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
                    "value": true
                },
                {
                    "parameter": "letterify",
                    "value": true
                }
            ],
            "expected": "Expected output with uppercase letters and digits replacing # and ?",
            "description": "Test bothify with valid input to ensure it functions correctly."
        }
    ]
}