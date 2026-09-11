{
    "test cases": [
        {
            "group": "peek method - iterator with no next element in JsonArray",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray with an iterator that has no next"
                        }
                    ],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test peek when the top of the stack is an iterator of a JsonArray that has no next element, expecting END_ARRAY token."
                }
            ]
        },
        {
            "group": "peek method - iterator with next element in JsonArray",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray with an iterator that has next"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test peek when the top of the stack is an iterator of a JsonArray that has a next element, expecting BEGIN_ARRAY token."
                }
            ]
        },
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
        },
        {
            "group": "peek method - multiple types in stack",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonObject followed by JsonArray"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test peek when the stack contains a JsonObject followed by a JsonArray, expecting BEGIN_ARRAY token."
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray followed by JsonPrimitive"
                        }
                    ],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test peek when the top of the stack is a JsonArray followed by a JsonPrimitive representing a number, expecting NUMBER token."
                }
            ]
        }
    ]
}