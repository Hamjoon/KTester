{
    "group": "resolve method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeClass"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeClass.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "Void.TYPE"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{}"
                }
            ],
            "expected": "Void.TYPE",
            "description": "Test resolving a Class type that is Void.TYPE."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeGenericType<T>"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeGenericType.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "T"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{T: Void.TYPE}"
                }
            ],
            "expected": "T",
            "description": "Test resolving a TypeVariable with Void.TYPE as the existing mapping in visitedTypeVariables."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeWildcardType"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeWildcardType.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "new WildcardType()"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{WildcardType: String.class}"
                }
            ],
            "expected": "new WildcardType()",
            "description": "Test resolving a WildcardType with an existing mapping in visitedTypeVariables."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeParameterizedType"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeParameterizedType.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "new ParameterizedType()"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{ParameterizedType: List.class}"
                }
            ],
            "expected": "new ParameterizedType()",
            "description": "Test resolving a ParameterizedType with an existing mapping in visitedTypeVariables."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeClass"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeClass.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "SomeInterface.class"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{}"
                }
            ],
            "expected": "SomeInterface.class",
            "description": "Test resolving a Class type that is an interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeGenericType<T>"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeGenericType.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "T[]"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{T: Integer.class}"
                }
            ],
            "expected": "Integer[].class",
            "description": "Test resolving a TypeVariable that is an array with an existing mapping in visitedTypeVariables."
        }
    ]
}