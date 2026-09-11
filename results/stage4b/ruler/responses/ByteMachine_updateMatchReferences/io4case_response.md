{
    "testCases": [
        {
            "group": "updateMatchReferences - Prefix Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "PREFIX"
                                }
                            }
                        }
                    ],
                    "expected": "No change in hasNumeric, hasIP, hasSuffix",
                    "description": "Test the method with a PREFIX pattern type, ensuring no decrements occur."
                }
            ]
        },
        {
            "group": "updateMatchReferences - Wildcard Pattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "WILDCARD"
                                }
                            }
                        }
                    ],
                    "expected": "No change in hasNumeric, hasIP, hasSuffix",
                    "description": "Test the method with a WILDCARD pattern type to verify that no decrements occur."
                }
            ]
        },
        {
            "group": "updateMatchReferences - Anything But Pattern with Numeric (No nextNameState)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "ANYTHING_BUT",
                                    "isNumeric": true
                                }
                            }
                        }
                    ],
                    "expected": "hasNumeric decremented by 1 and nothing removed from anythingButs",
                    "description": "Test the method with an ANYTHING_BUT pattern type that is numeric, ensuring hasNumeric is decremented when nextNameState is not provided."
                }
            ]
        },
        {
            "group": "updateMatchReferences - Anything But Ignore Case Pattern (No nextNameState)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "ANYTHING_BUT_IGNORE_CASE"
                                }
                            }
                        }
                    ],
                    "expected": "No change in anythingButs",
                    "description": "Test the method with an ANYTHING_BUT_IGNORE_CASE pattern type without nextNameState to ensure nothing is removed."
                }
            ]
        },
        {
            "group": "updateMatchReferences - Numeric Range Pattern (CIDR with nextNameState)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "NUMERIC_RANGE",
                                    "isCIDR": true
                                },
                                "nextNameState": "someState"
                            }
                        }
                    ],
                    "expected": "hasIP decremented by 1 and nothing removed from anythingButs",
                    "description": "Test the method with a NUMERIC_RANGE pattern type where isCIDR is true and nextNameState is provided, verifying hasIP is decremented."
                }
            ]
        },
        {
            "group": "updateMatchReferences - Numeric Range Pattern (Not CIDR with nextNameState)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "NUMERIC_RANGE",
                                    "isCIDR": false
                                },
                                "nextNameState": "someState"
                            }
                        }
                    ],
                    "expected": "hasNumeric decremented by 1 and nothing removed from anythingButs",
                    "description": "Test the method with a NUMERIC_RANGE pattern type where isCIDR is false and nextNameState is provided, ensuring hasNumeric is decremented."
                }
            ]
        }
    ]
}