{
    "group": "addMatchReferences",
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
            "expected": "No state change",
            "description": "Test case for EXACT pattern type where no counters or sets should be modified."
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
            "expected": "hasSuffix incremented by 1",
            "description": "Test case for SUFFIX pattern type where hasSuffix counter should be incremented."
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
            "expected": "hasNumeric incremented by 1",
            "description": "Test case for NUMERIC_EQ pattern type where hasNumeric counter should be incremented."
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
            "expected": "hasIP incremented by 1",
            "description": "Test case for NUMERIC_RANGE pattern type with CIDR true where hasIP counter should be incremented."
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
            "expected": "hasNumeric incremented by 1",
            "description": "Test case for NUMERIC_RANGE pattern type with CIDR false where hasNumeric counter should be incremented."
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
            "expected": "hasNumeric incremented by 1 and state1 added to anythingButs",
            "description": "Test case for ANYTHING_BUT pattern type with isNumeric true where hasNumeric counter should be incremented and nextNameState should be added to anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT",
                            "isNumeric": false
                        },
                        "nextNameState": "state2"
                    }
                }
            ],
            "expected": "state2 added to anythingButs",
            "description": "Test case for ANYTHING_BUT pattern type with isNumeric false where nextNameState should be added to anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_IGNORE_CASE"
                        },
                        "nextNameState": "state3"
                    }
                }
            ],
            "expected": "state3 added to anythingButs",
            "description": "Test case for ANYTHING_BUT_IGNORE_CASE pattern type where nextNameState should be added to anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_SUFFIX"
                        },
                        "nextNameState": "state4"
                    }
                }
            ],
            "expected": "hasSuffix incremented by 1 and state4 added to anythingButs",
            "description": "Test case for ANYTHING_BUT_SUFFIX pattern type where hasSuffix counter should be incremented and nextNameState should be added to anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "ANYTHING_BUT_PREFIX"
                        },
                        "nextNameState": "state5"
                    }
                }
            ],
            "expected": "state5 added to anythingButs",
            "description": "Test case for ANYTHING_BUT_PREFIX pattern type where nextNameState should be added to anythingButs."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "UNDEFINED"
                        }
                    }
                }
            ],
            "expected": "AssertionError thrown",
            "description": "Test case for undefined pattern type which should throw an AssertionError."
        },
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
            "expected": "No state change",
            "description": "Test case for PREFIX pattern type where no counters or sets should be modified."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "EXISTS"
                        }
                    }
                }
            ],
            "expected": "No state change",
            "description": "Test case for EXISTS pattern type where no counters or sets should be modified."
        },
        {
            "input": [
                {
                    "parameter": "match",
                    "value": {
                        "pattern": {
                            "type": "EQUALS_IGNORE_CASE"
                        }
                    }
                }
            ],
            "expected": "No state change",
            "description": "Test case for EQUALS_IGNORE_CASE pattern type where no counters or sets should be modified."
        },
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
            "expected": "No state change",
            "description": "Test case for WILDCARD pattern type where no counters or sets should be modified."
        }
    ]
}