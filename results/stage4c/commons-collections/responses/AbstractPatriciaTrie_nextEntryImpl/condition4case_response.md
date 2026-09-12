{
  "group": "nextEntryImpl Test Cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "start",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        },
        {
          "parameter": "previous",
          "value": "null"
        },
        {
          "parameter": "tree",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with only root node, expecting null as there are no other nodes."
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
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with a previous node not equal to the start node's predecessor, expecting null as the tree has only one node."
    },
    {
      "input": [
        {
          "parameter": "start",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        },
        {
          "parameter": "previous",
          "value": "null"
        },
        {
          "parameter": "tree",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with a node that has no children, expecting null as there are no other nodes."
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
          "value": "new TrieEntry<>(\"a\", 1, 0)"
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
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with a node having right child as null, expecting null as no traversal is possible."
    },
    {
      "input": [
        {
          "parameter": "start",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        },
        {
          "parameter": "previous",
          "value": "null"
        },
        {
          "parameter": "tree",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with a node that is the root and no right child, expecting null."
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
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test with node having no valid uplink on the left, expecting traversal to right and null return."
    },
    {
      "input": [
        {
          "parameter": "start",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        },
        {
          "parameter": "previous",
          "value": "null"
        },
        {
          "parameter": "tree",
          "value": "new TrieEntry<>(\"a\", 1, 0)"
        }
      ],
      "expected": "null",
      "description": "Test where current node is empty, expecting null as no further traversal is possible."
    }
  ]
}