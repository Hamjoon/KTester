{
    "testCases": [
        {
            "group": "higherEntry - Additional Valid Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-higher-entry"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(found)",
                        "description": "If the key is found and is less than the next higher entry, return the next entry."
                    },
                    "description": "Test case for a key that is found and has a higher entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-at-last-entry"
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "If the key is the last entry, there should be no higher entry, so return null."
                    },
                    "description": "Test case for a key that is the last entry in the trie."
                }
            ]
        },
        {
            "group": "higherEntry - Edge Cases with Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "!@#$%^&*()"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(found)",
                        "description": "If the key is a string with special characters and there is a higher entry, return that entry."
                    },
                    "description": "Test case for a key with special characters that has a higher entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": " "
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "If the key is a single space and there are no higher entries, return null."
                    },
                    "description": "Test case for a key that is a single space."
                }
            ]
        },
        {
            "group": "higherEntry - Performance Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "large-key"
                        }
                    ],
                    "expected": {
                        "result": "nextEntry(found)",
                        "description": "For a very large key, if it exists and has a higher entry, return that entry."
                    },
                    "description": "Test case for a very large key that exists in the trie."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key-with-longer-length-than-others"
                        }
                    ],
                    "expected": {
                        "result": null,
                        "description": "If the key is longer than any existing keys and there are no higher entries, return null."
                    },
                    "description": "Test case for a key that is longer than any other keys in the trie."
                }
            ]
        }
    ]
}