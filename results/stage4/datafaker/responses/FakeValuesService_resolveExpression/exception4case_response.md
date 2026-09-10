{
    "group": "resolveExpression Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "testMethod"
                },
                {
                    "parameter": "args",
                    "value": ["arg1", "arg2"]
                },
                {
                    "parameter": "current",
                    "value": "MockedProvider"
                },
                {
                    "parameter": "root",
                    "value": "MockedProvider"
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a method on the current provider with arguments where the method throws an exception. The method should throw a RuntimeException."
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
                    "value": "MockedProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a non-existent method on the current provider when root is null. The method should throw a RuntimeException indicating the method could not be found."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "ClassName.method_name"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "MockedProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a method from a class in the root provider when root is null. The method should throw a RuntimeException indicating the class method could not be found."
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
                    "value": "MockedProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a dynamic method from the Address provider when root is null. The method should throw a RuntimeException indicating the method could not be found."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "nonExistentProvider.method"
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
                    "value": "MockedProvider"
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a non-existent provider method when current is null. The method should throw a RuntimeException indicating the provider could not be found."
        },
        {
            "input": [
                {
                    "parameter": "directive",
                    "value": "testMethod"
                },
                {
                    "parameter": "args",
                    "value": []
                },
                {
                    "parameter": "current",
                    "value": "MockedProvider"
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test resolving a method on the current provider when the method throws an exception. The method should throw a RuntimeException."
        }
    ]
}