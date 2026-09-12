{
    "group": "writeRules Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[{\"key\": \"value\"}]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with one Patterns object",
            "description": "Test case for START_OBJECT token, should add processed match expression to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[\"192.168.0.1\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a Range object",
            "description": "Test case for VALUE_STRING token with a valid IP address, should add a Range object to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[\"someString\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "true"
                }
            ],
            "expected": "rule contains a list with a Patterns object with quotes",
            "description": "Test case for VALUE_STRING token withQuotes set to true, should add a quoted Patterns object to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[\"someString\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a Patterns object without quotes",
            "description": "Test case for VALUE_STRING token withQuotes set to false, should add an unquoted Patterns object to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[123.456]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a numeric Patterns object and an exact match Patterns object",
            "description": "Test case for VALUE_NUMBER_FLOAT token, should add numeric and exact match Patterns objects to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[123]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a numeric Patterns object and an exact match Patterns object",
            "description": "Test case for VALUE_NUMBER_INT token, should add numeric and exact match Patterns objects to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[null]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with an exact match Patterns object for null",
            "description": "Test case for VALUE_NULL token, should add an exact match Patterns object for null to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[true]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with an exact match Patterns object for true",
            "description": "Test case for VALUE_TRUE token, should add an exact match Patterns object for true to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[false]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with an exact match Patterns object for false",
            "description": "Test case for VALUE_FALSE token, should add an exact match Patterns object for false to the values list."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "JsonParseException with message 'Empty arrays are not allowed'",
            "description": "Test case for empty array, should throw JsonParseException indicating empty arrays are not allowed."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": "{}"
                },
                {
                    "parameter": "name",
                    "value": "\"testRule\""
                },
                {
                    "parameter": "parser",
                    "value": "JsonParser with input '[\"unsupported\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test case for unsupported token, should throw JsonParseException indicating invalid match value type."
        }
    ]
}