{
    "testCases": [
        {
            "group": "resolveExpression - With Arguments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "number_between"
                        },
                        {
                            "parameter": "args",
                            "value": ["1", "10"]
                        },
                        {
                            "parameter": "current",
                            "value": "MockedNumberProvider"
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
                    "expected": "A number between 1 and 10",
                    "description": "Test case where the directive is a method on the current provider that takes arguments. The method should return a number between the specified range."
                }
            ]
        },
        {
            "group": "resolveExpression - Invalid Directive with Dot Index",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "InvalidProvider.invalid_method"
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
                    "expected": null,
                    "description": "Test case where the directive contains an invalid provider and method. The method should return null."
                }
            ]
        },
        {
            "group": "resolveExpression - Current is Null and Valid Provider",
            "cases": [
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
                    "expected": "Expected company name from Company provider",
                    "description": "Test case where the directive is valid but current is null. The method should resolve to the Company provider and return a company name."
                }
            ]
        },
        {
            "group": "resolveExpression - Valid Directive with Arguments and Root",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "Address.city"
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
                    "expected": "Expected city name from Address provider",
                    "description": "Test case where the directive includes a valid address method. The method should resolve to the Address provider and return a city name."
                }
            ]
        },
        {
            "group": "resolveExpression - Null Arguments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "user.username"
                        },
                        {
                            "parameter": "args",
                            "value": null
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
                    "expected": "Expected username from User provider",
                    "description": "Test case where the directive is valid but the arguments are null. The method should resolve to the User provider and return a username."
                }
            ]
        }
    ]
}