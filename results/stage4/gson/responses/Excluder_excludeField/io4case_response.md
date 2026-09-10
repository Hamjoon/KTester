{
    "testCases": [
        {
            "group": "excludeField - Modifier Check Additional",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Modifier.STATIC"
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
                            "value": "Field without Modifier.STATIC"
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
            "group": "excludeField - Version Check Additional",
            "cases": [
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
                    "description": "Test case where the field's version is not valid according to the Until annotation."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with valid Since annotation and no Until annotation"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field's version is valid according to the Since annotation."
                }
            ]
        },
        {
            "group": "excludeField - Expose Annotation Check Additional",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Expose annotation set to serialize = false"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
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
                            "value": "false"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field has an Expose annotation and is marked for deserialization."
                }
            ]
        },
        {
            "group": "excludeField - Inner Class Check Additional",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of inner class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
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
                            "value": "false"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where inner class serialization is disabled but the field is not of an inner class."
                }
            ]
        },
        {
            "group": "excludeField - Anonymous or Non-Static Local Check Additional",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of non-static local class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is of a non-static local class and should be excluded."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field of static local class"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is of a static local class and should not be excluded."
                }
            ]
        },
        {
            "group": "excludeField - Exclusion Strategy Check Additional",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with exclusion strategy that does not skip it"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
                        }
                    ],
                    "expected": false,
                    "description": "Test case where the field is not excluded by the provided exclusion strategy."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with exclusion strategy that skips it"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
                        }
                    ],
                    "expected": true,
                    "description": "Test case where the field is excluded by the provided exclusion strategy."
                }
            ]
        }
    ]
}