{
    "testCases": [
        {
            "group": "isAssignableFrom - Null Type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": null
                        },
                        {
                            "parameter": "to",
                            "value": "SomeParameterizedType"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is null. The method should return false."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Equal Types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeParameterizedType"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeParameterizedType"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' and 'to' are equal types. The method should return true."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Parameterized Type Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeParameterizedType<SomeType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeParameterizedType<SomeOtherType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is a parameterized type with different type arguments than 'to'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeParameterizedType<SomeType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeParameterizedType<SomeType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' and 'to' are parameterized types with the same type arguments. The method should return true."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Interface Assignment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeClassImplementsInterface"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeInterface"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' is a class that implements an interface. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeClassDoesNotImplementInterface"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeInterface"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is a class that does not implement the interface 'to'. The method should return false."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Superclass Assignment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SubClass"
                        },
                        {
                            "parameter": "to",
                            "value": "SuperClass"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' is a subclass of 'to'. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "UnrelatedClass"
                        },
                        {
                            "parameter": "to",
                            "value": "SuperClass"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' has no relationship with 'to'. The method should return false."
                }
            ]
        }
    ]
}