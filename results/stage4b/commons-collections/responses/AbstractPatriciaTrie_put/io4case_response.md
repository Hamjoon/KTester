{
    "test cases": [
        {
            "group": "put method - key with non-empty value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonEmptyKey"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForNonEmptyKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a non-empty key is added with a non-empty value. Expecting the method to add the entry and return null."
                }
            ]
        },
        {
            "group": "put method - key with duplicate value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "duplicateKey"
                        },
                        {
                            "parameter": "value",
                            "value": "duplicateValue"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a key that already exists is added with the same value. Expecting the method to return null."
                }
            ]
        },
        {
            "group": "put method - key with special characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "!@#$%^&*()"
                        },
                        {
                            "parameter": "value",
                            "value": "valueWithSpecialChars"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a key with special characters is added. Expecting the method to add the entry and return null."
                }
            ]
        },
        {
            "group": "put method - key with long string",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "aVeryLongKeyStringThatExceedsNormalLength"
                        },
                        {
                            "parameter": "value",
                            "value": "longValue"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a very long key string is added. Expecting the method to add the entry and return null."
                }
            ]
        },
        {
            "group": "put method - key found and updated with a new value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "existingKey"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValueAgain"
                        }
                    ],
                    "expected": "updatedValueAgain",
                    "description": "Test case where an existing key is updated with a new value. Expecting the method to update the value and return the new value."
                }
            ]
        },
        {
            "group": "put method - key with empty string value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEmptyValue"
                        },
                        {
                            "parameter": "value",
                            "value": ""
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where a key is added with an empty string value. Expecting the method to add the entry and return null."
                }
            ]
        }
    ]
}