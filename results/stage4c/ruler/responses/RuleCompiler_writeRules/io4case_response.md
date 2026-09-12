{
    "group": "writeRules Additional Test Cases",
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
                    "value": "JsonParser with input '[\"192.168.0.1/24\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a Range object representing the CIDR range",
            "description": "Test case for VALUE_STRING token with a valid CIDR notation, should add a Range object to the values list."
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
                    "value": "JsonParser with input '[\"invalidCIDR\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with a Patterns object without quotes",
            "description": "Test case for VALUE_STRING token with invalid CIDR notation, should add an unquoted Patterns object to the values list."
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
                    "value": "JsonParser with input '[\"123456789012345678901234567890\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with an exact match Patterns object for the large number",
            "description": "Test case for VALUE_STRING token with a very large number string to test handling of large inputs."
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
                    "value": "JsonParser with input '[\"123.45.67.89\"]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "true"
                }
            ],
            "expected": "rule contains a list with a Patterns object with quotes",
            "description": "Test case for VALUE_STRING token with an invalid IP address, should add a quoted Patterns object to the values list."
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
                    "value": "JsonParser with input '[123456789012345678901234567890]'"
                },
                {
                    "parameter": "withQuotes",
                    "value": "false"
                }
            ],
            "expected": "rule contains a list with an exact match Patterns object for the large number",
            "description": "Test case for VALUE_NUMBER_INT token with a very large integer to test handling of large inputs."
        }
    ]
}