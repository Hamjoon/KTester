{
    "group": "getMatchesAccessibleFromEachTransition",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "null"
                }
            ],
            "expected": "empty map",
            "description": "Test with null ByteState. Expect an empty map as there are no transitions to process."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with no transitions"
                }
            ],
            "expected": "empty map",
            "description": "Test with a ByteState that has no transitions. Expect an empty map as there are no transitions to process."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with one transition leading to null"
                }
            ],
            "expected": "map with one entry and empty match set",
            "description": "Test with a ByteState that has one transition leading to a null next state. Expect a map with one entry where the match set is empty."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with one transition leading to a state with no transitions"
                }
            ],
            "expected": "map with one entry and match set containing matches from the transition",
            "description": "Test with a ByteState that has one transition leading to another state with no transitions. Expect a map with one entry where the match set contains matches from the initial transition."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with multiple transitions and nested states"
                }
            ],
            "expected": "map with all transitions and their accessible matches",
            "description": "Test with a complex ByteState that has multiple transitions leading to nested states. Expect a map with all transitions and their accessible matches computed correctly."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with circular transitions"
                }
            ],
            "expected": "map with transitions and matches without infinite loops",
            "description": "Test with a ByteState that has circular transitions. Expect a map with transitions and their matches computed without falling into infinite loops."
        }
    ]
}