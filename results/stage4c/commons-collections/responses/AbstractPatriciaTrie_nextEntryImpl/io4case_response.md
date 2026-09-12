{
    "group": "nextEntryImpl Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                },
                {
                    "parameter": "previous",
                    "value": "null"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "null",
            "description": "Test with root node only, having no children, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "null",
            "description": "Test with previous node being the same as start node, expecting null as there's no valid next entry."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"b\", 2, 1)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "null",
            "description": "Test with a node having no valid uplink on the left or right, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"b\", 2, 1)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "null",
            "description": "Test with node having a right child that is itself, expecting null as no traversal is possible."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"c\", 3, 2)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "null",
            "description": "Test where current node is an internal node with no valid children, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"b\", 2, 1)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "new TrieEntry<>(\"b\", 2, 1)",
            "description": "Test with a valid uplink on the left, expecting the left child to be returned."
        },
        {
            "input": [
                {
                    "parameter": "start",
                    "value": "new TrieEntry<>(\"a\", 1, 0)"
                },
                {
                    "parameter": "previous",
                    "value": "new TrieEntry<>(\"b\", 2, 1)"
                },
                {
                    "parameter": "tree",
                    "value": "new TrieEntry<>(\"root\", 1, -1)"
                }
            ],
            "expected": "new TrieEntry<>(\"c\", 3, 2)",
            "description": "Test with a valid uplink on the right, expecting the right child to be returned."
        }
    ]
}