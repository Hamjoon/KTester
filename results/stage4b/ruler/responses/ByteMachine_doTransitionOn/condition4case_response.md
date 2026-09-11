{
    "testCases": [
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
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "transitionTo should contain expected NameState based on the matches found",
                    "description": "Test with a normal string input where no numeric field value is expected, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "123"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": true
                        }
                    ],
                    "expected": "transitionTo should contain expected NameState based on numeric matches",
                    "description": "Test with a numeric string input where fieldValueIsNumeric is true, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": true
                        }
                    ],
                    "expected": "transitionTo should not contain any NameState as 'test' is not numeric",
                    "description": "Test with a non-numeric string input where fieldValueIsNumeric is true, ensuring that no NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "transitionTo should contain expected NameState based on the matches found",
                    "description": "Test with a normal string input where fieldValueIsNumeric is false, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": ""
                        },
                        {
                            "parameter": "transitionTo",
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "transitionTo should not contain any NameState as the input is empty",
                    "description": "Test with an empty string input to verify that no NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test"
                        },
                        {
                            "parameter": "transitionTo",
                            "value": []
                        },
                        {
                            "parameter": "fieldValueIsNumeric",
                            "value": false
                        }
                    ],
                    "expected": "transitionTo should contain expected NameState based on the matches found",
                    "description": "Test with a normal string input where fieldValueIsNumeric is false, ensuring that the appropriate NameState is added to transitionTo."
                }
            ]
        }
    ]
}