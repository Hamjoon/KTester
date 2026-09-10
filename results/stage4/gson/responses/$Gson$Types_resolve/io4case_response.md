{
    "testCases": [
        {
            "group": "resolve method test cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new WildcardTypeImpl(new Type[]{}, new Type[]{String.class})"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "subtypeOf(String.class)",
                    "description": "Test resolving a WildcardType with only lower bounds."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "List.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "List.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new ParameterizedTypeImpl(List.class, new Type[]{new TypeVariableImpl()})"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "ParameterizedType with resolved arguments",
                    "description": "Test resolving a ParameterizedType with a TypeVariable as an argument."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Map.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Map.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new ParameterizedTypeImpl(Map.class, new Type[]{String.class, new TypeVariableImpl()})"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "ParameterizedType with resolved arguments",
                    "description": "Test resolving a ParameterizedType with a TypeVariable as one of the arguments."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new GenericArrayTypeImpl(new TypeVariableImpl())"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "TypeVariable resolved to a specific type",
                    "description": "Test resolving a GenericArrayType that contains a TypeVariable."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new GenericArrayTypeImpl(String.class)"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "String[].class",
                    "description": "Test resolving a GenericArrayType with a specific component type."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "contextRawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "toResolve",
                            "value": "new WildcardTypeImpl(new Type[]{String.class}, new Type[]{String.class})"
                        },
                        {
                            "parameter": "visitedTypeVariables",
                            "value": "new HashMap<>()"
                        }
                    ],
                    "expected": "supertypeOf(String.class)",
                    "description": "Test resolving a WildcardType with both upper and lower bounds being the same."
                }
            ]
        }
    ]
}