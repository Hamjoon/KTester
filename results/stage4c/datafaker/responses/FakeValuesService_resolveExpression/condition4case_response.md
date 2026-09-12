{
    "group": "resolveExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": ""
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
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "",
            "description": "Test case for empty directive string. The expected behavior is to return the directive itself, which is an empty string."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "methodName"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "mockedAbstractProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "mockedMethodInvokeResult",
            "description": "Test case where the directive is a method name without arguments and current is an instance of AbstractProvider. The method should be invoked on the current object."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "provider.methodName"
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
                    "value": "mockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "mockedMethodInvokeResult",
            "description": "Test case for directive with a dot notation where the provider and method are specified. The method should be invoked on the obtained provider."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "nonExistentMethod"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "mockedAbstractProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "safeFetchResult",
            "description": "Test case for a non-existent method on an AbstractProvider. The method should fall back to a simple fetch using the directive."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "methodName"
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
                    "value": "mockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "mockedMethodInvokeResult",
            "description": "Test case where the directive is a method name without a dot and root is not null. The method should be resolved from the root object."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "ClassName.methodName"
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
                    "value": "mockedProviderRegistration"
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "mockedMethodInvokeResult",
            "description": "Test case for directive in the format ClassName.methodName. The method should be resolved and invoked using faker object and method references."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "invalid.method"
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
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": null,
            "description": "Test case for an invalid directive that does not resolve to any known method or fetch. The expected behavior is to return null."
        }
    ]
}