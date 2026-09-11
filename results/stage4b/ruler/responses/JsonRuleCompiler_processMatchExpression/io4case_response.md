{
    "testCases": [
        {
            "group": "processMatchExpression - Anything But Match (Ignore Case)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and valid string for equals-ignore-case"
                        }
                    ],
                    "expected": "Patterns for anything-but equals-ignore-case match",
                    "description": "Test case for anything-but match type with valid string for equals-ignore-case."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and invalid string for equals-ignore-case"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything-but match type with invalid string for equals-ignore-case, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Numeric Match (Invalid Array)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'numeric' and empty array"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for numeric match type with an empty array, expecting a JsonParseException."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'numeric' and array with invalid types"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for numeric match type where the array contains invalid types, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - CIDR Match (Invalid CIDR)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'cidr' and invalid CIDR string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for CIDR match type with invalid CIDR string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Wildcard Match (Empty String)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'wildcard' and empty string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for wildcard match type with an empty string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Unrecognized Match Type (Invalid Name)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'invalid_type_name'"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for unrecognized match type with an invalid type name, expecting a JsonParseException."
                }
            ]
        }
    ]
}