{
    "group": "updateMatchReferences - Default Case",
    "cases": [
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
            "expected": "throws AssertionError",
            "description": "Test the method with an unknown pattern type to ensure an AssertionError is thrown."
        }
    ]
}