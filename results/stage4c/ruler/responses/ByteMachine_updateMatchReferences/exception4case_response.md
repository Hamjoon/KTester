{
    "group": "updateMatchReferences",
    "cases": [
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