{
    "testCases": [
        {
            "group": "resolveExpression",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{address.city}"
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
                    "expected": "resolved full name and city",
                    "description": "Test with an expression that combines first name, last name, and city. The output should be a resolved string containing a full name and city."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{invalid.directive}"
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
                    "description": "Test with a mixture of valid and invalid expressions. The method should throw a RuntimeException due to the invalid directive."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{address.streetName} #{address.streetSuffix}"
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
                    "expected": "resolved full name and street name",
                    "description": "Test with an expression that combines first name, last name, street name, and street suffix. The output should be a resolved string containing a full name and street name."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{address.city} #{address.zipCode}"
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
                    "expected": "resolved full name and zip code",
                    "description": "Test with an expression that combines first name, last name, city, and zip code. The output should be a resolved string containing a full name and zip code."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{address.streetName} #{address.streetSuffix} #{address.city} #{address.zipCode}"
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
                    "expected": "resolved full address",
                    "description": "Test with a complex expression that combines first name, last name, street name, street suffix, city, and zip code. The output should be a resolved string containing a full address."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "#{name.firstName} #{name.lastName} #{name.firstName} #{name.lastName}"
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
                    "expected": "resolved repeated full name",
                    "description": "Test with an expression that repeats the first name and last name. The output should be a resolved string containing repeated full names."
                }
            ]
        }
    ]
}