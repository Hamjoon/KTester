{
    "group": "excludeField - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "null"
                },
                {
                    "parameter": "serialize",
                    "value": "true"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the field parameter is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "Field with invalid annotations (invalid Since/Until)"
                },
                {
                    "parameter": "serialize",
                    "value": "true"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the field has invalid annotations that cause an IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "Field with unsupported modifier"
                },
                {
                    "parameter": "serialize",
                    "value": "true"
                }
            ],
            "expected": "UnsupportedOperationException",
            "description": "Test case where the field has an unsupported modifier that should throw an UnsupportedOperationException."
        }
    ]
}