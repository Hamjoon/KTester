{
    "testCases": [
        {
            "group": "resolveExpression",
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
                            "value": null
                        },
                        {
                            "parameter": "context",
                            "value": null
                        }
                    ],
                    "expected": "simple text",
                    "description": "Test with a simple string input that does not contain any expressions. The output should be the same as the input."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "ProviderRegistration instance"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance"
                        }
                    ],
                    "expected": "resolved first name",
                    "description": "Test with a valid expression that should resolve to a first name. The output should be the resolved first name."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{address.city}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "ProviderRegistration instance"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance"
                        }
                    ],
                    "expected": "resolved city name",
                    "description": "Test with a valid expression that should resolve to a city name. The output should be the resolved city name."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{invalid.directive}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "ProviderRegistration instance"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance"
                        }
                    ],
                    "expected": "RuntimeException",
                    "description": "Test with an invalid expression that cannot be resolved. The method should throw a RuntimeException indicating the inability to resolve the directive."
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
                            "value": "ProviderRegistration instance"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance"
                        }
                    ],
                    "expected": "resolved full name",
                    "description": "Test with a complex expression that combines multiple expressions. The output should be a resolved full name consisting of a first name and last name."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{address.streetName} #{address.streetSuffix}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "ProviderRegistration instance"
                        },
                        {
                            "parameter": "context",
                            "value": "FakerContext instance"
                        }
                    ],
                    "expected": "resolved street name",
                    "description": "Test with an expression that resolves to a street name and suffix. The output should be a resolved street name."
                }
            ]
        }
    ]
}