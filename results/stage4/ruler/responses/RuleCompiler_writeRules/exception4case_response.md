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
                    "value": "mockParserWithInvalidNumber"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "JsonParseException with message 'Invalid number format'",
            "description": "Test case where the parser points to a JSON array with an invalid number format, expecting a JsonParseException."
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
                    "value": "mockParserWithMalformedJson"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "JsonParseException with message 'Unexpected end-of-input'",
            "description": "Test case where the parser points to a malformed JSON input, expecting a JsonParseException."
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
                    "value": "mockParserWithExtraComma"
                },
                {
                    "parameter": "withQuotes",
                    "value": false
                }
            ],
            "expected": "JsonParseException with message 'Unexpected character encountered while parsing value'",
            "description": "Test case where the parser points to a JSON array with an extra comma, expecting a JsonParseException."
        }
    ]
}