{
    "group": "getBoundFields - Accessor Method with SerializedName",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "new Gson()"
                },
                {
                    "parameter": "type",
                    "value": "TypeToken.get(ClassWithAccessorAndSerializedName.class)"
                },
                {
                    "parameter": "raw",
                    "value": "ClassWithAccessorAndSerializedName.class"
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
                "message": "SerializedName on accessorMethod is not supported"
            },
            "description": "Test for a class where the accessor method has a SerializedName annotation but the field does not, should throw JsonIOException."
        }
    ]
}