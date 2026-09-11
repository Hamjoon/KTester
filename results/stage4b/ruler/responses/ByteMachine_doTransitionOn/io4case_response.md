{
    "testCases": [
        {
            "group": "doTransitionOn",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "abc"
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
                    "expected": "transitionTo should contain expected NameState based on the matches found for 'abc'",
                    "description": "Test with a normal string input 'abc' where no numeric field value is expected, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "456"
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
                    "expected": "transitionTo should contain expected NameState based on numeric matches for '456'",
                    "description": "Test with a numeric string input '456' where fieldValueIsNumeric is true, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "0"
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
                    "expected": "transitionTo should contain expected NameState for numeric input '0'",
                    "description": "Test with a numeric string input '0' where fieldValueIsNumeric is true, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test123"
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
                    "expected": "transitionTo should contain expected NameState based on matches found for 'test123'",
                    "description": "Test with a mixed alphanumeric string input 'test123' where fieldValueIsNumeric is false, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "99999"
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
                    "expected": "transitionTo should contain expected NameState based on numeric matches for '99999'",
                    "description": "Test with a large numeric string input '99999' where fieldValueIsNumeric is true, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": " "
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
                    "expected": "transitionTo should not contain any NameState as the input is a whitespace",
                    "description": "Test with a whitespace string input to verify that no NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "test!"
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
                    "expected": "transitionTo should contain expected NameState based on matches found for 'test!'",
                    "description": "Test with a string input containing special characters 'test!' where fieldValueIsNumeric is false, ensuring that the appropriate NameState is added to transitionTo."
                },
                {
                    "input": [
                        {
                            "parameter": "valString",
                            "value": "NaN"
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
                    "expected": "transitionTo should not contain any NameState as 'NaN' is not a valid numeric value",
                    "description": "Test with a string input 'NaN' where fieldValueIsNumeric is true, ensuring that no NameState is added to transitionTo."
                }
            ]
        }
    ]
}