{
    "test cases": [
        {
            "group": "doTransitionOn",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "Expected NameState(s) based on the matches found for 'test'",
                    "description": "Test with a normal string input to verify standard transitions."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "123"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": true
                        }
                    ],
                    "expected": "Expected NameState(s) based on numeric matches for '123'",
                    "description": "Test with a numeric string input to verify numeric transitions."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": ""
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "Expected no transitions for an empty string",
                    "description": "Test with an empty string input to verify behavior when no transitions should occur."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": true
                        }
                    ],
                    "expected": "Expected NameState(s) based on the matches found for 'test' with numeric flag set",
                    "description": "Test with a string input that is not numeric but the flag is set to true."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "Expected NameState(s) based on the matches found for 'test'",
                    "description": "Test with a normal string input and the numeric flag set to false."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "valid"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "Expected transitions based on the specific matches for 'valid'",
                    "description": "Test with a valid string input to verify transitions based on specific patterns."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "invalid"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "Expected no transitions for 'invalid' based on patterns",
                    "description": "Test with an invalid string input to ensure no transitions occur."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "mixed123"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": "new HashSet<NameState>()"
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": true
                        }
                    ],
                    "expected": "Expected transitions based on mixed input with numeric flag set",
                    "description": "Test with mixed string input and numeric flag to verify proper handling of transitions."
                }
            ]
        }
    ]
}