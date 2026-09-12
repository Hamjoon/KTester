{
    "group": "JsonTreeReader.peek() Tests",
    "cases": [
        {
            "input": [],
            "expected": "JsonToken.END_DOCUMENT",
            "description": "Test when stack is empty. Expect END_DOCUMENT as there are no elements to process."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonObject()]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.BEGIN_OBJECT",
            "description": "Test when the stack contains a JsonObject. Expect BEGIN_OBJECT token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray()]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.BEGIN_ARRAY",
            "description": "Test when the stack contains a JsonArray. Expect BEGIN_ARRAY token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonPrimitive(\"string\")]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.STRING",
            "description": "Test when the stack contains a JsonPrimitive string. Expect STRING token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonPrimitive(true)]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.BOOLEAN",
            "description": "Test when the stack contains a JsonPrimitive boolean. Expect BOOLEAN token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonPrimitive(123)]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.NUMBER",
            "description": "Test when the stack contains a JsonPrimitive number. Expect NUMBER token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonNull.INSTANCE]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "JsonToken.NULL",
            "description": "Test when the stack contains a JsonNull. Expect NULL token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[SENTINEL_CLOSED]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test when the stack contains SENTINEL_CLOSED. Expect IllegalStateException indicating the reader is closed."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new Object()]"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "MalformedJsonException",
            "description": "Test when the stack contains an unsupported object type. Expect MalformedJsonException."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonObject(), new Iterator<Object>()]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.NAME",
            "description": "Test when the stack contains a JsonObject followed by an Iterator with elements. Expect NAME token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray(), new Iterator<Object>()]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.END_ARRAY",
            "description": "Test when the stack contains a JsonArray followed by an empty Iterator. Expect END_ARRAY token."
        }
    ]
}