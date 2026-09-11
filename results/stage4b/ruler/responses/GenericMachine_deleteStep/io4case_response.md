{
    "test cases": [
        {
            "group": "deleteStep - Multiple Patterns for a Single Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithMultiplePatterns"
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
                                "key1": ["pattern1", "pattern2"]
                            }
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
                    "description": "Test case where multiple patterns exist for a single key and the rule is deleted for one of the patterns. The deletedKeys should include 'key1' if any rule was successfully deleted."
                }
            ]
        },
        {
            "group": "deleteStep - Empty Patterns List",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithEmptyPatterns"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key4"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {
                                "key4": []
                            }
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
                    "description": "Test case where the patterns list for the key is empty. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - Key Index Out of Bounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithValidMatchers"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key1"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 1
                        },
                        {
                            "parameter": "patterns",
                            "value": {
                                "key1": ["pattern1"]
                            }
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
                    "description": "Test case where the keyIndex exceeds the keys list size. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - No Patterns for Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithNoPatterns"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key5"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {
                                "key5": null
                            }
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
                    "description": "Test case where the patterns for the key are null. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - State with No Transition",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mockStateWithNoTransition"
                        },
                        {
                            "parameter": "keys",
                            "value": ["key6"]
                        },
                        {
                            "parameter": "keyIndex",
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {
                                "key6": ["pattern4"]
                            }
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
                    "description": "Test case where the state has no transitions for the specified key. The method should return without modifying deletedKeys."
                }
            ]
        }
    ]
}