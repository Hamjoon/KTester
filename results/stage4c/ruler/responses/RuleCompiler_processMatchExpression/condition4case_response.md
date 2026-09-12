{
    "group": "processMatchExpression Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EXACT_MATCH field"
                }
            ],
            "expected": "Patterns object with exact match",
            "description": "Test for exact match expression where the match type is EXACT_MATCH and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and PREFIX_MATCH field"
                }
            ],
            "expected": "Patterns object with prefix match",
            "description": "Test for prefix match expression where the match type is PREFIX_MATCH and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and SUFFIX_MATCH field"
                }
            ],
            "expected": "Patterns object with suffix match",
            "description": "Test for suffix match expression where the match type is SUFFIX_MATCH and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and NUMERIC field"
                }
            ],
            "expected": "Patterns object with numeric match",
            "description": "Test for numeric match expression where the match type is NUMERIC and the value is a valid array."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and ANYTHING_BUT_MATCH field"
                }
            ],
            "expected": "Patterns object with anything-but match",
            "description": "Test for anything-but match expression where the match type is ANYTHING_BUT_MATCH and the value is a valid string/array/number."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EXISTS_MATCH field"
                }
            ],
            "expected": "Patterns object with exists match",
            "description": "Test for exists match expression where the match type is EXISTS_MATCH."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and CIDR field"
                }
            ],
            "expected": "Range object with CIDR match",
            "description": "Test for CIDR match expression where the match type is CIDR and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EQUALS_IGNORE_CASE field"
                }
            ],
            "expected": "Patterns object with equals-ignore-case match",
            "description": "Test for equals-ignore-case match expression where the match type is EQUALS_IGNORE_CASE and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and WILDCARD field"
                }
            ],
            "expected": "Patterns object with wildcard match",
            "description": "Test for wildcard match expression where the match type is WILDCARD and the value is a valid string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and unrecognized field"
                }
            ],
            "expected": "JsonParseException with message 'Unrecognized match type'",
            "description": "Test for unrecognized match type where the match type is not one of the expected types."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser without FIELD_NAME token"
                }
            ],
            "expected": "JsonParseException with message 'Match expression name not found'",
            "description": "Test for missing match expression name where the token is not FIELD_NAME."
        }
    ]
}