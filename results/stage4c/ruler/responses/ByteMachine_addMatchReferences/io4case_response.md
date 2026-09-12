{
    "group": "addMatchReferences",
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