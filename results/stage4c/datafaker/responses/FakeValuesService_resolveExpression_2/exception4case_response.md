{
    "group": "resolveExpression Edge Case Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{directive} extra text"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "mockProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an expression containing an unresolved directive followed by extra text to ensure it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{directive1} #{directive2}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "mockProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an expression containing multiple unresolved directives to ensure it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{directive}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with a null root parameter to ensure it throws a RuntimeException when attempting to resolve a directive."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{directive}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "mockProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with a null context parameter to ensure it throws a RuntimeException when attempting to resolve a directive."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{directive}"
                },
                {
                    "parameter": "current",
                    "value": "invalidCurrentObject"
                },
                {
                    "parameter": "root",
                    "value": "mockProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an invalid current object to ensure it throws a RuntimeException when attempting to resolve a directive."
        }
    ]
}