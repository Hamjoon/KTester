{
    "testCases": [
        {
            "group": "higherEntry - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "When the input key is null, the method should return null."
                    },
                    "description": "Test case for null key input."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "non-existing-key"
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "When there are no higher entries than the non-existing key, the method should return null."
                    },
                    "description": "Test case for a key that does not exist in the trie."
                }
            ]
        },
        {
            "group": "higherEntry - Root Entry Conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": ""
                        }
                    ],
                    "expected": {
                        "result": "firstEntry()",
                        "description": "If the root is empty and the key is empty, return the first entry."
                    },
                    "description": "Test case for empty key when root is empty."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "some-key"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(root)",
                        "description": "If the root is not empty and there are more entries, return the next entry."
                    },
                    "description": "Test case for a non-empty root with more entries."
                }
            ]
        },
        {
            "group": "higherEntry - Valid Bit Index Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "valid-key"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(found)",
                        "description": "If the key is found and is less than the found key, return the next entry."
                    },
                    "description": "Test case for a key that is found and is lower than the found key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "another-valid-key"
                        }
                    ],
                    "expected": {
                        "result": "ceil",
                        "description": "If the key is valid and the bit index is valid, return the ceiling entry."
                    },
                    "description": "Test case for a valid key with a valid bit index."
                }
            ]
        },
        {
            "group": "higherEntry - Invalid Bit Index Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-invalid-bit-index"
                        }
                    ],
                    "expected": {
                        "result": "IllegalStateException",
                        "description": "If the bit index is invalid, an IllegalStateException should be thrown."
                    },
                    "description": "Test case for a key that results in an invalid bit index."
                }
            ]
        },
        {
            "group": "higherEntry - Null Bit Key Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-null-bit"
                        }
                    ],
                    "expected": {
                        "result": "firstEntry()",
                        "description": "If the bit index is null and the root is not empty, return the first entry."
                    },
                    "description": "Test case for a key that results in a null bit index when the root is not empty."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-null-bit-2"
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "If the root is empty and there are no entries, return null."
                    },
                    "description": "Test case for a key that results in a null bit index when the root is empty."
                }
            ]
        },
        {
            "group": "higherEntry - Equal Bit Key Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-equal-bit"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(found)",
                        "description": "If the bit index is equal, return the next entry."
                    },
                    "description": "Test case for a key that results in an equal bit index."
                }
            ]
        }
    ]
}