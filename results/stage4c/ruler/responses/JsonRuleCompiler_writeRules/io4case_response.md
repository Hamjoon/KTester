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
                    "value": "JsonParser with tokens [VALUE_NUMBER_FLOAT, END_ARRAY] and value 123.45"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with numericEquals and exactMatch patterns for the float",
            "description": "Test VALUE_NUMBER_FLOAT token, expecting both numericEquals and exactMatch patterns for the float number."
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
                    "value": "JsonParser with tokens [VALUE_FALSE, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a single map with an exactMatch pattern for false",
            "description": "Test VALUE_FALSE token, expecting an exactMatch pattern for false."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[{\"existingKey\": [Patterns.exactMatch(\"existingValue\")]}]"
                },
                {
                    "parameter": "name",
                    "value": "\"existingKey\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [VALUE_STRING, END_ARRAY] and text 'newValue'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules update the existing key with a new exactMatch pattern",
            "description": "Test updating an existing key with a new VALUE_STRING token, expecting the existing key to be updated with a new exactMatch pattern."
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
                    "value": "JsonParser with tokens [VALUE_STRING, VALUE_NUMBER_INT, END_ARRAY] and text 'text' and number 10"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with exactMatch pattern for the string and both numericEquals and exactMatch patterns for the number",
            "description": "Test a combination of VALUE_STRING and VALUE_NUMBER_INT tokens, expecting patterns for both string and number."
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
                    "value": "JsonParser with tokens [START_OBJECT, VALUE_STRING, END_OBJECT, END_ARRAY] and text 'nestedString'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with processed patterns from the nested object",
            "description": "Test handling of a nested START_OBJECT with VALUE_STRING, expecting processMatchExpression to handle the nested structure."
        }
    ]
}