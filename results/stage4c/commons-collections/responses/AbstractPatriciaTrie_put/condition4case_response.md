{
    "group": "put(K, V) method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "key",
                    "value": null
                },
                {
                    "parameter": "value",
                    "value": "someValue"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when the key is null. The method should throw a NullPointerException due to Objects.requireNonNull(key)."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "zeroLengthKey"
                },
                {
                    "parameter": "value",
                    "value": "value1"
                }
            ],
            "expected": "previousValue or null",
            "description": "Test when the key length in bits is zero. The key-value should be stored in the root node. If the root is empty, the size is incremented; otherwise, the modification count is incremented."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "existingKey"
                },
                {
                    "parameter": "value",
                    "value": "newValue"
                }
            ],
            "expected": "previousValue",
            "description": "Test when the key already exists in the trie. The value should be updated, and the modification count should be incremented."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "newKey"
                },
                {
                    "parameter": "value",
                    "value": "newValue"
                }
            ],
            "expected": "null",
            "description": "Test when inserting a new key-value pair. A new TrieEntry should be created and added, and the size should be incremented."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "nullBitKey"
                },
                {
                    "parameter": "value",
                    "value": "value2"
                }
            ],
            "expected": "previousValue or null",
            "description": "Test when the key has a null bit index. The key-value should be stored in the root node. If the root is empty, the size is incremented; otherwise, the modification count is incremented."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "equalBitKey"
                },
                {
                    "parameter": "value",
                    "value": "value3"
                }
            ],
            "expected": "previousValue",
            "description": "Test when the key has an equal bit index and is not the root. The value should be updated, and the modification count should be incremented."
        },
        {
            "input": [
                {
                    "parameter": "key",
                    "value": "invalidKey"
                },
                {
                    "parameter": "value",
                    "value": "value4"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test when the bit index is out of bounds, which should throw an IllegalArgumentException."
        }
    ]
}