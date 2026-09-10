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
                    "value": "null"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "new HashMap<>()"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test passing null as toResolve to ensure a NullPointerException is thrown."
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
                    "value": "new TypeVariableImpl()"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test passing null as visitedTypeVariables to ensure a NullPointerException is thrown."
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
                    "value": "new WildcardTypeImpl(new Type[]{String.class}, new Type[]{})"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "new HashMap<>()"
                }
            ],
            "expected": "supertypeOf(String.class)",
            "description": "Test resolving a WildcardType with only upper bounds."
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
        }
    ]
}