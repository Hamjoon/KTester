{
    "output": [
        {
            "group": "writeRules - Exception Cases",
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
                            "value": "mockedJsonParserWithUnexpectedToken"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Match value must be String, number, true, false, or null'",
                    "description": "Test case where parser returns an unexpected token, should throw an exception."
                }
            ]
        },
        {
            "group": "writeRules - Additional Exception Cases",
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
                            "value": "mockedJsonParserWithInvalidNumericValue"
                        },
                        {
                            "parameter": "withQuotes",
                            "value": false
                        }
                    ],
                    "expected": "throws JsonParseException with message 'Invalid numeric value encountered'",
                    "description": "Test case where parser encounters an invalid numeric value that cannot be processed, should throw an exception."
                }
            ]
        }
    ]
}