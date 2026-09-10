{
    "test_cases": [
        {
            "group": "updateMatchReferences",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "PREFIX"
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the behavior when the pattern type is PREFIX. The method should execute without any changes to the state."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "EQUALS_IGNORE_CASE"
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the behavior when the pattern type is EQUALS_IGNORE_CASE. The method should execute without any changes to the state."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "WILDCARD"
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the behavior when the pattern type is WILDCARD. The method should execute without any changes to the state."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "ANYTHING_BUT",
                                    "isNumeric": false
                                }
                            }
                        }
                    ],
                    "expected": "The next name state should be removed from anythingButs and hasNumeric should not change.",
                    "description": "Test the behavior when the pattern type is ANYTHING_BUT but isNumeric is false. The next name state should be removed from anythingButs."
                },
                {
                    "input": [
                        {
                            "parameter": "match",
                            "value": {
                                "pattern": {
                                    "type": "ANYTHING_BUT_IGNORE_CASE",
                                    "isNumeric": true
                                }
                            }
                        }
                    ],
                    "expected": "The next name state should be removed from anythingButs.",
                    "description": "Test the behavior when the pattern type is ANYTHING_BUT_IGNORE_CASE with isNumeric true. The next name state should be removed from anythingButs."
                }
            ]
        }
    ]
}