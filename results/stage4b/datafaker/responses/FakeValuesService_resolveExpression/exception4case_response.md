{
    "group": "resolveExpression - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "first_name"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "MockedAbstractProviderInstance"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where the method invocation on AbstractProvider throws an exception, causing a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "invalid_method"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "MockedAbstractProviderInstance"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where invoking an invalid method on AbstractProvider throws an exception, resulting in a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "Name.first_name"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "MockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where the method invocation on the resolved Name provider throws an exception, causing a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "Address.street_title"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "MockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where the safeFetch call for Address.street_title throws an exception, resulting in a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "invalid_provider.invalid_method"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "MockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where an invalid provider and method combination is used, and an exception is thrown during resolution."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "Company.name"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "MockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException",
            "description": "Test case where the resolution of a valid directive throws an exception, resulting in a RuntimeException."
        }
    ]
}