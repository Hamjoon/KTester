{
    "group": "bothify exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": null
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
            "expected": "NullPointerException",
            "description": "Test with null input string to ensure method handles null values appropriately."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": null
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
            "expected": "NullPointerException",
            "description": "Test with null context to ensure method handles null context appropriately."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "###???"
                },
                {
                    "parameter": "context",
                    "value": "mocked context with RandomService throwing exception"
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
            "expected": "RuntimeException",
            "description": "Test with context's RandomService throwing an exception to ensure method handles exceptions from RandomService."
        }
    ]
}