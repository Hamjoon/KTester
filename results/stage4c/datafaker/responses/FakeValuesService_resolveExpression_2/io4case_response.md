{
    "test cases": [
        {
            "group": "resolveExpression Additional Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.middleName} #{name.lastName}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "Resolved full name with middle name",
                    "description": "Test with an expression containing valid directives for first, middle, and last names to ensure it returns a resolved full name including the middle name."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{address.streetAddress} #{address.city}, #{address.state} #{address.zipCode}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "Resolved full address",
                    "description": "Test with an expression containing valid directives for street address, city, state, and zip code to ensure it returns a resolved full address."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "Contact: #{phoneNumber}, Email: #{emailAddress}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "Resolved contact information",
                    "description": "Test with an expression containing valid directives for phone number and email address to ensure it returns resolved contact information."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{address.city} #{address.unknownField}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "RuntimeException",
                    "description": "Test with an expression containing a valid directive followed by an unknown directive to ensure it throws a RuntimeException."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.firstName}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "Resolved first name twice",
                    "description": "Test with an expression containing the same valid directive repeated to ensure it returns the resolved value twice."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "{}"
                        },
                        {
                            "parameter": "current",
                            "value": null
                        },
                        {
                            "parameter": "root",
                            "value": "mockProviderRegistration"
                        },
                        {
                            "parameter": "context",
                            "value": "mockFakerContext"
                        }
                    ],
                    "expected": "RuntimeException",
                    "description": "Test with an empty directive to ensure it throws a RuntimeException."
                }
            ]
        }
    ]
}