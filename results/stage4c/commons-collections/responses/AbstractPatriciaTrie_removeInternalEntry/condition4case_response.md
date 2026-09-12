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
      "expected": "IllegalArgumentException",
      "description": "Test case to verify that an IllegalArgumentException is thrown when attempting to remove the root entry."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "nonInternalNode"
        }
      ],
      "expected": "IllegalArgumentException",
      "description": "Test case to verify that an IllegalArgumentException is thrown when attempting to remove a non-internal node."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithSelfLoopPredecessor"
        }
      ],
      "expected": "Verify that the predecessor of the node is set to its parent.",
      "description": "Test case to verify that if the predecessor of the internal node loops to itself and is not its parent, it is set to its parent."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithLeftChildAsSelf"
        }
      ],
      "expected": "Verify that the parent's left child is updated correctly.",
      "description": "Test case to verify that if the parent's left child is the node, it is updated to the node's right child."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithRightChildAsSelf"
        }
      ],
      "expected": "Verify that the parent's right child is updated correctly.",
      "description": "Test case to verify that if the parent's right child is the node, it is updated to the node's left child."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithLeftChildParentAsNode"
        }
      ],
      "expected": "Verify that the left child's parent is updated to the predecessor.",
      "description": "Test case to verify that if the left child's parent is the node, it is updated to the predecessor."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithRightChildParentAsNode"
        }
      ],
      "expected": "Verify that the right child's parent is updated to the predecessor.",
      "description": "Test case to verify that if the right child's parent is the node, it is updated to the predecessor."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithValidUplinkLeft"
        }
      ],
      "expected": "Verify that the left child's predecessor is updated to the predecessor.",
      "description": "Test case to verify that if the left child has a valid uplink, its predecessor is updated to the predecessor."
    },
    {
      "input": [
        {
          "parameter": "h",
          "value": "internalNodeWithValidUplinkRight"
        }
      ],
      "expected": "Verify that the right child's predecessor is updated to the predecessor.",
      "description": "Test case to verify that if the right child has a valid uplink, its predecessor is updated to the predecessor."
    }
  ]
}