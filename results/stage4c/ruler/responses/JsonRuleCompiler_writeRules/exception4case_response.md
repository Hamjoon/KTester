{
    "group": "JsonRuleCompiler_writeRules",
    "cases": [
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[]"
                },
                {
                    "parameter": "name",
                    "value": "\"testName\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [VALUE_NUMBER_INT, VALUE_STRING, END_ARRAY] and value 123 and text 'invalid'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with numericEquals and exactMatch patterns for the number, and exactMatch pattern for the string",
            "description": "Test VALUE_NUMBER_INT followed by VALUE_STRING token, expecting patterns for both number and string."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[]"
                },
                {
                    "parameter": "name",
                    "value": "\"testName\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [VALUE_STRING, VALUE_NUMBER_FLOAT, END_ARRAY] and text 'string' and value 123.45"
                },
                {
                    "parameter": "withQuotes",
                    "value": "true"
                }
            ],
            "expected": "rules contain a map with quoted exactMatch pattern for the string and both numericEquals and exactMatch patterns for the float",
            "description": "Test VALUE_STRING followed by VALUE_NUMBER_FLOAT with withQuotes=true, expecting quoted exactMatch for the string and patterns for the float."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[]"
                },
                {
                    "parameter": "name",
                    "value": "\"testName\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [VALUE_NULL, VALUE_TRUE, VALUE_FALSE, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with exactMatch patterns for null, true, and false",
            "description": "Test VALUE_NULL, VALUE_TRUE, and VALUE_FALSE tokens in sequence, expecting exactMatch patterns for each."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[]"
                },
                {
                    "parameter": "name",
                    "value": "\"testName\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [UNKNOWN_TOKEN, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test unknown token handling, expecting a JsonParseException due to an unrecognized token."
        }
    ]
}