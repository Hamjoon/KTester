{
    "result": [
        {
            "group": "deleteStep - Key Index Negative",
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
                            "value": -1
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
                    "expected": [],
                    "description": "Test case where the keyIndex is negative. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - Patterns Map Null",
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
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": null
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
                    "description": "Test case where the patterns map is null. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - Patterns List Null",
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
                            "value": 0
                        },
                        {
                            "parameter": "patterns",
                            "value": {"key1": null}
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
                    "description": "Test case where the patterns list for the key is null. The method should return without modifying deletedKeys."
                }
            ]
        },
        {
            "group": "deleteStep - State is Null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": null
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
                    "expected": [],
                    "description": "Test case where the state is null. The method should return without modifying deletedKeys."
                }
            ]
        }
    ]
}