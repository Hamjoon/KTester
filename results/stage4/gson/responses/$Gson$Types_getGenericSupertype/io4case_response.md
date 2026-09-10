{
    "testCases": [
        {
            "group": "getGenericSupertype",
            "cases": [
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
                            "value": "Serializable.class"
                        }
                    ],
                    "expected": "Serializable.class",
                    "description": "Test when context is null and rawType is Object, expecting to return the supertype Serializable."
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
                            "value": "Collection.class"
                        }
                    ],
                    "expected": "java.util.Collection",
                    "description": "Test when context is null and rawType is ArrayList, expecting to return the generic superinterface Collection."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "LinkedList.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "List.class"
                        }
                    ],
                    "expected": "java.util.List",
                    "description": "Test when supertype is an interface implemented by rawType, expecting to return the generic interface List."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
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
                    "expected": "java.util.Map",
                    "description": "Test when supertype is a superinterface of rawType, expecting to return the generic superinterface Map."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "HashMap.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "Object.class"
                        }
                    ],
                    "expected": "java.lang.Object",
                    "description": "Test when supertype is the superclass of rawType, expecting to return the generic superclass Object."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
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
                    "expected": "java.lang.Cloneable",
                    "description": "Test when supertype is a superinterface of rawType that is not directly implemented, expecting to return the correct generic superinterface Cloneable."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "ArrayList.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "Runnable.class"
                        }
                    ],
                    "expected": "Runnable.class",
                    "description": "Test when supertype is not related to rawType, expecting to return the supertype Runnable."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "Integer.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "ArrayList.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "Number.class"
                        }
                    ],
                    "expected": "java.lang.Number",
                    "description": "Test when context is Integer, rawType is ArrayList, and supertype is Number, expecting to return the supertype Number."
                }
            ]
        }
    ]
}