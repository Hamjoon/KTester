{
    "group": "processMatchExpression Additional Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and ANYTHING_BUT_MATCH field, with empty string pattern"
                }
            ],
            "expected": "JsonParseException with message 'Null prefix/suffix not allowed'",
            "description": "Test for anything-but match expression where the match type is ANYTHING_BUT_MATCH and the pattern is an empty string."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and ANYTHING_BUT_MATCH field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for anything-but match expression where the match type is ANYTHING_BUT_MATCH and there are multiple keys."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and PREFIX_MATCH field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for prefix match expression where the match type is PREFIX_MATCH and there are multiple keys."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and SUFFIX_MATCH field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for suffix match expression where the match type is SUFFIX_MATCH and there are multiple keys."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and EQUALS_IGNORE_CASE field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for equals-ignore-case match expression where the match type is EQUALS_IGNORE_CASE and there are multiple keys."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and WILDCARD field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for wildcard match expression where the match type is WILDCARD and there are multiple keys."
        },
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "JsonParser with FIELD_NAME token and CIDR field, with multiple keys"
                }
            ],
            "expected": "JsonParseException with message 'Only one key allowed in match expression'",
            "description": "Test for CIDR match expression where the match type is CIDR and there are multiple keys."
        }
    ]
}