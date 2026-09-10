{
    "group": "writeRules",
    "cases": [
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithEmptyArray"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "JsonParseException with message 'Empty arrays are not allowed'",
            "description": "Test case where the parser points to an empty JSON array, expecting a JsonParseException."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithSingleObject"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "rule contains 'testRule' with a list of Patterns",
            "description": "Test case where the parser points to a JSON array with a single object, expecting the rule to contain the processed Patterns."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithStringValue"
                },
                {
                    "parameter": "withQuotes",
                    "value": true
                }
            ],
            "expected": "rule contains 'testRule' with Patterns that has quoted string",
            "description": "Test case where the parser points to a JSON array with a string value and withQuotes is true, expecting the string to be quoted."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithNumberValue"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "rule contains 'testRule' with Patterns for numeric match",
            "description": "Test case where the parser points to a JSON array with a numeric value, expecting the rule to contain numeric match patterns."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithBooleanValue"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "rule contains 'testRule' with Patterns for boolean match",
            "description": "Test case where the parser points to a JSON array with a boolean value, expecting the rule to contain boolean match patterns."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithNullValue"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "rule contains 'testRule' with Patterns for null match",
            "description": "Test case where the parser points to a JSON array with a null value, expecting the rule to contain patterns for null."
        },
        {
            "input": [
                {
                    "parameter": "rule",
                    "value": {}
                },
                {
                    "parameter": "name",
                    "value": "testRule"
                },
                {
                    "parameter": "parser",
                    "value": "mockParserWithInvalidValue"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test case where the parser points to an invalid value, expecting a JsonParseException."
        }
    ]
}