{
    "group": "removeInternalEntry",
    "cases": [
        {
            "input": [
                {
                    "parameter": "h",
                    "value": "internalNodeWithPredecessorAsLeftChild"
                }
            ],
            "expected": "Verify that the predecessor is updated correctly when it is the left child.",
            "description": "Test case to verify that if the predecessor of the internal node is its left child, the predecessor is updated correctly."
        },
        {
            "input": [
                {
                    "parameter": "h",
                    "value": "internalNodeWithPredecessorAsRightChild"
                }
            ],
            "expected": "Verify that the predecessor is updated correctly when it is the right child.",
            "description": "Test case to verify that if the predecessor of the internal node is its right child, the predecessor is updated correctly."
        },
        {
            "input": [
                {
                    "parameter": "h",
                    "value": "internalNodeWithBothChildrenLoopback"
                }
            ],
            "expected": "Verify that the node is removed correctly when both children are loopbacks.",
            "description": "Test case to verify the behavior when attempting to remove an internal node where both children are loopbacks."
        },
        {
            "input": [
                {
                    "parameter": "h",
                    "value": "internalNodeWithParentAsSelfLoop"
                }
            ],
            "expected": "Verify that the parent is updated correctly when it loops to itself.",
            "description": "Test case to verify the behavior when the node's parent loops to itself."
        },
        {
            "input": [
                {
                    "parameter": "h",
                    "value": "internalNodeWithParentAsPredecessor"
                }
            ],
            "expected": "Verify that the parent remains the predecessor when it is the predecessor.",
            "description": "Test case to verify that if the node's parent is also the predecessor, it remains as such after removal."
        }
    ]
}