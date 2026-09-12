{
    "group": "processMatchExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as VALUE_STRING"
                }
            ],
            "expected": "Patterns from processAnythingButMatchExpression",
            "description": "Test anything-but match with single string value."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as VALUE_NUMBER_INT"
                }
            ],
            "expected": "Patterns from processAnythingButMatchExpression",
            "description": "Test anything-but match with single integer value."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as VALUE_NUMBER_FLOAT"
                }
            ],
            "expected": "Patterns from processAnythingButMatchExpression",
            "description": "Test anything-but match with single float value."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_OBJECT with EQUALS_IGNORE_CASE"
                }
            ],
            "expected": "Patterns from processAnythingButEqualsIgnoreCaseMatchExpression",
            "description": "Test anything-but match with equals-ignore-case in object."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_ARRAY with EQUALS_IGNORE_CASE"
                }
            ],
            "expected": "Patterns from processAnythingButEqualsIgnoreCaseListMatchExpression",
            "description": "Test anything-but match with equals-ignore-case in array."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'existsMatch' and additional key"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test exists match expression with additional key in the object."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'wildcard' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test wildcard match with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'equalsIgnoreCase' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test equals-ignore-case match with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'cidr' and additional key"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test CIDR match with additional key in the object."
        }
    ]
}