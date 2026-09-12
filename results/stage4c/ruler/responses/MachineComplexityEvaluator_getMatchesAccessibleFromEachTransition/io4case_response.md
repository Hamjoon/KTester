{
    "group": "getMatchesAccessibleFromEachTransition",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with one transition leading to a state with multiple transitions"
                }
            ],
            "expected": "map with multiple entries and match sets containing matches from all transitions",
            "description": "Test with a ByteState that has one transition leading to another state with multiple transitions. Expect a map with multiple entries where each entry's match set contains matches from all transitions."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with deeply nested transitions"
                }
            ],
            "expected": "map with all transitions and their accessible matches",
            "description": "Test with a ByteState that has deeply nested transitions. Expect a map with all transitions and their accessible matches computed correctly, ensuring that the depth-first search handles deep nesting without errors."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with transitions leading back to the original state"
                }
            ],
            "expected": "map with transitions and matches without infinite loops",
            "description": "Test with a ByteState where transitions lead back to the original state. Expect a map with transitions and their matches computed without falling into infinite loops, ensuring visited states are handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with transitions containing WILDCARD matches"
                }
            ],
            "expected": "map with transitions and match sets including WILDCARD matches",
            "description": "Test with a ByteState where transitions contain WILDCARD matches. Expect a map with transitions and their match sets including the WILDCARD matches, verifying that special match types are handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with transitions leading to states with identical transitions"
                }
            ],
            "expected": "map with unique transitions and their matches",
            "description": "Test with a ByteState where transitions lead to states with identical transitions. Expect a map with unique transitions and their matches, ensuring that identical transitions are not redundantly processed."
        }
    ]
}