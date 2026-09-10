{
    "result": [
        {
            "group": "processMatchExpression - Anything But Match - Missing Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for ANYTHING_BUT_MATCH followed by START_OBJECT token but missing the expected key"
                        }
                    ],
                    "expected": "JsonParseException with message 'Anything-But expression name not found'",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH but the expected key is missing."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match - Null Prefix/Suffix",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for ANYTHING_BUT_MATCH followed by START_OBJECT token and a null prefix/suffix"
                        }
                    ],
                    "expected": "JsonParseException with message 'Null prefix/suffix not allowed'",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH but the provided prefix/suffix is null."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match - Too Many Elements",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for ANYTHING_BUT_MATCH followed by valid prefix/suffix and extra elements"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH but there are too many elements present."
                }
            ]
        },
        {
            "group": "processMatchExpression - Numeric Match - Too Many Elements",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for NUMERIC followed by START_ARRAY token and extra elements"
                        }
                    ],
                    "expected": "JsonParseException with message 'Too many elements in numeric match expression'",
                    "description": "Test for numeric match scenario where the match type is NUMERIC but there are too many elements in the array."
                }
            ]
        }
    ]
}