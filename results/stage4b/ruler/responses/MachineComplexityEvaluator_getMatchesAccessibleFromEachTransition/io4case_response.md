{
    "test cases": [
        {
            "group": "getMatchesAccessibleFromEachTransition",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with one transition leading to another state with no transitions"
                        }
                    ],
                    "expected": "{SingleByteTransition -> Set<ByteMatch>}",
                    "description": "Test case where the input ByteState has one transition leading to another state with no transitions. The method should return a map with the transition and an empty set of matches."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with multiple transitions leading to some states with matches and some without"
                        }
                    ],
                    "expected": "{SingleByteTransition1 -> Set<ByteMatch1>, SingleByteTransition2 -> Set<ByteMatch2>, SingleByteTransition3 -> Set<ByteMatch3>}",
                    "description": "Test case where the input ByteState has multiple transitions leading to various states, some of which contain matches and some do not. The method should return a map with all transitions and their corresponding matches."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with deep transitions"
                        }
                    ],
                    "expected": "{SingleByteTransition -> Set<ByteMatch>}",
                    "description": "Test case where the input ByteState has deep transitions that lead to matches after several states. The method should correctly compute matches through the depth of transitions."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with multiple circular transitions"
                        }
                    ],
                    "expected": "{SingleByteTransition -> Set<ByteMatch>}",
                    "description": "Test case where the input ByteState has multiple circular transitions. The method should handle the circular references correctly and return the expected matches without causing an infinite loop."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "ByteState with transitions that lead to states with a large number of matches"
                        }
                    ],
                    "expected": "{SingleByteTransition -> Set<ByteMatch>}",
                    "description": "Test case where the input ByteState has transitions leading to states that contain a large number of matches. The method should return the correct matches efficiently."
                }
            ]
        }
    ]
}