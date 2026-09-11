{
    "test cases": [
        {
            "group": "evaluate method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with a transition leading to a wildcard pattern and a next NameState"
                        }
                    ],
                    "expected": "complexity based on wildcard patterns and next NameState",
                    "description": "Test the evaluation of a ByteState with a transition leading to a wildcard pattern and a next NameState, expecting the complexity to reflect the combined complexity of both."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with transitions leading to multiple wildcard patterns and a maxComplexity of 5"
                        }
                    ],
                    "expected": 5,
                    "description": "Test the evaluation of a ByteState with multiple transitions leading to wildcard patterns, expecting the returned complexity to be capped at maxComplexity."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with a single transition leading to no wildcard patterns"
                        }
                    ],
                    "expected": 0,
                    "description": "Test the evaluation of a ByteState with a single transition that leads to no wildcard patterns, expecting a complexity of 0."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with multiple transitions but no wildcard patterns"
                        }
                    ],
                    "expected": 0,
                    "description": "Test the evaluation of a ByteState with multiple transitions that do not lead to any wildcard patterns, expecting a complexity of 0."
                },
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked ByteState with maximum transitions and wildcard patterns"
                        }
                    ],
                    "expected": "maxComplexity",
                    "description": "Test the evaluation of a ByteState with maximum transitions leading to wildcard patterns, expecting the returned complexity to be capped at maxComplexity."
                }
            ]
        }
    ]
}