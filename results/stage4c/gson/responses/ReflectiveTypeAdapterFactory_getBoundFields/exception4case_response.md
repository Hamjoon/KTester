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
                    "value": "TypeToken.get(RecordClassWithSerializedName.class)"
                },
                {
                    "parameter": "raw",
                    "value": "RecordClassWithSerializedName.class"
                },
                {
                    "parameter": "blockInaccessible",
                    "value": false
                },
                {
                    "parameter": "isRecord",
                    "value": true
                }
            ],
            "expected": "JsonIOException",
            "description": "Test case where a record class has a @SerializedName annotation on an accessor method without corresponding field annotation, should throw JsonIOException."
        }
    ]
}