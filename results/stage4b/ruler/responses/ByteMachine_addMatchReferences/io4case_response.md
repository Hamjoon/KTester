{
    "testCases": [
        {
            "group": "Test addMatchReferences with Wildcard Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.WILDCARD)"
                        }
                    ],
                    "expected": "No change in hasNumeric, hasIP, hasSuffix",
                    "description": "Test with a WILDCARD pattern. No counters should increment."
                }
            ]
        },
        {
            "group": "Test addMatchReferences with Equals Ignore Case Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.EQUALS_IGNORE_CASE)"
                        }
                    ],
                    "expected": "No change in hasNumeric, hasIP, hasSuffix",
                    "description": "Test with an EQUALS_IGNORE_CASE pattern. No counters should increment."
                }
            ]
        },
        {
            "group": "Test addMatchReferences with Anything But Numeric Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.ANYTHING_BUT)"
                        }
                    ],
                    "expected": "hasNumeric = 1 and anythingButs size = 1",
                    "description": "Test with an ANYTHING_BUT pattern. hasNumeric should increment and anythingButs should increase."
                }
            ]
        },
        {
            "group": "Test addMatchReferences with Anything But Numeric Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.ANYTHING_BUT)"
                        }
                    ],
                    "expected": "hasNumeric = 1 and anythingButs size = 1",
                    "description": "Test with an ANYTHING_BUT pattern. hasNumeric should increment and anythingButs should increase."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.ANYTHING_BUT_IGNORE_CASE)"
                        }
                    ],
                    "expected": "anythingButs size = 1",
                    "description": "Test with an ANYTHING_BUT_IGNORE_CASE pattern. anythingButs should increase, but hasNumeric should not."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.ANYTHING_BUT_SUFFIX)"
                        }
                    ],
                    "expected": "hasSuffix = 1 and anythingButs size = 1",
                    "description": "Test with an ANYTHING_BUT_SUFFIX pattern. hasSuffix should increment and anythingButs should increase."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.ANYTHING_BUT_PREFIX)"
                        }
                    ],
                    "expected": "anythingButs size = 1",
                    "description": "Test with an ANYTHING_BUT_PREFIX pattern. anythingButs should increase."
                }
            ]
        },
        {
            "group": "Test addMatchReferences with Default Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": "new ByteMatch(Patterns.UNDEFINED)"
                        }
                    ],
                    "expected": "Throw AssertionError",
                    "description": "Test with an undefined pattern type. Should throw an AssertionError."
                }
            ]
        }
    ]
}