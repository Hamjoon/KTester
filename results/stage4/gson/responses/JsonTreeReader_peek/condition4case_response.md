{
    "testCases": [
        {
            "group": "peek method - stackSize == 0",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.END_DOCUMENT",
                    "description": "Test when stackSize is 0, should return END_DOCUMENT"
                }
            ]
        },
        {
            "group": "peek method - stack contains Iterator",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "Iterator with next element"
                        }
                    ],
                    "expected": "JsonToken.NAME",
                    "description": "Test when the top of the stack is an Iterator and isObject is true, should return NAME"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "Iterator without next element"
                        }
                    ],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test when the top of the stack is an Iterator and isObject is true but has no next element, should return END_OBJECT"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "Iterator with next element, isObject false"
                        }
                    ],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test when the top of the stack is an Iterator and isObject is false, should return END_ARRAY"
                }
            ]
        },
        {
            "group": "peek method - stack contains JsonObject",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonObject"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test when the top of the stack is a JsonObject, should return BEGIN_OBJECT"
                }
            ]
        },
        {
            "group": "peek method - stack contains JsonArray",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonArray"
                        }
                    ],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test when the top of the stack is a JsonArray, should return BEGIN_ARRAY"
                }
            ]
        },
        {
            "group": "peek method - stack contains JsonPrimitive",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with string"
                        }
                    ],
                    "expected": "JsonToken.STRING",
                    "description": "Test when the top of the stack is a JsonPrimitive that is a string, should return STRING"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with boolean"
                        }
                    ],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test when the top of the stack is a JsonPrimitive that is a boolean, should return BOOLEAN"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with number"
                        }
                    ],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test when the top of the stack is a JsonPrimitive that is a number, should return NUMBER"
                },
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonPrimitive with unsupported type"
                        }
                    ],
                    "expected": "AssertionError",
                    "description": "Test when the top of the stack is a JsonPrimitive with an unsupported type, should throw AssertionError"
                }
            ]
        },
        {
            "group": "peek method - stack contains JsonNull",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "JsonNull"
                        }
                    ],
                    "expected": "JsonToken.NULL",
                    "description": "Test when the top of the stack is JsonNull, should return NULL"
                }
            ]
        },
        {
            "group": "peek method - stack contains SENTINEL_CLOSED",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "SENTINEL_CLOSED"
                        }
                    ],
                    "expected": "IllegalStateException with message 'JsonReader is closed'",
                    "description": "Test when the top of the stack is SENTINEL_CLOSED, should throw IllegalStateException"
                }
            ]
        },
        {
            "group": "peek method - stack contains unsupported type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "stack",
                            "value": "UnsupportedType"
                        }
                    ],
                    "expected": "MalformedJsonException with appropriate message",
                    "description": "Test when the top of the stack is an unsupported type, should throw MalformedJsonException"
                }
            ]
        }
    ]
}