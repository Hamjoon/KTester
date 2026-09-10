{
    "group": "writeRules with exceptions",
    "cases": [
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[]"
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mocked JsonParser that returns END_ARRAY token"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
            "description": "Test writeRules with an empty rules list to ensure it throws an exception."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[{}]"
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mocked JsonParser that returns an unexpected token"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test writeRules with an unexpected token to ensure it throws the appropriate exception."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[{}]"
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mocked JsonParser that returns VALUE_NUMBER_FLOAT token with a non-representable number"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "no exception thrown, but adds exact match for the number",
            "description": "Test writeRules with a VALUE_NUMBER_FLOAT token that cannot be represented as a ComparableNumber to ensure it handles the case without throwing an exception."
        }
    ]
}