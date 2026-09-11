{
    "testCases": [
        {
            "group": "processMatchExpression - Exact Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"EXACT_MATCH\", \"value\": \"test\" }"
                        }
                    ],
                    "expected": "Patterns.exactMatch(\"\"test\"\")",
                    "description": "Test for exact match with a valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"EXACT_MATCH\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'exact match pattern must be a string'",
                    "description": "Test for exact match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"PREFIX_MATCH\", \"value\": \"test\" }"
                        }
                    ],
                    "expected": "Patterns.prefixMatch(\"test\")",
                    "description": "Test for prefix match with a valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"PREFIX_MATCH\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'prefix match pattern must be a string'",
                    "description": "Test for prefix match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"SUFFIX_MATCH\", \"value\": \"test\" }"
                        }
                    ],
                    "expected": "Patterns.suffixMatch(\"test\")",
                    "description": "Test for suffix match with a valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"SUFFIX_MATCH\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'suffix match pattern must be a string'",
                    "description": "Test for suffix match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"NUMERIC\", \"value\": [1, 2, 3] }"
                        }
                    ],
                    "expected": "Patterns range from processNumericMatchExpression",
                    "description": "Test for numeric match with a valid array of numbers."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"NUMERIC\", \"value\": \"not an array\" }"
                        }
                    ],
                    "expected": "IOException with message 'Value of NUMERIC must be an array.'",
                    "description": "Test for numeric match with an invalid non-array value."
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
                            "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"PREFIX_MATCH\": \"test\" } }"
                        }
                    ],
                    "expected": "Patterns.anythingButPrefix(\"test\")",
                    "description": "Test for anything-but match with a valid prefix match."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'prefix/suffix match pattern must be a string'",
                    "description": "Test for anything-but match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"EXISTS_MATCH\" }"
                        }
                    ],
                    "expected": "Patterns from processExistsExpression",
                    "description": "Test for exists match with valid input."
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
                            "value": "{ \"matchType\": \"CIDR\", \"value\": \"192.168.0.0/24\" }"
                        }
                    ],
                    "expected": "Range from CIDR.cidr(\"192.168.0.0/24\")",
                    "description": "Test for CIDR match with a valid CIDR notation."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"CIDR\", \"value\": \"invalidCIDR\" }"
                        }
                    ],
                    "expected": "IOException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match with an invalid CIDR notation."
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
                            "value": "{ \"matchType\": \"EQUALS_IGNORE_CASE\", \"value\": \"test\" }"
                        }
                    ],
                    "expected": "Patterns.equalsIgnoreCaseMatch(\"\"test\"\")",
                    "description": "Test for equals ignore case match with a valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"EQUALS_IGNORE_CASE\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'equals-ignore-case match pattern must be a string'",
                    "description": "Test for equals ignore case match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"WILDCARD\", \"value\": \"*test*\" }"
                        }
                    ],
                    "expected": "Patterns.wildcardMatch(\"\"*test*\"\")",
                    "description": "Test for wildcard match with a valid string value."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"WILDCARD\", \"value\": 123 }"
                        }
                    ],
                    "expected": "IOException with message 'wildcard match pattern must be a string'",
                    "description": "Test for wildcard match with an invalid numeric value."
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
                            "value": "{ \"matchType\": \"UNKNOWN_MATCH_TYPE\" }"
                        }
                    ],
                    "expected": "IOException with message 'Unrecognized match type UNKNOWN_MATCH_TYPE'",
                    "description": "Test for unrecognized match type."
                }
            ]
        }
    ]
}