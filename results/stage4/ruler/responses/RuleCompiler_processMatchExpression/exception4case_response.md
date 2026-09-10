{
    "output": [
        {
            "group": "processMatchExpression - Exists Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EXISTS_MATCH' followed by an invalid structure"
                        }
                    ],
                    "expected": "IOException with message indicating invalid exists expression",
                    "description": "Test for exists match pattern processing with invalid input structure."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match (Invalid Type)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by a START_OBJECT token containing an unsupported type"
                        }
                    ],
                    "expected": "JsonParseException with message 'Unsupported anything-but pattern: '",
                    "description": "Test for anything-but match pattern processing with unsupported pattern type."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match (Null Value)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by a START_OBJECT token containing 'PREFIX_MATCH' and a null string"
                        }
                    ],
                    "expected": "JsonParseException with message 'Null prefix/suffix not allowed'",
                    "description": "Test for anything-but match pattern processing with null prefix/suffix value."
                }
            ]
        },
        {
            "group": "processMatchExpression - CIDR Match (Invalid Format)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'CIDR' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match pattern processing with non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'CIDR' followed by a string token that is not a valid CIDR"
                        }
                    ],
                    "expected": "IOException with message indicating invalid CIDR format",
                    "description": "Test for CIDR match pattern processing with invalid CIDR format."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match (Too Many Keys)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by a START_OBJECT token containing multiple keys"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for anything-but match pattern processing with too many keys in the object."
                }
            ]
        }
    ]
}