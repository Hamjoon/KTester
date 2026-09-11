{
    "testCases": [
        {
            "group": "getBoundFields - Interface Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyInterface.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyInterface.class"
                        },
                        {
                            "parameter": "blockInaccessible",
                            "value": "false"
                        },
                        {
                            "parameter": "isRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "{}",
                    "description": "Test for an interface type, should return an empty map."
                }
            ]
        },
        {
            "group": "getBoundFields - Reflection Access Filter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClass.class"
                        },
                        {
                            "parameter": "blockInaccessible",
                            "value": "false"
                        },
                        {
                            "parameter": "isRecord",
                            "value": "false"
                        }
                    ],
                    "expected": {
                        "exception": "JsonIOException",
                        "message": "ReflectionAccessFilter does not permit using reflection for MyClass (supertype of MyClass). Register a TypeAdapter for this type or adjust the access filter."
                    },
                    "description": "Test for a class that is blocked by ReflectionAccessFilter, should throw JsonIOException."
                }
            ]
        },
        {
            "group": "getBoundFields - Field Serialization and Deserialization",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClass.class"
                        },
                        {
                            "parameter": "blockInaccessible",
                            "value": "true"
                        },
                        {
                            "parameter": "isRecord",
                            "value": "false"
                        }
                    ],
                    "expected": {
                        "result": {
                            "field1": "BoundField for field1",
                            "field2": "BoundField for field2"
                        }
                    },
                    "description": "Test for a class with fields that should be serialized and deserialized, should return a map with BoundFields."
                }
            ]
        },
        {
            "group": "getBoundFields - Multiple JSON Fields Conflict",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyConflictClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyConflictClass.class"
                        },
                        {
                            "parameter": "blockInaccessible",
                            "value": "false"
                        },
                        {
                            "parameter": "isRecord",
                            "value": "false"
                        }
                    ],
                    "expected": {
                        "exception": "IllegalArgumentException",
                        "message": "Class MyConflictClass declares multiple JSON fields named 'fieldName'; conflict is caused by fields field1 and field2."
                    },
                    "description": "Test for a class with multiple fields having the same JSON name, should throw IllegalArgumentException."
                }
            ]
        },
        {
            "group": "getBoundFields - Static Field Handling in Records",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyRecord.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyRecord.class"
                        },
                        {
                            "parameter": "blockInaccessible",
                            "value": "false"
                        },
                        {
                            "parameter": "isRecord",
                            "value": "true"
                        }
                    ],
                    "expected": {
                        "result": {
                            "field1": "BoundField for field1",
                            "field2": "BoundField for field2"
                        }
                    },
                    "description": "Test for a record with a static field, should return a map with BoundFields excluding static fields."
                }
            ]
        }
    ]
}