{
    "testCases": [
        {
            "group": "processMatchExpression - Anything But Match - Ignore Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"EQUALS_IGNORE_CASE\": \"test\" } }"
                        }
                    ],
                    "expected": "Patterns from processAnythingButEqualsIgnoreCaseMatchExpression",
                    "description": "Test for anything-but match with valid ignore case pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"EQUALS_IGNORE_CASE\": 123 } }"
                        }
                    ],
                    "expected": "IOException with message 'equals-ignore-case match pattern must be a string'",
                    "description": "Test for anything-but match with an invalid numeric value for ignore case."
                }
            ]
        },
        {
            "group": "processMatchExpression - Anything But Match - Invalid Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"INVALID_TYPE\": \"test\" } }"
                        }
                    ],
                    "expected": "IOException with message 'Unsupported anything-but pattern: INVALID_TYPE'",
                    "description": "Test for anything-but match with an unsupported pattern type."
                }
            ]
        },
        {
            "group": "processMatchExpression - CIDR Match - Invalid CIDR",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"CIDR\", \"value\": \"192.168.0.256/24\" }"
                        }
                    ],
                    "expected": "IOException with message 'prefix match pattern must be a string'",
                    "description": "Test for CIDR match with an invalid CIDR notation (out of range IP)."
                }
            ]
        },
        {
            "group": "processMatchExpression - Numeric Match - Empty Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"NUMERIC\", \"value\": [] }"
                        }
                    ],
                    "expected": "IOException with message 'Value of NUMERIC must be an array.'",
                    "description": "Test for numeric match with an empty array."
                }
            ]
        },
        {
            "group": "processMatchExpression - Wildcard Match - Empty String",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"WILDCARD\", \"value\": \"\" }"
                        }
                    ],
                    "expected": "IOException with message 'wildcard match pattern must be a string'",
                    "description": "Test for wildcard match with an empty string."
                }
            ]
        },
        {
            "group": "processMatchExpression - Prefix Match - Trailing Whitespace",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"PREFIX_MATCH\", \"value\": \"test \" }"
                        }
                    ],
                    "expected": "Patterns.prefixMatch(\"test \")",
                    "description": "Test for prefix match with a valid string value containing trailing whitespace."
                }
            ]
        },
        {
            "group": "processMatchExpression - Suffix Match - Leading Whitespace",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "parser",
                            "value": "{ \"matchType\": \"SUFFIX_MATCH\", \"value\": \" test\" }"
                        }
                    ],
                    "expected": "Patterns.suffixMatch(\" test\")",
                    "description": "Test for suffix match with a valid string value containing leading whitespace."
                }
            ]
        }
    ]
}