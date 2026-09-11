{
    "testCases": [
        {
            "group": "deleteStep - No Matchers",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithNoMatchers"
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
                            "value": {"key1": []}
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mockRule"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": []
                        }
                    ],
                    "expected": null,
                    "description": "Test case when both matchers (byteMachine and nameMatcher) are null. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - NamePattern Matching",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithNameMatcher"
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
                            "value": "mockRule"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": []
                        }
                    ],
                    "expected": ["key1"],
                    "description": "Test case where a nameMatcher finds a matching pattern and deletes the rule. The deletedKeys should include 'key1' if the rule was successfully deleted."
                }
            ]
        },
        {
            "group": "deleteStep - ByteMachine Matching",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithByteMachine"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key2"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {"key2": ["pattern2"]}
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mockRule"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": []
                        }
                    ],
                    "expected": ["key2"],
                    "description": "Test case where a byteMachine finds a matching pattern and deletes the rule. The deletedKeys should include 'key2' if the rule was successfully deleted."
                }
            ]
        },
        {
            "group": "deleteStep - Recursive Deletion",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithRecursiveTransitions"
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
                            "value": "mockRule"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": []
                        }
                    ],
                    "expected": ["key1", "key2"],
                    "description": "Test case where the method is called recursively for multiple keys. Both keys should be deleted if their corresponding rules are successfully deleted."
                }
            ]
        },
        {
            "group": "deleteStep - No Rules to Delete",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithNoRules"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key3"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {"key3": ["pattern3"]}
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mockRule"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": []
                        }
                    ],
                    "expected": [],
                    "description": "Test case where the state has no rules to delete. The deletedKeys should remain empty."
                }
            ]
        }
    ]
}