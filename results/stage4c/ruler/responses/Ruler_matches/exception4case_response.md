{
    "group": "matches method exception test cases",
    "cases": [
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
            "expected": "Exception",
            "description": "Test NUMERIC_RANGE pattern with CIDR range and invalid IP address format causing conversion exception."
        },
        {
            "input": [
                {
                    "parameter": "val",
                    "value": "NumericJsonNode(42)"
                },
                {
                    "parameter": "pattern",
                    "value": "UnsupportedPatternType()"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test default case with unsupported pattern type, expecting RuntimeException."
        }
    ]
}