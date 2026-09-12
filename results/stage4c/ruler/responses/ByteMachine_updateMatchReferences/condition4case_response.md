{
    "group": "updateMatchReferences",
    "cases": [
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "EXACT"
                        }
                    }
                }
            ],
            "expected": "No change in state",
            "description": "Test case for EXACT pattern type, expecting no change in any state."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "SUFFIX"
                        }
                    }
                }
            ],
            "expected": "hasSuffix decremented",
            "description": "Test case for SUFFIX pattern type, expecting hasSuffix to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "NUMERIC_EQ"
                        }
                    }
                }
            ],
            "expected": "hasNumeric decremented",
            "description": "Test case for NUMERIC_EQ pattern type, expecting hasNumeric to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "NUMERIC_RANGE",
                            "isCIDR": true
                        }
                    }
                }
            ],
            "expected": "hasIP decremented",
            "description": "Test case for NUMERIC_RANGE pattern with CIDR, expecting hasIP to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "NUMERIC_RANGE",
                            "isCIDR": false
                        }
                    }
                }
            ],
            "expected": "hasNumeric decremented",
            "description": "Test case for NUMERIC_RANGE pattern without CIDR, expecting hasNumeric to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT",
                            "isNumeric": true
                        },
                        "nextNameState": "state1"
                    }
                }
            ],
            "expected": "anythingButs updated, hasNumeric decremented",
            "description": "Test case for ANYTHING_BUT pattern with numeric, expecting anythingButs to be updated and hasNumeric to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_IGNORE_CASE"
                        },
                        "nextNameState": "state2"
                    }
                }
            ],
            "expected": "anythingButs updated",
            "description": "Test case for ANYTHING_BUT_IGNORE_CASE pattern, expecting anythingButs to be updated."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_SUFFIX"
                        },
                        "nextNameState": "state3"
                    }
                }
            ],
            "expected": "anythingButs updated, hasSuffix decremented",
            "description": "Test case for ANYTHING_BUT_SUFFIX pattern, expecting anythingButs to be updated and hasSuffix to be decremented."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_PREFIX"
                        },
                        "nextNameState": "state4"
                    }
                }
            ],
            "expected": "anythingButs updated",
            "description": "Test case for ANYTHING_BUT_PREFIX pattern, expecting anythingButs to be updated."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "UNKNOWN"
                        }
                    }
                }
            ],
            "expected": "AssertionError",
            "description": "Test case for unknown pattern type, expecting an AssertionError to be thrown."
        }
    ]
}