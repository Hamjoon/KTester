{
  "group": "doRedBlackDelete method test cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Node with both left and right children"
        }
      ],
      "expected": "Node swapped with next greater node and tree properties maintained",
      "description": "Test deletion when the node to be deleted has both left and right children."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Node with only left child"
        }
      ],
      "expected": "Left child replaces the deleted node",
      "description": "Test deletion when the node to be deleted has only a left child."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Node with only right child"
        }
      ],
      "expected": "Right child replaces the deleted node",
      "description": "Test deletion when the node to be deleted has only a right child."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Leaf node (no children)"
        }
      ],
      "expected": "Leaf node removed and tree properties maintained",
      "description": "Test deletion when the node to be deleted is a leaf node."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Root node with no children"
        }
      ],
      "expected": "Tree becomes empty",
      "description": "Test deletion when the root node is the only node in the tree."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Black node with no children"
        }
      ],
      "expected": "Red-black fixup performed to maintain tree properties",
      "description": "Test deletion when the node to be deleted is a black node with no children."
    },
    {
      "input": [
        {
          "parameter": "deletedNode",
          "value": "Red node with no children"
        }
      ],
      "expected": "Node simply removed without needing fixup",
      "description": "Test deletion when the node to be deleted is a red node with no children."
    }
  ]
}