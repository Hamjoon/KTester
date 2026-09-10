{
    "group": "getMatchesAccessibleFromEachTransition Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with a transition leading to a state with null transitions"
                }
            ],
            "expected": "map with one transition and empty matches",
            "description": "Test with ByteState that has a transition leading to another state which has null transitions, expecting the map to contain this transition with empty matches."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with a transition leading to a state that throws an exception"
                }
            ],
            "expected": "exception due to invalid state transition",
            "description": "Test with ByteState that has a transition leading to a state that throws an exception when accessed, expecting the method to throw an exception."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState with a transition leading to a state with invalid match types"
                }
            ],
            "expected": "exception due to invalid match types",
            "description": "Test with ByteState that has a transition leading to a state with invalid match types, expecting the method to throw an exception."
        }
    ]
}