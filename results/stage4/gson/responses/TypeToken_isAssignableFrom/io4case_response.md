{
    "test cases": [
        {
            "group": "isAssignableFrom - Generic Array Type Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeGenericArrayType<SomeType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeGenericArrayType<SomeOtherType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is a generic array type with different type arguments than 'to'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeGenericArrayType<SomeType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeGenericArrayType<SomeType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' and 'to' are generic array types with the same type arguments. The method should return true."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Nested Parameterized Types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeOuterType<SomeInnerType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeOuterType<SomeOtherInnerType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is a nested parameterized type with different inner types than 'to'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeOuterType<SomeInnerType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeOuterType<SomeInnerType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' and 'to' are nested parameterized types with the same inner types. The method should return true."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Wildcard Type Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeWildcardType<? extends SomeBaseType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeWildcardType<? extends SomeOtherBaseType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' is a wildcard type with a different upper bound than 'to'. The method should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeWildcardType<? extends SomeBaseType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeWildcardType<? extends SomeBaseType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": true,
                    "description": "Test when 'from' and 'to' are wildcard types with the same upper bounds. The method should return true."
                }
            ]
        },
        {
            "group": "isAssignableFrom - Empty Type Var Map",
            "cases": [
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
                    "description": "Test when 'from' and 'to' are the same parameterized type with an empty type variable map. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "from",
                            "value": "SomeParameterizedType<SomeType>"
                        },
                        {
                            "parameter": "to",
                            "value": "SomeParameterizedType<AnotherType>"
                        },
                        {
                            "parameter": "typeVarMap",
                            "value": "{}"
                        }
                    ],
                    "expected": false,
                    "description": "Test when 'from' and 'to' are parameterized types with different type arguments and an empty type variable map. The method should return false."
                }
            ]
        }
    ]
}