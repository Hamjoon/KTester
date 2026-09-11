{
    "test_cases": [
        {
            "group": "getValueType - valid cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "^"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for character '^', which is not defined in the method and should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": " "
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for a space character, which is not defined in the method and should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "Z"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an uppercase character 'Z', which is not defined in the method and should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "0"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for a numeric character '0', which is not defined in the method and should return null."
                }
            ]
        },
        {
            "group": "getValueType - invalid cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "?"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '?', which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "}"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '}', which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "&"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '&', which should return null."
                }
            ]
        }
    ]
}