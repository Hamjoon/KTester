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
            "expected": null,
            "description": "Test the behavior when the pattern type is EXACT. The method should execute without any changes to the state."
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
            "expected": "hasSuffix should be decremented by 1",
            "description": "Test the behavior when the pattern type is SUFFIX. The hasSuffix counter should decrement."
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
            "expected": "hasNumeric should be decremented by 1",
            "description": "Test the behavior when the pattern type is NUMERIC_EQ. The hasNumeric counter should decrement."
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
            "expected": "hasNumeric should be decremented by 1",
            "description": "Test the behavior when the pattern type is NUMERIC_RANGE and isCIDR is false. The hasNumeric counter should decrement."
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
            "expected": "hasIP should be decremented by 1",
            "description": "Test the behavior when the pattern type is NUMERIC_RANGE and isCIDR is true. The hasIP counter should decrement."
        },
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
            "expected": "hasNumeric should be decremented by 1 and the next name state should be removed from anythingButs",
            "description": "Test the behavior when the pattern type is ANYTHING_BUT. The hasNumeric counter should decrement and the next name state should be removed from anythingButs."
        },
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
            "expected": "The next name state should be removed from anythingButs",
            "description": "Test the behavior when the pattern type is ANYTHING_BUT_IGNORE_CASE. The next name state should be removed from anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_SUFFIX"
                        }
                    }
                }
            ],
            "expected": "hasSuffix should be decremented by 1 and the next name state should be removed from anythingButs",
            "description": "Test the behavior when the pattern type is ANYTHING_BUT_SUFFIX. The hasSuffix counter should decrement and the next name state should be removed from anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_PREFIX"
                        }
                    }
                }
            ],
            "expected": "The next name state should be removed from anythingButs",
            "description": "Test the behavior when the pattern type is ANYTHING_BUT_PREFIX. The next name state should be removed from anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "UNKNOWN_TYPE"
                        }
                    }
                }
            ],
            "expected": "AssertionError with message 'Not implemented yet'",
            "description": "Test the behavior when the pattern type is unknown. The method should throw an AssertionError indicating that the type is not implemented."
        }
    ]
}