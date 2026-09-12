{
    "group": "matchRouter Method Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": null
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when relativePath is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when method is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource"
                },
                {
                    "parameter": "method",
                    "value": "INVALID_METHOD"
                }
            ],
            "expected": "RouterMappingException",
            "description": "Test when method is not a valid HTTP method, expecting a RouterMappingException."
        }
    ]
}