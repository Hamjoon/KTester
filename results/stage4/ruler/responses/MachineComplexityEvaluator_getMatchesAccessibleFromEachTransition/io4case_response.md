{
    "testCases": [
        {
            "group": "getMatchesAccessibleFromEachTransition Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with a transition leading to a null state"
                        }
                    ],
                    "expected": "map with one transition and no matches",
                    "description": "Test with ByteState that has a transition leading to a null next state, expecting the map to contain this transition but no matches."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with a transition leading to a state with no transitions"
                        }
                    ],
                    "expected": "map with one transition and empty matches",
                    "description": "Test with ByteState that has a transition leading to another state which has no transitions, expecting the map to contain this transition with empty matches."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with a transition leading to a state with multiple transitions"
                        }
                    ],
                    "expected": "map with one transition and matches from the next state",
                    "description": "Test with ByteState that has a transition leading to another state which has multiple transitions, expecting the map to contain this transition and the matches from the next state."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with circular transitions"
                        }
                    ],
                    "expected": "map with transitions and matches without infinite loop",
                    "description": "Test with ByteState that has circular transitions, expecting the method to handle it without entering an infinite loop and return the correct matches."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with transitions leading to states with varying match types"
                        }
                    ],
                    "expected": "map with transitions and matches of different types",
                    "description": "Test with ByteState that has transitions leading to states with varying match types, expecting the map to include all transitions and their respective matches of different types."
                }
            ]
        }
    ]
}