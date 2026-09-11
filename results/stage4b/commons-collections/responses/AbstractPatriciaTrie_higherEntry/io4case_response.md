{
    "testCases": [
        {
            "group": "higherEntry - Root is empty with size 1",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": null,
                    "description": "Test when root is empty but size is 1. Should return null as there are no higher entries."
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
                            "value": "keyWithNoHigherEntry"
                        }
                    ],
                    "expected": null,
                    "description": "Test when a key is provided that does not have any higher entry in the trie. Should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyAtMaxLength"
                        }
                    ],
                    "expected": "nextEntry(found)",
                    "description": "Test when a key at the maximum valid length is provided. Should return the next entry if it exists."
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
                            "value": "keyWithNegativeBitIndex"
                        }
                    ],
                    "expected": null,
                    "description": "Test when a key with a negative bit index is provided. Should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithBitIndexGreaterThanMax"
                        }
                    ],
                    "expected": "nextEntry(added)",
                    "description": "Test when a key with a bit index greater than the maximum is added. Should return the next entry after adding the entry."
                }
            ]
        },
        {
            "group": "higherEntry - Edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": null
                        }
                    ],
                    "expected": "firstEntry()",
                    "description": "Test when the key is null. Should return the first entry."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithSpecialCharacters!@#"
                        }
                    ],
                    "expected": "nextEntry(found)",
                    "description": "Test when a key with special characters is provided. Should return the next entry if it exists."
                }
            ]
        }
    ]
}