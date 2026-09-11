{
    "testCases": [
        {
            "group": "getBoundFields - Inaccessible Field Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyClassWithInaccessibleFields.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyClassWithInaccessibleFields.class"
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
                            "accessibleField": "BoundField for accessibleField"
                        }
                    },
                    "description": "Test for a class with both accessible and inaccessible fields when blockInaccessible is true, should return only BoundFields for accessible fields."
                }
            ]
        },
        {
            "group": "getBoundFields - Record Field Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(MyRecordWithAccessors.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "MyRecordWithAccessors.class"
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
                            "field1": "BoundField for field1 using accessor",
                            "field2": "BoundField for field2 using accessor"
                        }
                    },
                    "description": "Test for a record with accessors for fields, should return BoundFields using the accessor methods."
                }
            ]
        },
        {
            "group": "getBoundFields - Empty Class Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(EmptyClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "EmptyClass.class"
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
                    "description": "Test for an empty class with no fields, should return an empty map."
                }
            ]
        },
        {
            "group": "getBoundFields - Field with SerializedName Annotation",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ClassWithSerializedName.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "ClassWithSerializedName.class"
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
                        "message": "SerializedName on fieldName is not supported"
                    },
                    "description": "Test for a class where a field has a SerializedName annotation but the accessor does not, should throw JsonIOException."
                }
            ]
        },
        {
            "group": "getBoundFields - Multiple Inheritance Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "new Gson()"
                        },
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(DerivedClass.class)"
                        },
                        {
                            "parameter": "raw",
                            "value": "DerivedClass.class"
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
                        "result": {
                            "baseField": "BoundField for baseField",
                            "derivedField": "BoundField for derivedField"
                        }
                    },
                    "description": "Test for a derived class that inherits fields from a base class, should return BoundFields for both base and derived fields."
                }
            ]
        }
    ]
}