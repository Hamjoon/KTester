{
    "testCases": [
        {
            "group": "excludeField - Modifier Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Modifier.TRANSIENT"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has a modifier that should cause it to be excluded."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field without Modifier.TRANSIENT"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field does not have a modifier that should cause it to be excluded."
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
                    "expected": true,
                    "description": "Test case where the field's version is not valid according to the Since and Until annotations."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with valid Since and Until annotations"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field's version is valid according to the Since and Until annotations."
                }
            ]
        },
        {
            "group": "excludeField - Synthetic Check",
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
                    "description": "Test case where the field is synthetic and should be excluded."
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
                    "description": "Test case where the field is not synthetic and should not be excluded."
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
                            "value": "Field without Expose annotation"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field does not have an Expose annotation and is required for serialization."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Expose annotation set to serialize = false"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has an Expose annotation but is not marked for serialization."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Expose annotation set to serialize = true"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field has an Expose annotation and is marked for serialization."
                }
            ]
        },
        {
            "group": "excludeField - Inner Class Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of inner class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where inner class serialization is disabled and the field is of an inner class."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of non-inner class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where inner class serialization is disabled but the field is not of an inner class."
                }
            ]
        },
        {
            "group": "excludeField - Anonymous or Non-Static Local Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of anonymous class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is of an anonymous class and should be excluded."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of static local class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is of a static local class and should not be excluded."
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
                            "value": "Field with exclusion strategy that skips it"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is excluded by the provided exclusion strategy."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with exclusion strategy that does not skip it"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is not excluded by the provided exclusion strategy."
                }
            ]
        }
    ]
}