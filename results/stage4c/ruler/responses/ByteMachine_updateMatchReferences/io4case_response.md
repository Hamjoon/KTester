{
    "group": "updateMatchReferences",
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
            "expected": "No change in state",
            "description": "Test case for PREFIX pattern type, expecting no change in any state."
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
            "expected": "No change in state",
            "description": "Test case for EXISTS pattern type, expecting no change in any state."
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
            "expected": "No change in state",
            "description": "Test case for EQUALS_IGNORE_CASE pattern type, expecting no change in any state."
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
            "expected": "No change in state",
            "description": "Test case for WILDCARD pattern type, expecting no change in any state."
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
                        "nextNameState": "state5"
                    }
                }
            ],
            "expected": "anythingButs updated",
            "description": "Test case for ANYTHING_BUT pattern without numeric, expecting anythingButs to be updated."
        }
    ]
}