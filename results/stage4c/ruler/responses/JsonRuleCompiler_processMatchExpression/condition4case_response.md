{
    "group": "processMatchExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'exactMatch' and VALUE_STRING"
                }
            ],
            "expected": "Patterns.exactMatch",
            "description": "Test exact match expression with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'exactMatch' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test exact match expression with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'prefixMatch' and VALUE_STRING"
                }
            ],
            "expected": "Patterns.prefixMatch",
            "description": "Test prefix match expression with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'prefixMatch' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test prefix match expression with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'suffixMatch' and VALUE_STRING"
                }
            ],
            "expected": "Patterns.suffixMatch",
            "description": "Test suffix match expression with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'suffixMatch' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test suffix match expression with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'numeric' and START_ARRAY"
                }
            ],
            "expected": "Patterns from processNumericMatchExpression",
            "description": "Test numeric match expression with valid array pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'numeric' and non-START_ARRAY"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test numeric match expression with invalid non-array pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_OBJECT"
                }
            ],
            "expected": "Patterns.anythingButPrefix or Patterns.anythingButSuffix",
            "description": "Test anything-but match with prefix or suffix in object."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'anythingButMatch' as START_ARRAY"
                }
            ],
            "expected": "Patterns from processAnythingButListMatchExpression",
            "description": "Test anything-but match with valid array pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'existsMatch'"
                }
            ],
            "expected": "Patterns from processExistsExpression",
            "description": "Test exists match expression."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'cidr' and VALUE_STRING"
                }
            ],
            "expected": "Range from CIDR.cidr",
            "description": "Test CIDR match with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'cidr' and non-VALUE_STRING"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test CIDR match with invalid non-string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'equalsIgnoreCase' and VALUE_STRING"
                }
            ],
            "expected": "Patterns.equalsIgnoreCaseMatch",
            "description": "Test equals-ignore-case match with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME 'wildcard' and VALUE_STRING"
                }
            ],
            "expected": "Patterns.wildcardMatch",
            "description": "Test wildcard match with valid string pattern."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with unrecognized FIELD_NAME"
                }
            ],
            "expected": "JsonParseException",
            "description": "Test unrecognized match type."
        }
    ]
}