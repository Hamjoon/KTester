{
    "group": "deleteStep Method Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithTransitionsAndNoMatchingPattern"
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
                    "value": {
                        "key1": ["nonMatchingPattern"]
                    }
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
            "description": "Test case where transitions exist but none of the patterns match, so nothing should be deleted."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithMultipleTransitions"
                },
                {
                    "parameter": "keys",
                    "value": ["key1", "key2", "key3"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {
                        "key1": ["pattern1"],
                        "key2": ["pattern2"],
                        "key3": ["pattern3"]
                    }
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
            "expected": "['key3']",
            "description": "Test case with multiple keys where the rule is deleted at the last key and intermediate states also have transitions."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithEmptyPatterns"
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
                    "value": {
                        "key1": []
                    }
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
            "description": "Test case where the patterns map contains an empty list for the key, so no deletion should occur."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithDeepHierarchy"
                },
                {
                    "parameter": "keys",
                    "value": ["key1", "key2", "key3", "key4"]
                },
                {
                    "parameter": "keyIndex",
                    "value": 0
                },
                {
                    "parameter": "patterns",
                    "value": {
                        "key1": ["pattern1"],
                        "key2": ["pattern2"],
                        "key3": ["pattern3"],
                        "key4": ["pattern4"]
                    }
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
            "expected": "['key4']",
            "description": "Test case with a deep hierarchy of keys where the rule is deleted at the last key."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "MockedNameStateWithMultipleRules"
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
                    "value": {
                        "key1": ["pattern1"]
                    }
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
            "description": "Test case where the state has multiple rules, but only the specified rule should be considered for deletion."
        }
    ]
}