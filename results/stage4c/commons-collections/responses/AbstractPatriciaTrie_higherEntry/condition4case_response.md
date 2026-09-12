{
    "group": "AbstractPatriciaTrie.higherEntry",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "null"
                }
            ],
            "expected": "null",
            "description": "Test case where the input key is null, expecting null since no higher entry can be found."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey"
                }
            ],
            "expected": "nextEntry of found",
            "description": "Test case where the input key exists in the trie, expecting the next entry after the found key."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistentKey"
                }
            ],
            "expected": "firstEntry",
            "description": "Test case where the input key does not exist in the trie, and the root is empty, expecting the first entry in the trie."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nonExistentKeyWithValidBitIndex"
                }
            ],
            "expected": "nextEntry of added",
            "description": "Test case where the input key does not exist and has a valid bit index, expecting the next entry of a temporarily added entry."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyWithNullBitIndex"
                }
            ],
            "expected": "firstEntry or nextEntry of firstEntry",
            "description": "Test case where the input key results in a null bit index, expecting the first entry or the next entry of the first entry."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyWithEqualBitIndex"
                }
            ],
            "expected": "nextEntry of found",
            "description": "Test case where the input key results in an equal bit index, expecting the next entry of the found key."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "invalidKey"
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test case where the input key results in an invalid lookup, expecting an IllegalStateException to be thrown."
        }
    ]
}