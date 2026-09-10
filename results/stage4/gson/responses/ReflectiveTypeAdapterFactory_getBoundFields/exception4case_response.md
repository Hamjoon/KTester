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
                    "value": "TypeToken.get(MyClassWithFieldHavingSerializedName.class)"
                },
                {
                    "parameter": "raw",
                    "value": "MyClassWithFieldHavingSerializedName.class"
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
            "expected": "A JsonIOException indicating @SerializedName on field is not supported",
            "description": "Test case to verify that getBoundFields throws JsonIOException when a field has a @SerializedName annotation but its accessor method does not."
        },
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
                    "value": "true"
                },
                {
                    "parameter": "isRecord",
                    "value": "false"
                }
            ],
            "expected": "A map excluding private fields when blockInaccessible is true",
            "description": "Test case to verify that getBoundFields excludes private fields when blockInaccessible is true."
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
        }
    ]
}