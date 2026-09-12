{
    "group": "JsonTreeReader.peek() Additional Tests",
    "cases": [
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
            "expected": "JsonToken.END_OBJECT",
            "description": "Test when the stack contains a JsonObject followed by an empty Iterator. Expect END_OBJECT token."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray(), new Iterator<Object>(Arrays.asList(new JsonPrimitive(\"item\")).iterator())]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.STRING",
            "description": "Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive string element. Expect STRING token after pushing the element."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray(), new Iterator<Object>(Arrays.asList(new JsonPrimitive(42)).iterator())]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.NUMBER",
            "description": "Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive number element. Expect NUMBER token after pushing the element."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray(), new Iterator<Object>(Arrays.asList(new JsonPrimitive(true)).iterator())]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.BOOLEAN",
            "description": "Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive boolean element. Expect BOOLEAN token after pushing the element."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[new JsonArray(), new Iterator<Object>(Arrays.asList(JsonNull.INSTANCE).iterator())]"
                },
                {
                    "parameter": "stackSize",
                    "value": "2"
                }
            ],
            "expected": "JsonToken.NULL",
            "description": "Test when the stack contains a JsonArray followed by an Iterator with a JsonNull element. Expect NULL token after pushing the element."
        }
    ]
}