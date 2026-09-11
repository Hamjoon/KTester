{
    "output": [
        {
            "group": "processMatchExpression - Anything But Match (Invalid Prefix/Suffix)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and value string is empty"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything-but match type where the prefix/suffix is an empty string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match (Unsupported Pattern)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and unsupported operator"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything-but match type with unsupported pattern, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match (Invalid Array)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and value as an empty array"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything-but match type with an empty array, expecting a JsonParseException."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and array with invalid types"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything-but match type where the array contains invalid types, expecting a JsonParseException."
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
                            "value": "Mocked JsonParser with field name 'numeric' and value as an array with less than 2 elements"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for numeric match type with an array containing less than two elements, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Exists Match (Invalid Input)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'exists_match' and invalid structure"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for exists match type with invalid input structure, expecting a JsonParseException."
                }
            ]
        }
    ]
}