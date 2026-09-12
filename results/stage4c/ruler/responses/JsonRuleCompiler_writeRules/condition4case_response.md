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
                    "value": "JsonParser with tokens [START_OBJECT, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a single map with processed patterns",
            "description": "Test handling of START_OBJECT token, which should invoke processMatchExpression."
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
                    "value": "JsonParser with tokens [VALUE_STRING, END_ARRAY] and text '192.168.0.1'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a single map with an IP range pattern",
            "description": "Test VALUE_STRING token with an IP address, expecting conversion to a range."
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
                    "value": "JsonParser with tokens [VALUE_STRING, END_ARRAY] and text 'testString'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "true"
                }
            ],
            "expected": "rules contain a single map with a quoted exact match pattern",
            "description": "Test VALUE_STRING token with withQuotes=true, expecting a quoted exact match."
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
                    "value": "JsonParser with tokens [VALUE_NUMBER_INT, END_ARRAY] and value 123"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a map with numericEquals and exactMatch patterns",
            "description": "Test VALUE_NUMBER_INT token, expecting both numericEquals and exactMatch patterns."
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
                    "value": "JsonParser with tokens [VALUE_NULL, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules contain a single map with an exactMatch pattern for null",
            "description": "Test VALUE_NULL token, expecting an exactMatch pattern for null."
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
                    "value": "JsonParser with tokens [END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "JsonParseException with message 'Empty arrays are not allowed'",
            "description": "Test empty array handling, expecting a JsonParseException."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": "[{\"existingKey\": [Patterns.exactMatch(\"existingValue\")]}]"
                },
                {
                    "parameter": "name",
                    "value": "\"newKey\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with tokens [VALUE_TRUE, END_ARRAY]"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rules updated with a new key and exactMatch pattern for true",
            "description": "Test VALUE_TRUE token with existing rules, expecting an additional key-value pair."
        }
    ]
}