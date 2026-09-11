{
    "test cases": [
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
                            "value": "StringBuilder.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "CharSequence.class"
                        }
                    ],
                    "expected": "StringBuilder.class",
                    "description": "Test when rawType is a subclass of supertype (CharSequence), expecting the generic supertype to be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "List.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "LinkedList.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "Collection.class"
                        }
                    ],
                    "expected": "Collection.class",
                    "description": "Test when rawType is a subclass of supertype (Collection), expecting the generic supertype to be returned."
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
                            "value": "List.class"
                        }
                    ],
                    "expected": "List.class",
                    "description": "Test when rawType implements supertype (List), expecting the generic supertype to be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "null"
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
                    "description": "Test when context is null but rawType is valid, expecting the method to return the supertype."
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
                            "value": "Serializable.class"
                        }
                    ],
                    "expected": "Object.class",
                    "description": "Test when rawType is a subclass of supertype (Serializable), expecting the context to be returned."
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
                            "value": "Cloneable.class"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when context and rawType are both null but supertype is valid, expecting a NullPointerException."
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
                            "value": "Collection.class"
                        }
                    ],
                    "expected": "Collection.class",
                    "description": "Test when rawType is a subclass of supertype (Collection), expecting the generic supertype to be returned."
                }
            ]
        }
    ]
}