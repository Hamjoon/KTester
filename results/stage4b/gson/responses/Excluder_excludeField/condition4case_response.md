{
    "testCases": [
        {
            "group": "excludeField - Modifier Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with transient modifier"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has a transient modifier. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field without transient or static modifier"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field does not have transient or static modifiers. The method should return false."
                }
            ]
        },
        {
            "group": "excludeField - Version Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Since annotation and no Until annotation"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field has a Since annotation and the Excluder's version is not set to ignore. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Until annotation and no Since annotation"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has a Until annotation and the Excluder's version is not set to ignore. The method should return true."
                }
            ]
        },
        {
            "group": "excludeField - Synthetic Field Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Synthetic field"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is synthetic. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Non-synthetic field"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is not synthetic. The method should return false."
                }
            ]
        },
        {
            "group": "excludeField - Expose Annotation Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Expose annotation that does not serialize"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has an Expose annotation that does not allow serialization. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Expose annotation that allows serialization"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field has an Expose annotation that allows serialization. The method should return false."
                }
            ]
        },
        {
            "group": "excludeField - Inner Class and Anonymous Class Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Inner class field"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is an inner class and inner class serialization is disabled. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Static nested class field"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is a static nested class. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Anonymous class field"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is an anonymous class. The method should return true."
                }
            ]
        },
        {
            "group": "excludeField - Exclusion Strategy Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that matches exclusion strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field matches an exclusion strategy. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that does not match any exclusion strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field does not match any exclusion strategy. The method should return false."
                }
            ]
        }
    ]
}