{
    "group": "resolveExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "methodName"
                },
                {
                    "parameter": "args",
                    "value": ["arg1", "arg2"]
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
            "expected": "mockedMethodInvokeResultWithArgs",
            "description": "Test case where the directive is a method name with arguments and current is an instance of AbstractProvider. The method should be invoked on the current object with the provided arguments."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "provider.methodName"
                },
                {
                    "parameter": "args",
                    "value": ["arg1"]
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
            "expected": "mockedMethodInvokeResultWithArgs",
            "description": "Test case for directive with dot notation where the provider and method are specified with arguments. The method should be invoked on the obtained provider with the provided arguments."
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
                    "value": "mockedNonAbstractProvider"
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
            "description": "Test case where the directive is a method name without arguments and current is not an instance of AbstractProvider. The method should fall back to a simple fetch using the directive."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "ClassName.methodName"
                },
                {
                    "parameter": "args",
                    "value": ["arg1", "arg2"]
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
            "expected": "mockedMethodInvokeResultWithArgs",
            "description": "Test case for directive in the format ClassName.methodName with arguments. The method should be resolved and invoked using faker object and method references with the provided arguments."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "nonExistentClass.methodName"
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
            "expected": "safeFetchResult",
            "description": "Test case for a directive with a non-existent class name. The method should fall back to a simple fetch using the directive."
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
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "mockedContext"
                }
            ],
            "expected": "null",
            "description": "Test case where the directive is a method name without a dot, root is null, and no current object is provided. The expected behavior is to return null."
        }
    ]
}