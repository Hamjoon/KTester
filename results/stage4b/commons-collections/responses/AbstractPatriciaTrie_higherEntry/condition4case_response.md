{
    "testCases": [
        {
            "group": "higherEntry - Root is empty",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test when root is empty and key is null. Should return null."
                }
            ]
        },
        {
            "group": "higherEntry - Root is not empty",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": "nextEntry(root)",
                    "description": "Test when root is not empty and key is provided. Should return the next entry after the root."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "anotherKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test when root is not empty and key is not greater than any existing keys. Should return null."
                }
            ]
        },
        {
            "group": "higherEntry - Key length checks",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": ""
                        }
                    ],
                    "expected": "firstEntry()",
                    "description": "Test when the key length is 0. Should return the first entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "validKey"
                        }
                    ],
                    "expected": "nextEntry(found)",
                    "description": "Test when a valid key is given that is found in the trie. Should return the next entry."
                }
            ]
        },
        {
            "group": "higherEntry - Bit index checks",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithValidBitIndex"
                        }
                    ],
                    "expected": "nextEntry(added)",
                    "description": "Test when a key with a valid bit index is added. Should return the next entry after adding the entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithNullBitIndex"
                        }
                    ],
                    "expected": "firstEntry()",
                    "description": "Test when a key with a null bit index is provided. Should return the first entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEqualBitIndex"
                        }
                    ],
                    "expected": "nextEntry(found)",
                    "description": "Test when a key that has an equal bit index to an existing key is provided. Should return the next entry."
                }
            ]
        },
        {
            "group": "higherEntry - IllegalStateException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "invalidKey"
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "Test when a key leads to an invalid lookup state. Should throw an IllegalStateException."
                }
            ]
        }
    ]
}