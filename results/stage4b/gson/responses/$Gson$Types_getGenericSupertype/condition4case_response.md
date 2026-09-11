{
    "group": "getGenericSupertype",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "String.class"
                },
                {
                    "parameter": "rawType",
                    "value": "String.class"
                },
                {
                    "parameter": "supertype",
                    "value": "String.class"
                }
            ],
            "expected": "String.class",
            "description": "Test when supertype is the same as rawType, expecting the context to be returned."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "List.class"
                },
                {
                    "parameter": "rawType",
                    "value": "ArrayList.class"
                },
                {
                    "parameter": "supertype",
                    "value": "List.class"
                }
            ],
            "expected": "List.class",
            "description": "Test when rawType is a subclass of supertype, expecting the generic supertype to be returned."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "Map.class"
                },
                {
                    "parameter": "rawType",
                    "value": "HashMap.class"
                },
                {
                    "parameter": "supertype",
                    "value": "Map.class"
                }
            ],
            "expected": "Map.class",
            "description": "Test when rawType is a subclass of supertype and supertype is an interface, expecting the generic supertype to be returned."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "Object.class"
                },
                {
                    "parameter": "rawType",
                    "value": "ArrayList.class"
                },
                {
                    "parameter": "supertype",
                    "value": "Serializable.class"
                }
            ],
            "expected": "Object.class",
            "description": "Test when rawType implements supertype, expecting the context to be returned."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "Object.class"
                },
                {
                    "parameter": "rawType",
                    "value": "ArrayList.class"
                },
                {
                    "parameter": "supertype",
                    "value": "Cloneable.class"
                }
            ],
            "expected": "Cloneable.class",
            "description": "Test when rawType does not implement supertype, expecting the method to return supertype."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "String.class"
                },
                {
                    "parameter": "supertype",
                    "value": "Object.class"
                }
            ],
            "expected": "Object.class",
            "description": "Test when context is null, expecting the method to return the supertype."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "null"
                },
                {
                    "parameter": "supertype",
                    "value": "Object.class"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when both context and rawType are null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "Object.class"
                },
                {
                    "parameter": "supertype",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when supertype is null, expecting a NullPointerException."
        }
    ]
}