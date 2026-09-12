{
    "group": "resolve method tests",
    "cases": [
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
                    "value": "{}"
                }
            ],
            "expected": "T",
            "description": "Test resolving a TypeVariable with no prior mapping in visitedTypeVariables."
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
                    "value": "{T: String.class}"
                }
            ],
            "expected": "String.class",
            "description": "Test resolving a TypeVariable with an existing mapping in visitedTypeVariables."
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
                    "value": "SomeClass[].class"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{}"
                }
            ],
            "expected": "SomeClass[].class",
            "description": "Test resolving a Class type that is an array."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeGenericArrayType"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeGenericArrayType.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "new GenericArrayType()"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "{}"
                }
            ],
            "expected": "new GenericArrayType()",
            "description": "Test resolving a GenericArrayType."
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
                    "value": "{}"
                }
            ],
            "expected": "new ParameterizedType()",
            "description": "Test resolving a ParameterizedType."
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
                    "value": "{}"
                }
            ],
            "expected": "new WildcardType()",
            "description": "Test resolving a WildcardType."
        }
    ]
}