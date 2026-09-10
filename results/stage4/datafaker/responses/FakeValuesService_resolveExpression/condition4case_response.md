{
    "group": "resolveExpression Tests",
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
                    "value": null
                }
            ],
            "expected": "",
            "description": "Test with an empty directive. The method should return an empty string."
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
            "expected": "ExpectedValueFromTestMethod",
            "description": "Test resolving a method on the current provider when args are empty. The method should return the value from the testMethod."
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
            "expected": "ExpectedFirstName",
            "description": "Test resolving a method from a nested provider. The method should return the first name from the Name provider."
        },
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
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "ExpectedValueWithArgs",
            "description": "Test resolving a method on the current provider with arguments. The method should return the value from the testMethod using the provided arguments."
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
            "expected": "ExpectedValueFromClassMethod",
            "description": "Test resolving a method from a class in the root provider. The method should return the expected value from ClassName.method_name."
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
            "description": "Test resolving a non-existent method on the current provider. The method should throw a RuntimeException indicating the method could not be found."
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
                    "value": "MockedProvider"
                },
                {
                    "parameter": "context",
                    "value": "MockedFakerContext"
                }
            ],
            "expected": "ExpectedStreetTitle",
            "description": "Test resolving a dynamic method from the Address provider. The method should return the expected street title."
        }
    ]
}