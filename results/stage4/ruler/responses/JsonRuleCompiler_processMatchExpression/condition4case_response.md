{
    "testCases": [
        {
            "group": "processMatchExpression - Exact Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Patterns object representing exact match",
                    "description": "Test for exact match scenario where the match type is EXACT_MATCH and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'exact match pattern must be a string'",
                    "description": "Test for exact match scenario where the match type is EXACT_MATCH but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Patterns object representing prefix match",
                    "description": "Test for prefix match scenario where the match type is PREFIX_MATCH and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for prefix match scenario where the match type is PREFIX_MATCH but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Patterns object representing suffix match",
                    "description": "Test for suffix match scenario where the match type is SUFFIX_MATCH and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'suffix match pattern must be a string'",
                    "description": "Test for suffix match scenario where the match type is SUFFIX_MATCH but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token followed by START_ARRAY token"
                        }
                    ],
                    "expected": "Patterns object representing numeric match",
                    "description": "Test for numeric match scenario where the match type is NUMERIC and a valid array is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-START_ARRAY token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Value of NUMERIC must be an array.'",
                    "description": "Test for numeric match scenario where the match type is NUMERIC but the provided value is not an array."
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
                            "value": "JsonParser with FIELD_NAME token followed by START_OBJECT token and valid prefix/suffix"
                        }
                    ],
                    "expected": "Patterns object representing anything but prefix/suffix match",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH and valid prefix/suffix is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by invalid token types"
                        }
                    ],
                    "expected": "JsonParseException with message 'Value of ANYTHING_BUT_MATCH must be an array or single string/number value.'",
                    "description": "Test for anything but match scenario where the match type is ANYTHING_BUT_MATCH but the provided value is invalid."
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
                            "value": "JsonParser with FIELD_NAME token for EXISTS_MATCH"
                        }
                    ],
                    "expected": "Patterns object representing exists match",
                    "description": "Test for exists match scenario where the match type is EXISTS_MATCH."
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
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Range object representing CIDR match",
                    "description": "Test for CIDR match scenario where the match type is CIDR and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match scenario where the match type is CIDR but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Patterns object representing equals ignore case match",
                    "description": "Test for equals ignore case match scenario where the match type is EQUALS_IGNORE_CASE and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'equals-ignore-case match pattern must be a string'",
                    "description": "Test for equals ignore case match scenario where the match type is EQUALS_IGNORE_CASE but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token followed by VALUE_STRING token"
                        }
                    ],
                    "expected": "Patterns object representing wildcard match",
                    "description": "Test for wildcard match scenario where the match type is WILDCARD and a valid string is provided."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token followed by non-VALUE_STRING token"
                        }
                    ],
                    "expected": "JsonParseException with message 'wildcard match pattern must be a string'",
                    "description": "Test for wildcard match scenario where the match type is WILDCARD but the provided value is not a string."
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
                            "value": "JsonParser with FIELD_NAME token with unrecognized match type"
                        }
                    ],
                    "expected": "JsonParseException with message 'Unrecognized match type'",
                    "description": "Test for unrecognized match type scenario to ensure proper handling of invalid match types."
                }
            ]
        }
    ]
}