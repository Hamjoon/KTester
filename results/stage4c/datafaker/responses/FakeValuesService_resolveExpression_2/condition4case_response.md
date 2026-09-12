{
    "group": "resolveExpression Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simple text"
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
            "expected": "simple text",
            "description": "Test with a simple text expression without any curly braces to ensure it returns the same text."
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
                    "value": "mockFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an expression containing an unresolved directive to ensure it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name.firstName} #{name.lastName}"
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
            "expected": "Resolved full name",
            "description": "Test with an expression containing valid directives for first and last names to ensure it returns a resolved full name."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{address.city}, #{address.state}"
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
            "expected": "Resolved city and state",
            "description": "Test with an expression containing valid directives for city and state to ensure it returns a resolved city and state."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, #{name.firstName}!"
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
            "expected": "Hello, resolved first name!",
            "description": "Test with a mixed expression containing text and a valid directive to ensure it returns the correct resolved text."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{unknown.directive}"
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
            "description": "Test with an expression containing an unknown directive to ensure it throws a RuntimeException."
        }
    ]
}