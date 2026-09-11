{
    "testCases": [
        {
            "group": "processMatchExpression - Exact Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'exact_match' and value 'test'"
                        }
                    ],
                    "expected": "Patterns.exactMatch('\"test\"')",
                    "description": "Test case for exact match type where the parser returns the correct string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'exact_match' and value not a string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for exact match type where the value is not a string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Prefix Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'prefix_match' and value 'test'"
                        }
                    ],
                    "expected": "Patterns.prefixMatch('\"test\"')",
                    "description": "Test case for prefix match type where the parser returns the correct string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'prefix_match' and value not a string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for prefix match type where the value is not a string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Suffix Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'suffix_match' and value 'test'"
                        }
                    ],
                    "expected": "Patterns.suffixMatch('test\"')",
                    "description": "Test case for suffix match type where the parser returns the correct string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'suffix_match' and value not a string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for suffix match type where the value is not a string, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Numeric Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'numeric' and value as array"
                        }
                    ],
                    "expected": "Patterns for numeric match",
                    "description": "Test case for numeric match type with valid array input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'numeric' and value not an array"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for numeric match type where the value is not an array, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and valid prefix/suffix"
                        }
                    ],
                    "expected": "Patterns.anythingButPrefix or Patterns.anythingButSuffix",
                    "description": "Test case for anything but match type with valid prefix/suffix."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'anything_but_match' and invalid operator"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for anything but match type with unsupported operator, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Exists Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'exists_match'"
                        }
                    ],
                    "expected": "Patterns for exists match",
                    "description": "Test case for exists match type where the parser processes the existence check."
                }
            ]
        },
        {
            "group": "processMatchExpression - CIDR Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'cidr' and valid string"
                        }
                    ],
                    "expected": "Range for CIDR match",
                    "description": "Test case for CIDR match type with valid input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'cidr' and invalid string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for CIDR match type with invalid input, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Equals Ignore Case Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'equals_ignore_case' and valid string"
                        }
                    ],
                    "expected": "Patterns.equalsIgnoreCaseMatch('\"test\"')",
                    "description": "Test case for equals-ignore-case match type with valid input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'equals_ignore_case' and invalid string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for equals-ignore-case match type with invalid input, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Wildcard Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'wildcard' and valid string"
                        }
                    ],
                    "expected": "Patterns.wildcardMatch('\"test\"')",
                    "description": "Test case for wildcard match type with valid input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'wildcard' and invalid string"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for wildcard match type with invalid input, expecting a JsonParseException."
                }
            ]
        },
        {
            "group": "processMatchExpression - Unrecognized Match Type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "Mocked JsonParser with field name 'unrecognized_match_type'"
                        }
                    ],
                    "expected": "JsonParseException",
                    "description": "Test case for unrecognized match type, expecting a JsonParseException."
                }
            ]
        }
    ]
}