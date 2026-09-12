{
    "group": "processMatchExpression Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EXACT_MATCH field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'exact match pattern must be a string'",
            "description": "Test for exact match expression where the match type is EXACT_MATCH but the value is not a string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and PREFIX_MATCH field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'prefix match pattern must be a string'",
            "description": "Test for prefix match expression where the match type is PREFIX_MATCH but the value is not a string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and SUFFIX_MATCH field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'suffix match pattern must be a string'",
            "description": "Test for suffix match expression where the match type is SUFFIX_MATCH but the value is not a string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and NUMERIC field, but non-array value"
                }
            ],
            "expected": "JsonParseException with message 'Value of NUMERIC must be an array.'",
            "description": "Test for numeric match expression where the match type is NUMERIC but the value is not an array."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and ANYTHING_BUT_MATCH field, but unsupported pattern"
                }
            ],
            "expected": "JsonParseException with message 'Unsupported anything-but pattern: unsupportedPattern'",
            "description": "Test for anything-but match expression where the match type is ANYTHING_BUT_MATCH but the pattern is unsupported."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and ANYTHING_BUT_MATCH field, but non-string/non-array/non-number value"
                }
            ],
            "expected": "JsonParseException with message 'Value of ANYTHING_BUT_MATCH must be an array or single string/number value.'",
            "description": "Test for anything-but match expression where the match type is ANYTHING_BUT_MATCH but the value is not a string, array, or number."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and CIDR field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'prefix match pattern must be a string'",
            "description": "Test for CIDR match expression where the match type is CIDR but the value is not a string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EQUALS_IGNORE_CASE field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'equals-ignore-case match pattern must be a string'",
            "description": "Test for equals-ignore-case match expression where the match type is EQUALS_IGNORE_CASE but the value is not a string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and WILDCARD field, but non-string value"
                }
            ],
            "expected": "JsonParseException with message 'wildcard match pattern must be a string'",
            "description": "Test for wildcard match expression where the match type is WILDCARD but the value is not a string."
        }
    ]
}