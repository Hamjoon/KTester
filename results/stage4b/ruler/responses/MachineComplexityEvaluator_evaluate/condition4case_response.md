{
    "testCases": [
        {
            "group": "evaluate method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with no transitions"
                        }
                    ],
                    "expected": 0,
                    "description": "Test the evaluation of a ByteState with no transitions, expecting a complexity of 0."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with transitions leading to multiple wildcard patterns"
                        }
                    ],
                    "expected": "maxComplexity",
                    "description": "Test the evaluation of a ByteState that leads to multiple wildcard patterns, expecting the returned complexity to be capped at maxComplexity."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with transitions leading to a single wildcard pattern"
                        }
                    ],
                    "expected": 1,
                    "description": "Test the evaluation of a ByteState that leads to a single wildcard pattern, expecting a complexity of 1."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with complex transitions"
                        }
                    ],
                    "expected": "complexity greater than 1",
                    "description": "Test the evaluation of a complex ByteState with multiple transitions and wildcard patterns, expecting a complexity greater than 1."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with wildcards and next NameStates"
                        }
                    ],
                    "expected": "maxSize from next NameStates",
                    "description": "Test the evaluation of a ByteState that has wildcard transitions leading to next NameStates, expecting the complexity to correctly reflect the maximum size from those states."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with empty transitions and next NameStates"
                        }
                    ],
                    "expected": 0,
                    "description": "Test the evaluation of a ByteState with empty transitions and no next NameStates, expecting a complexity of 0."
                }
            ]
        }
    ]
}