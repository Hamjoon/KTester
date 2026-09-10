{
    "test cases": [
        {
            "group": "processMatchExpression - Exact Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EXACT_MATCH' followed by a string token 'value'"
                        }
                    ],
                    "expected": "Patterns object representing exact match with value '\"value\"'",
                    "description": "Test for exact match pattern processing with valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EXACT_MATCH' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'exact match pattern must be a string'",
                    "description": "Test for exact match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EXACT_MATCH' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for exact match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'PREFIX_MATCH' followed by a string token 'prefixValue'"
                        }
                    ],
                    "expected": "Patterns object representing prefix match with value '\"prefixValue\"'",
                    "description": "Test for prefix match pattern processing with valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'PREFIX_MATCH' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for prefix match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'PREFIX_MATCH' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for prefix match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'SUFFIX_MATCH' followed by a string token 'suffixValue'"
                        }
                    ],
                    "expected": "Patterns object representing suffix match with value 'suffixValue\"'",
                    "description": "Test for suffix match pattern processing with valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'SUFFIX_MATCH' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'suffix match pattern must be a string'",
                    "description": "Test for suffix match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'SUFFIX_MATCH' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for suffix match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'NUMERIC' followed by a START_ARRAY token"
                        }
                    ],
                    "expected": "Patterns object representing numeric match with valid range",
                    "description": "Test for numeric match pattern processing with valid array input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'NUMERIC' followed by a non-START_ARRAY token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Value of NUMERIC must be an array.'",
                    "description": "Test for numeric match pattern processing with invalid non-array value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'NUMERIC' followed by a START_ARRAY token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for numeric match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by a START_OBJECT token"
                        }
                    ],
                    "expected": "Patterns object representing anything-but match with valid string value",
                    "description": "Test for anything-but match pattern processing with valid object structure."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by an invalid structure"
                        }
                    ],
                    "expected": "JsonParseException with message 'Value of ANYTHING_BUT_MATCH must be an array or single string/number value.'",
                    "description": "Test for anything-but match pattern processing with invalid structure."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'ANYTHING_BUT_MATCH' followed by valid structure but with too many keys"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for anything-but match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'EXISTS_MATCH'"
                        }
                    ],
                    "expected": "Patterns object representing exists match",
                    "description": "Test for exists match pattern processing with valid input."
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
                            "value": "JsonParser with FIELD_NAME token and value 'CIDR' followed by a string token '192.168.0.1'"
                        }
                    ],
                    "expected": "Range object representing CIDR match",
                    "description": "Test for CIDR match pattern processing with valid string input."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'CIDR' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'CIDR' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for CIDR match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'EQUALS_IGNORE_CASE' followed by a string token 'value'"
                        }
                    ],
                    "expected": "Patterns object representing equals ignore case match with value '\"value\"'",
                    "description": "Test for equals ignore case match pattern processing with valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EQUALS_IGNORE_CASE' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'equals-ignore-case match pattern must be a string'",
                    "description": "Test for equals ignore case match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'EQUALS_IGNORE_CASE' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for equals ignore case match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'WILDCARD' followed by a string token 'wildcardValue'"
                        }
                    ],
                    "expected": "Patterns object representing wildcard match with value '\"wildcardValue\"'",
                    "description": "Test for wildcard match pattern processing with valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'WILDCARD' followed by a non-string token"
                        }
                    ],
                    "expected": "JsonParseException with message 'wildcard match pattern must be a string'",
                    "description": "Test for wildcard match pattern processing with invalid non-string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "JsonParser with FIELD_NAME token and value 'WILDCARD' followed by a string token and an additional token"
                        }
                    ],
                    "expected": "JsonParseException with message 'Only one key allowed in match expression'",
                    "description": "Test for wildcard match pattern processing with additional tokens beyond one key."
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
                            "value": "JsonParser with FIELD_NAME token and value 'UNKNOWN_MATCH_TYPE'"
                        }
                    ],
                    "expected": "JsonParseException with message 'Unrecognized match type UNKNOWN_MATCH_TYPE'",
                    "description": "Test for unrecognized match type handling."
                }
            ]
        }
    ]
}