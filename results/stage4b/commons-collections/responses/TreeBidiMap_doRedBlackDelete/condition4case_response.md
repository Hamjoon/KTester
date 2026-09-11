{
    "testCases": [
        {
            "group": "doRedBlackDelete - Node with both children",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with both left and right children"
                        }
                    ],
                    "expected": "Node is replaced with next greater node and tree structure is maintained",
                    "description": "Test case where the deleted node has both left and right children to ensure the correct node is swapped."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Node with left child only",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with left child only"
                        }
                    ],
                    "expected": "Left child becomes the new parent node and tree structure is maintained",
                    "description": "Test case where the deleted node has only a left child to verify proper reassignment of parent."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Node with right child only",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with right child only"
                        }
                    ],
                    "expected": "Right child becomes the new parent node and tree structure is maintained",
                    "description": "Test case where the deleted node has only a right child to verify proper reassignment of parent."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Node with no children",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with no children"
                        }
                    ],
                    "expected": "Node is removed and tree structure is updated, if node is black, fixup is called",
                    "description": "Test case where the deleted node has no children to ensure proper deletion and potential fixup."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Root node deletion",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Root node"
                        }
                    ],
                    "expected": "Tree becomes empty after root node deletion",
                    "description": "Test case where the root node is deleted to verify that the tree properly updates to an empty state."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Node is black",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with no children"
                        }
                    ],
                    "expected": "Fixup method is called to maintain red-black tree properties",
                    "description": "Test case where a black node is deleted to ensure that the fixup process is triggered."
                }
            ]
        },
        {
            "group": "doRedBlackDelete - Node is red",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Red node with no children"
                        }
                    ],
                    "expected": "Node is removed without needing fixup",
                    "description": "Test case where a red node is deleted to verify that no fixup is needed."
                }
            ]
        }
    ]
}