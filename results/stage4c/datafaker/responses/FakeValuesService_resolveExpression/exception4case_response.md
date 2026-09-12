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
            "expected": "RuntimeException",
            "description": "Test case where the method invocation on the current object throws an exception. It should result in a RuntimeException being thrown."
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
            "expected": "RuntimeException",
            "description": "Test case where the method invocation on the provider obtained from the directive throws an exception, resulting in a RuntimeException."
        }
    ]
}