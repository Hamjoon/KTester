{
    "testCases": [
        {
            "group": "resolve with TypeVariable",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "TypeVariable<SomeClass>"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "resolved Type for TypeVariable<SomeClass>",
                    "description": "Test resolving a TypeVariable in the context of a class."
                }
            ]
        },
        {
            "group": "resolve with Class array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "SomeClass[].class"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "SomeClass[].class",
                    "description": "Test resolving an array type for a class."
                }
            ]
        },
        {
            "group": "resolve with GenericArrayType",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "GenericArrayType<SomeClass>"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "GenericArrayType<SomeClass>",
                    "description": "Test resolving a GenericArrayType."
                }
            ]
        },
        {
            "group": "resolve with ParameterizedType",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Collection.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Collection.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "ParameterizedType<Collection<SomeClass>>"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "ParameterizedType<Collection<SomeClass>>",
                    "description": "Test resolving a ParameterizedType."
                }
            ]
        },
        {
            "group": "resolve with WildcardType",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "WildcardType"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "WildcardType",
                    "description": "Test resolving a WildcardType."
                }
            ]
        },
        {
            "group": "resolve with infinite recursion",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "SomeClass.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "TypeVariable<SomeClass>"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<TypeVariable<?>, Type>()"
                        }
                    ],
                    "expected": "TypeVariable<SomeClass>",
                    "description": "Test resolving a TypeVariable that causes infinite recursion."
                }
            ]
        }
    ]
}