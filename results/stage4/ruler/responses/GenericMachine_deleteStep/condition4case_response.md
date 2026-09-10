{
    "testCases": [
        {
            "group": "deleteStep - No Matchers",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with no transitions"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "patterns",
                            "value": "empty map"
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mocked RuleName"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": "empty list"
                        }
                    ],
                    "expected": "no changes to deletedKeys",
                    "description": "Test when both matchers are null, expecting no deletion action."
                }
            ]
        },
        {
            "group": "deleteStep - Pattern Found in NameMatcher",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with valid key transition"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "patterns",
                            "value": "map with key1 having valid Patterns"
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mocked RuleName"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": "empty list"
                        }
                    ],
                    "expected": "deletedKeys should contain 'key1'",
                    "description": "Test when a valid pattern is found in NameMatcher and the rule is deleted."
                }
            ]
        },
        {
            "group": "deleteStep - Pattern Found in ByteMachine",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with valid byte transition"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "patterns",
                            "value": "map with key1 having valid Patterns"
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mocked RuleName"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": "empty list"
                        }
                    ],
                    "expected": "deletedKeys should contain 'key1'",
                    "description": "Test when a valid pattern is found in ByteMachine and the rule is deleted."
                }
            ]
        },
        {
            "group": "deleteStep - Last Step with No Rules",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with no rules"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1', 'key2']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "1"
                        },
                        {
                            "parameter": "patterns",
                            "value": "map with key2 having valid Patterns"
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mocked RuleName"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": "empty list"
                        }
                    ],
                    "expected": "deletedKeys should contain 'key2'",
                    "description": "Test when reaching the last state and there are no rules to delete, expecting deletion of key."
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
                            "value": "mocked NameState with multiple transitions"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1', 'key2']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "patterns",
                            "value": "map with key1 having valid Patterns and key2 having valid Patterns"
                        },
                        {
                            "parameter": "ruleName",
                            "value": "mocked RuleName"
                        },
                        {
                            "parameter": "deletedKeys",
                            "value": "empty list"
                        }
                    ],
                    "expected": "deletedKeys should contain 'key1' and 'key2'",
                    "description": "Test when multiple keys lead to recursive calls, expecting both keys to be deleted."
                }
            ]
        }
    ]
}