{
    "group": "matches method additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "NumericJsonNode(42)"
                },
                {
                    "parameter": "pattern",
                    "value": "Range(NUMERIC_RANGE, '30', '50', false, false, false)"
                }
            ],
            "expected": true,
            "description": "Test NUMERIC_RANGE pattern with a number within the closed range."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "NumericJsonNode(30)"
                },
                {
                    "parameter": "pattern",
                    "value": "Range(NUMERIC_RANGE, '30', '50', true, false, false)"
                }
            ],
            "expected": false,
            "description": "Test NUMERIC_RANGE pattern with a number at the open bottom boundary of the range."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "TextualJsonNode('\"192.168.1.1\"')"
                },
                {
                    "parameter": "pattern",
                    "value": "Range(NUMERIC_RANGE, '192.168.0.0', '192.168.255.255', false, false, true)"
                }
            ],
            "expected": true,
            "description": "Test NUMERIC_RANGE pattern with a CIDR range and a matching IP address."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "TextualJsonNode('\"192.168.1.1\"')"
                },
                {
                    "parameter": "pattern",
                    "value": "Range(NUMERIC_RANGE, '192.168.0.0', '192.168.0.255', false, false, true)"
                }
            ],
            "expected": false,
            "description": "Test NUMERIC_RANGE pattern with a CIDR range and a non-matching IP address."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "TextualJsonNode('\"example\"')"
                },
                {
                    "parameter": "pattern",
                    "value": "ValuePatterns(EXISTS, '')"
                }
            ],
            "expected": true,
            "description": "Test EXISTS pattern which should always return true."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "TextualJsonNode('\"example\"')"
                },
                {
                    "parameter": "pattern",
                    "value": "ValuePatterns(ABSENT, '')"
                }
            ],
            "expected": false,
            "description": "Test ABSENT pattern which should always return false."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "TextualJsonNode('\"test\"')"
                },
                {
                    "parameter": "pattern",
                    "value": "ValuePatterns(PREFIX, '\"wrong\"')"
                }
            ],
            "expected": false,
            "description": "Test PREFIX pattern with a non-matching prefix."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "NumericJsonNode(42)"
                },
                {
                    "parameter": "pattern",
                    "value": "ValuePatterns(NUMERIC_EQ, '43')"
                }
            ],
            "expected": false,
            "description": "Test NUMERIC_EQ pattern with a non-matching numeric value."
        }
    ]
}