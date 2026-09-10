{
    "test cases": [
        {
            "group": "deleteStep - Empty Keys List",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState"
                        },
                        {
                            "parameter": "keys",
                            "value": "[]"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "patterns",
                            "value": "map with valid patterns"
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
                    "description": "Test when the keys list is empty, expecting no deletion action."
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
                            "value": "mocked NameState with valid transitions"
                        },
                        {
                            "parameter": "keys",
                            "value": "['key1']"
                        },
                        {
                            "parameter": "keyIndex",
                            "value": "1"
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
                    "expected": "no changes to deletedKeys",
                    "description": "Test when keyIndex is out of bounds, expecting no deletion action."
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
                            "value": "mocked NameState with valid transitions"
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
                            "value": "map with key1 having no Patterns"
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
                    "description": "Test when there are no patterns for the key, expecting no deletion action."
                }
            ]
        },
        {
            "group": "deleteStep - Multiple Patterns for Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with valid transitions"
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
                            "value": "map with key1 having multiple valid Patterns"
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
                    "description": "Test when multiple patterns are present for a key, expecting the key to be deleted if the rule is matched."
                }
            ]
        },
        {
            "group": "deleteStep - Key with Existing Rules",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "mocked NameState with existing rules"
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
                    "expected": "deletedKeys should remain empty",
                    "description": "Test when the key has existing rules and the rule cannot be deleted, expecting no changes to deletedKeys."
                }
            ]
        }
    ]
}