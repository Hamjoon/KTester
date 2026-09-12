{
  "group": "put(K, V) additional method tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "key",
          "value": "boundaryKey"
        },
        {
          "parameter": "value",
          "value": "boundaryValue"
        }
      ],
      "expected": "previousValue or null",
      "description": "Test when the key is at the boundary of valid bit lengths. The key-value should be stored correctly, either incrementing the size or modification count."
    },
    {
      "input": [
        {
          "parameter": "key",
          "value": "nonExistentKey"
        },
        {
          "parameter": "value",
          "value": "initialValue"
        }
      ],
      "expected": "null",
      "description": "Test inserting a completely new key-value pair that does not share any prefix with existing keys. A new TrieEntry should be created and added, and the size should be incremented."
    },
    {
      "input": [
        {
          "parameter": "key",
          "value": "keyWithAllZeroBits"
        },
        {
          "parameter": "value",
          "value": "zeroBitsValue"
        }
      ],
      "expected": "previousValue or null",
      "description": "Test when the key has all zero bits. The key-value should be stored in the root node. If the root is empty, the size is incremented; otherwise, the modification count is incremented."
    },
    {
      "input": [
        {
          "parameter": "key",
          "value": "keyWithAllOneBits"
        },
        {
          "parameter": "value",
          "value": "oneBitsValue"
        }
      ],
      "expected": "null",
      "description": "Test when the key has all one bits, ensuring it is stored correctly in the trie structure, creating a new TrieEntry and incrementing the size."
    }
  ]
}