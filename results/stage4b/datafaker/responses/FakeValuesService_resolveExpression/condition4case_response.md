{
    "testCases": [
        {
            "group": "resolveExpression - Empty Directive",
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
                    "description": "Test case where the directive is empty. The method should return an empty string."
                }
            ]
        },
        {
            "group": "resolveExpression - No Dot Index and Current is AbstractProvider",
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
                    "expected": "Expected first name from the provider",
                    "description": "Test case where directive is a method on the AbstractProvider. The method should be invoked and return the first name."
                }
            ]
        },
        {
            "group": "resolveExpression - No Dot Index and Current is Not AbstractProvider",
            "cases": [
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
                            "value": "SomeNonAbstractProvider"
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
                    "expected": null,
                    "description": "Test case where directive is invalid and current is not an AbstractProvider. The method should return null."
                }
            ]
        },
        {
            "group": "resolveExpression - With Dot Index",
            "cases": [
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
                    "expected": "Expected first name from Name provider",
                    "description": "Test case where the directive includes a dot. The method should resolve to the Name provider and return the first name."
                }
            ]
        },
        {
            "group": "resolveExpression - Last Ditch Effort Fetch",
            "cases": [
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
                    "expected": "Expected street title from Address provider",
                    "description": "Test case where the directive cannot be resolved normally, but should fall back to safeFetch for Address.street_title."
                }
            ]
        }
    ]
}