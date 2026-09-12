{
    "group": "ReflectiveTypeAdapterFactory.getBoundFields",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "MockGsonInstance"
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
                    "value": false
                },
                {
                    "parameter": "isRecord",
                    "value": false
                }
            ],
            "expected": "An empty map",
            "description": "Test case where the class has no fields, should return an empty map."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "MockGsonInstance"
                },
                {
                    "parameter": "type",
                    "value": "TypeToken.get(StaticFieldsClass.class)"
                },
                {
                    "parameter": "raw",
                    "value": "StaticFieldsClass.class"
                },
                {
                    "parameter": "blockInaccessible",
                    "value": false
                },
                {
                    "parameter": "isRecord",
                    "value": false
                }
            ],
            "expected": "A map with bound fields excluding static fields",
            "description": "Test case where the class has only static fields, should return a map excluding these fields."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "MockGsonInstance"
                },
                {
                    "parameter": "type",
                    "value": "TypeToken.get(InaccessibleFieldsClass.class)"
                },
                {
                    "parameter": "raw",
                    "value": "InaccessibleFieldsClass.class"
                },
                {
                    "parameter": "blockInaccessible",
                    "value": true
                },
                {
                    "parameter": "isRecord",
                    "value": false
                }
            ],
            "expected": "JsonIOException",
            "description": "Test case where fields are inaccessible and blockInaccessible is true, should throw JsonIOException."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "MockGsonInstance"
                },
                {
                    "parameter": "type",
                    "value": "TypeToken.get(AnnotatedFieldClass.class)"
                },
                {
                    "parameter": "raw",
                    "value": "AnnotatedFieldClass.class"
                },
                {
                    "parameter": "blockInaccessible",
                    "value": false
                },
                {
                    "parameter": "isRecord",
                    "value": false
                }
            ],
            "expected": "A map with bound fields including annotated fields",
            "description": "Test case where fields have annotations like @SerializedName, should include these fields in the map."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "MockGsonInstance"
                },
                {
                    "parameter": "type",
                    "value": "TypeToken.get(SuperClass.class)"
                },
                {
                    "parameter": "raw",
                    "value": "SuperClass.class"
                },
                {
                    "parameter": "blockInaccessible",
                    "value": false
                },
                {
                    "parameter": "isRecord",
                    "value": false
                }
            ],
            "expected": "A map with bound fields from superclass",
            "description": "Test case where the class has inherited fields from a superclass, should include these fields in the map."
        }
    ]
}