{
    "test cases": [
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
                            "value": "TypeToken.get(MyClassWithPrivateField.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithPrivateField.class"
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
                    "expected": "A map with bound fields including private fields",
                    "description": "Test case to verify that getBoundFields includes private fields when blockInaccessible is false."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithInheritedFields.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithInheritedFields.class"
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
                    "expected": "A map with bound fields for inherited fields",
                    "description": "Test case to verify that getBoundFields correctly handles inherited fields when access is not blocked."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithNonSerializableField.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithNonSerializableField.class"
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
                    "expected": "A map excluding non-serializable fields",
                    "description": "Test case to verify that getBoundFields excludes fields that are non-serializable."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithAllModifiers.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithAllModifiers.class"
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
                    "expected": "A map with only accessible fields",
                    "description": "Test case to verify that getBoundFields returns only accessible fields when blockInaccessible is true."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyEmptyClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyEmptyClass.class"
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
                    "description": "Test case to verify that getBoundFields returns an empty map when the class has no fields."
                }
            ]
        }
    ]
}