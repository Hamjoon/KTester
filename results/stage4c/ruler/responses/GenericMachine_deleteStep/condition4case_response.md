{
    "group": "deleteStep Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithNoTransitions"
                },
                {
                    "parameter": "keys",
                    "value": ["key1"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {"key1": ["pattern1"]}
                },
                {
                    "parameter": "ruleName",
                    "value": "rule1"
                },
                {
                    "parameter": "deletedKeys",
                    "value": []
                }
            ],
            "expected": "[]",
            "description": "Test case where both byteMachine and nameMatcher are null, so nothing should be deleted."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithByteMachine"
                },
                {
                    "parameter": "keys",
                    "value": ["key1"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {"key1": ["pattern1"]}
                },
                {
                    "parameter": "ruleName",
                    "value": "rule1"
                },
                {
                    "parameter": "deletedKeys",
                    "value": []
                }
            ],
            "expected": "[]",
            "description": "Test case where byteMachine is not null and nameMatcher is null, and the pattern is not a name pattern."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithNameMatcher"
                },
                {
                    "parameter": "keys",
                    "value": ["key1"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {"key1": ["pattern1"]}
                },
                {
                    "parameter": "ruleName",
                    "value": "rule1"
                },
                {
                    "parameter": "deletedKeys",
                    "value": []
                }
            ],
            "expected": "[]",
            "description": "Test case where nameMatcher is not null and byteMachine is null, and the pattern is a name pattern."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithTransitions"
                },
                {
                    "parameter": "keys",
                    "value": ["key1"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {"key1": ["pattern1"]}
                },
                {
                    "parameter": "ruleName",
                    "value": "rule1"
                },
                {
                    "parameter": "deletedKeys",
                    "value": []
                }
            ],
            "expected": "['key1']",
            "description": "Test case where both byteMachine and nameMatcher are not null, and the pattern matches a rule which gets deleted."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithTransitionsAndMultipleKeys"
                },
                {
                    "parameter": "keys",
                    "value": ["key1", "key2"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {"key1": ["pattern1"], "key2": ["pattern2"]}
                },
                {
                    "parameter": "ruleName",
                    "value": "rule1"
                },
                {
                    "parameter": "deletedKeys",
                    "value": []
                }
            ],
            "expected": "['key1', 'key2']",
            "description": "Test case with multiple keys where the rule is deleted at the last key."
        }
    ]
}