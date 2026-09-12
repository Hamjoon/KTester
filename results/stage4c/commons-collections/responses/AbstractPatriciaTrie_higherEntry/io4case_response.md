{
    "group": "AbstractPatriciaTrie.higherEntry - Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "emptyTrie"
                }
            ],
            "expected": "null",
            "description": "Test case where the trie is empty and any key is provided, expecting null since there are no entries in the trie."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyAtBoundary"
                }
            ],
            "expected": "null",
            "description": "Test case where the input key is the largest key in the trie, expecting null since there is no higher entry."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyWithNegativeBitIndex"
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test case where the input key results in a negative bit index, expecting an IllegalStateException to be thrown due to invalid lookup."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyWithMaxBitIndex"
                }
            ],
            "expected": "nextEntry of found",
            "description": "Test case where the input key has the maximum possible bit index, expecting the next entry after the found key."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "keyWithSingleBitDifference"
                }
            ],
            "expected": "nextEntry of found",
            "description": "Test case where the input key differs by only one bit from an existing key, expecting the next entry after the found key."
        }
    ]
}