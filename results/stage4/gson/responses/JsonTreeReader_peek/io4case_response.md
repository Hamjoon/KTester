{
    "testCases": [
        {
            "group": "peek method - stack contains multiple elements",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray with JsonPrimitive and JsonObject"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test when the top of the stack is a JsonArray containing JsonPrimitive and JsonObject, should return BEGIN_ARRAY"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonObject with JsonPrimitive"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test when the top of the stack is a JsonObject containing JsonPrimitive, should return BEGIN_OBJECT"
                }
            ]
        },
        {
            "group": "peek method - stack contains empty collections",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "empty JsonArray"
                        }
                    ],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test when the top of the stack is an empty JsonArray, should return END_ARRAY"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "empty JsonObject"
                        }
                    ],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test when the top of the stack is an empty JsonObject, should return END_OBJECT"
                }
            ]
        },
        {
            "group": "peek method - stack contains mixed types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray with JsonNull and JsonPrimitive"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test when the top of the stack is a JsonArray containing JsonNull and JsonPrimitive, should return BEGIN_ARRAY"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonObject with JsonNull"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test when the top of the stack is a JsonObject containing JsonNull, should return BEGIN_OBJECT"
                }
            ]
        },
        {
            "group": "peek method - stack contains various JsonPrimitive types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with character"
                        }
                    ],
                    "expected": "AssertionError",
                    "description": "Test when the top of the stack is a JsonPrimitive with a character type, should throw AssertionError"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with empty string"
                        }
                    ],
                    "expected": "JsonToken.STRING",
                    "description": "Test when the top of the stack is a JsonPrimitive with an empty string, should return STRING"
                }
            ]
        }
    ]
}