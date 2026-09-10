{
    "testCases": [
        {
            "group": "resolveExpression Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "Name.last_name"
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
                    "expected": "ExpectedLastName",
                    "description": "Test resolving a method from the Name provider for last name. The method should return the last name from the Name provider."
                },
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
                            "value": "MockedProvider"
                        },
                        {
                            "parameter": "context",
                            "value": "MockedFakerContext"
                        }
                    ],
                    "expected": "ExpectedCity",
                    "description": "Test resolving a method from the Address provider for city. The method should return the expected city name."
                },
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
                            "value": "MockedProvider"
                        },
                        {
                            "parameter": "context",
                            "value": "MockedFakerContext"
                        }
                    ],
                    "expected": "ExpectedCompanyName",
                    "description": "Test resolving a method from the Company provider. The method should return the expected company name."
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
                    "description": "Test resolving a non-existent provider method. The method should throw a RuntimeException indicating the provider could not be found."
                },
                {
                    "input": [
                        {
                            "parameter": "directive",
                            "value": "Address.zip_code"
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
                    "expected": "ExpectedZipCode",
                    "description": "Test resolving a method from the Address provider for zip code. The method should return the expected zip code."
                }
            ]
        }
    ]
}