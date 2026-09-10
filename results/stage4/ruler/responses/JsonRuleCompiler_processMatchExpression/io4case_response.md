{
    "test cases": [
        {
            "group": "processMatchExpression - Anything But Match - Ignore Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for ANYTHING_BUT_MATCH followed by START_OBJECT token and valid string for equals-ignore-case"
                        }
                    ],
                    "expected": "Patterns object representing anything but equals-ignore-case match",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH and valid string is provided for equals-ignore-case."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for ANYTHING_BUT_MATCH followed by START_OBJECT token and non-START_ARRAY token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Value of ANYTHING_BUT_MATCH must be an array or single string/number value.'",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH but the provided value is neither an array nor a valid string."
                }
            ]
        },
        {
            "group": "processMatchExpression - Numeric Match - Boundary Conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by START_ARRAY token containing empty array"
                        }
                    ],
                    "expected": "Patterns object representing numeric match with empty array",
                    "description": "Test for numeric match scenario where the match type is NUMERIC and an empty array is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by START_ARRAY token containing a single number"
                        }
                    ],
                    "expected": "Patterns object representing numeric match with single number",
                    "description": "Test for numeric match scenario where the match type is NUMERIC and a single number is provided in the array."
                }
            ]
        },
        {
            "group": "processMatchExpression - CIDR Match - Boundary Conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for CIDR followed by VALUE_STRING token containing invalid CIDR format"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match scenario where the match type is CIDR but the provided value is in an invalid CIDR format."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token for CIDR followed by VALUE_STRING token containing valid CIDR format"
                        }
                    ],
                    "expected": "Range object representing CIDR match",
                    "description": "Test for CIDR match scenario where the match type is CIDR and a valid CIDR format is provided."
                }
            ]
        }
    ]
}