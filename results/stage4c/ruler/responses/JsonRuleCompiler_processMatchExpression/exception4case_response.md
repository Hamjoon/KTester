{
    "group": "processMatchExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_OBJECT with unsupported pattern"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test anything-but match with unsupported pattern in object."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_OBJECT with empty string pattern"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test anything-but match with empty string pattern in object."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as non-START_ARRAY, non-VALUE_STRING, non-VALUE_NUMBER_INT, non-VALUE_NUMBER_FLOAT"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test anything-but match with invalid type, neither array nor string/number."
        }
    ]
}