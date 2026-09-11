{
    "group": "writeRules - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": []
                },
                {
                    "parameter": "name",
                    "value": "testInvalidToken"
                },
                {
                    "parameter": "parser",
                    "value": "mockJsonParserWithInvalidToken"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test case to verify that an exception is thrown when an invalid token is encountered in the JSON."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": []
                },
                {
                    "parameter": "name",
                    "value": "testEmptyArray"
                },
                {
                    "parameter": "parser",
                    "value": "mockJsonParserWithEmptyArray"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "throws JsonParseException with message 'Empty arrays are not allowed'",
            "description": "Test case to verify that an exception is thrown when an empty array is provided."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": []
                },
                {
                    "parameter": "name",
                    "value": "testInvalidString"
                },
                {
                    "parameter": "parser",
                    "value": "mockJsonParserWithInvalidString"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test case to verify that an exception is thrown when an invalid string is encountered in the JSON."
        },
        {
            "input": [
                {
                    "parameter": "rules",
                    "value": []
                },
                {
                    "parameter": "name",
                    "value": "testInvalidBoolean"
                },
                {
                    "parameter": "parser",
                    "value": "mockJsonParserWithInvalidBoolean"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
            "description": "Test case to verify that an exception is thrown when an invalid boolean value is encountered in the JSON."
        }
    ]
}