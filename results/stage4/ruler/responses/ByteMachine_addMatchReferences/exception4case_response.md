{
    "group": "addMatchReferences - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "match",
                    "value": "new ByteMatch(new Patterns(MatchType.UNDEFINED))"
                }
            ],
            "expected": "An AssertionError should be thrown.",
            "description": "Test that adding a match with an undefined pattern type throws an AssertionError."
        }
    ]
}