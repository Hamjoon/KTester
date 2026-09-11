{
    "result": [
        {
            "group": "peek method - JsonPrimitive with unsupported type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with unsupported type"
                        }
                    ],
                    "expected": "MalformedJsonException with appropriate message",
                    "description": "Test peek when the top of the stack is a JsonPrimitive of an unsupported type, expecting a MalformedJsonException."
                }
            ]
        }
    ]
}