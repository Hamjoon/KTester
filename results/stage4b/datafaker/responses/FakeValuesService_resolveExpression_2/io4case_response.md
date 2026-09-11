{
    "testCases": [
        {
            "group": "resolveExpression Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{number}"
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
                    "expected": "1",
                    "description": "Test with a directive that should resolve to a number."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "The price is #{number} dollars."
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
                    "expected": "The price is 42 dollars.",
                    "description": "Test with a directive embedded in a sentence to verify correct resolution."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name} is #{age} years old."
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
                    "expected": "John Doe is 30 years old.",
                    "description": "Test with multiple directives to verify that they resolve correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name} #{surname} #{address.city} #{address.country} #{unknownDirective}"
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
                    "expected": "RuntimeException: Unable to resolve #{unknownDirective} directive for FakerContext null.",
                    "description": "Test with an unknown directive to verify that it throws a RuntimeException."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{company.name} is located at #{address.street} #{address.city}."
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
                    "expected": "Acme Corp is located at 123 Main St.",
                    "description": "Test with a directive that resolves company name and address to ensure both are resolved correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.first} #{name.last} #{name.middle}."
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
                    "expected": "John A. Doe.",
                    "description": "Test with a directive that resolves first, middle, and last names to ensure all parts are resolved."
                }
            ]
        }
    ]
}