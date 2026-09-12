{
    "group": "writeRules Exception Test Cases",
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
                    "value": "JsonParser with malformed input"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "JsonParseException due to malformed JSON input",
            "description": "Test case for JsonParser with malformed input, should throw JsonParseException due to invalid JSON structure."
        }
    ]
}