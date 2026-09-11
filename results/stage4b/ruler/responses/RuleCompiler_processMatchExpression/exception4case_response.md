{
    "group": "processMatchExpression - Anything But Match - Null Prefix/Suffix",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"PREFIX_MATCH\": \"\" } }"
                }
            ],
            "expected": "IOException with message 'Null prefix/suffix not allowed'",
            "description": "Test for anything-but match with an empty string as prefix."
        }
    ]
    ,
    "group": "processMatchExpression - Anything But Match - Invalid Value Type",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"PREFIX_MATCH\": [1, 2, 3] } }"
                }
            ],
            "expected": "IOException with message 'prefix/suffix match pattern must be a string'",
            "description": "Test for anything-but match with an invalid array value for prefix."
        }
    ]
    ,
    "group": "processMatchExpression - Anything But Match - Unsupported Pattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"INVALID_PATTERN\": \"test\" } }"
                }
            ],
            "expected": "IOException with message 'Unsupported anything-but pattern: INVALID_PATTERN'",
            "description": "Test for anything-but match with an unsupported pattern type."
        }
    ]
    ,
    "group": "processMatchExpression - Anything But Match - Too Many Elements",
    "cases": [
        {
            "input": [
                {
                    "parameter": "parser",
                    "value": "{ \"matchType\": \"ANYTHING_BUT_MATCH\", \"value\": { \"PREFIX_MATCH\": \"test\" }, \"extraField\": \"extraValue\" }"
                }
            ],
            "expected": "IOException with message 'Only one key allowed in match expression'",
            "description": "Test for anything-but match with extra fields in the JSON object."
        }
    ]
}