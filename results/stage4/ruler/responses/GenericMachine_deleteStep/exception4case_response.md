{
    "output": [
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