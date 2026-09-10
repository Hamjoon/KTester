{
    "testCases": [
        {
            "group": "doRedBlackDelete with both children",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with both left and right children"
                        }
                    ],
                    "expected": "Node is replaced by its next greater node",
                    "description": "Test case where the deleted node has both left and right children, ensuring the correct node is swapped."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with only left child",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with only left child"
                        }
                    ],
                    "expected": "Replacement node is set properly and parent-child relationships are maintained",
                    "description": "Test case where the deleted node has only a left child, ensuring proper reassignment of parent and child nodes."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with only right child",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with only right child"
                        }
                    ],
                    "expected": "Replacement node is set properly and parent-child relationships are maintained",
                    "description": "Test case where the deleted node has only a right child, ensuring proper reassignment of parent and child nodes."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with no children",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Node with no children"
                        }
                    ],
                    "expected": "Parent node's child reference is set to null",
                    "description": "Test case where the deleted node has no children, ensuring the parent node correctly removes the reference to the deleted node."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with root node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Root node with children"
                        }
                    ],
                    "expected": "Root is correctly replaced with the replacement node",
                    "description": "Test case where the deleted node is the root node, ensuring the root reference is updated correctly."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with empty tree",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Null node"
                        }
                    ],
                    "expected": "No changes made to the tree",
                    "description": "Test case where the deleted node is null, ensuring the method handles empty trees without errors."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with black node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with children"
                        }
                    ],
                    "expected": "Red-black properties are maintained after deletion",
                    "description": "Test case where the deleted node is black and has children, ensuring the red-black properties are preserved after deletion."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with red node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Red node with children"
                        }
                    ],
                    "expected": "Red-black properties are maintained after deletion",
                    "description": "Test case where the deleted node is red and has children, ensuring the red-black properties are preserved after deletion."
                }
            ]
        }
    ]
}