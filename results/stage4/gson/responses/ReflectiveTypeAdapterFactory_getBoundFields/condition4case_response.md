{
    "test_cases": [
        {
            "group": "getBoundFields",
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
                    "expected": "A map with bound fields for MyClass",
                    "description": "Test case to verify that getBoundFields returns a map of bound fields for a normal class."
                },
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
                    "expected": "An empty map",
                    "description": "Test case to verify that getBoundFields returns an empty map when the raw type is an interface."
                },
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
                            "value": "true"
                        }
                    ],
                    "expected": "A map with bound fields for MyClass, with appropriate handling for records",
                    "description": "Test case to verify that getBoundFields correctly handles records and returns bound fields."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithStaticField.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithStaticField.class"
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
                    "expected": "A map excluding the static field",
                    "description": "Test case to verify that getBoundFields excludes static fields when isRecord is true."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithInaccessibleField.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithInaccessibleField.class"
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
                    "expected": "A JsonIOException indicating reflection access is blocked",
                    "description": "Test case to verify that getBoundFields throws JsonIOException when reflection access is blocked for an inherited field."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithMultipleJsonFields.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithMultipleJsonFields.class"
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
                    "expected": "An IllegalArgumentException indicating multiple JSON fields conflict",
                    "description": "Test case to verify that getBoundFields throws IllegalArgumentException when multiple fields with the same JSON name are declared."
                }
            ]
        }
    ]
}