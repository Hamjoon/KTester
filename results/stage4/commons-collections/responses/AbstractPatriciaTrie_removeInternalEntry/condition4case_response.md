{
    "test cases": [
        {
            "group": "removeInternalEntry",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "h",
                            "value": "root"
                        }
                    ],
                    "expected": "IllegalArgumentException with message 'Cannot delete root Entry!'",
                    "description": "Test removing the root entry, which should throw an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "h",
                            "value": "TrieEntry with isInternalNode() returning false"
                        }
                    ],
                    "expected": "IllegalArgumentException with message '<TrieEntry> is not an internal Entry!'",
                    "description": "Test removing an entry that is not an internal node, expecting an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "h",
                            "value": "valid internal TrieEntry"
                        }
                    ],
                    "expected": "TrieEntry has its predecessor and parent updated correctly",
                    "description": "Test removing a valid internal TrieEntry and check if its predecessor and parent are updated correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "h",
                            "value": "valid internal TrieEntry with looping predecessor"
                        }
                    ],
                    "expected": "TrieEntry's predecessor should point to its parent after removal",
                    "description": "Test removing an internal TrieEntry that has itself as its predecessor, ensuring the predecessor points to its parent afterwards."
                },
                {
                    "input": [
                        {
                            "parameter": "h",
                            "value": "valid internal TrieEntry with children"
                        }
                    ],
                    "expected": "Children of the removed entry should have their parent set to the correct new parent",
                    "description": "Test removing an internal TrieEntry that has children and verify the children have their parent updated correctly."
                }
            ]
        }
    ]
}