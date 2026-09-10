{
    "testCases": [
        {
            "group": "doRedBlackDelete with both children and black node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with both left and right children"
                        }
                    ],
                    "expected": "Node is replaced by its next greater node and red-black properties are maintained",
                    "description": "Test case where the deleted node is black and has both left and right children, ensuring the correct node is swapped and red-black properties are preserved."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with both children and red node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Red node with both left and right children"
                        }
                    ],
                    "expected": "Node is replaced by its next greater node and red-black properties are maintained",
                    "description": "Test case where the deleted node is red and has both left and right children, ensuring the correct node is swapped and red-black properties are preserved."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with only left child and black node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with only left child"
                        }
                    ],
                    "expected": "Replacement node is set properly and parent-child relationships are maintained, red-black properties are preserved",
                    "description": "Test case where the deleted node is black and has only a left child, ensuring proper reassignment of parent and child nodes while maintaining red-black properties."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with only right child and black node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with only right child"
                        }
                    ],
                    "expected": "Replacement node is set properly and parent-child relationships are maintained, red-black properties are preserved",
                    "description": "Test case where the deleted node is black and has only a right child, ensuring proper reassignment of parent and child nodes while maintaining red-black properties."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with no children and black node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Black node with no children"
                        }
                    ],
                    "expected": "Parent node's child reference is set to null and red-black properties are maintained",
                    "description": "Test case where the deleted node is black and has no children, ensuring the parent node correctly removes the reference to the deleted node while maintaining red-black properties."
                }
            ]
        },
        {
            "group": "doRedBlackDelete with no children and red node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "deletedNode",
                            "value": "Red node with no children"
                        }
                    ],
                    "expected": "Parent node's child reference is set to null and red-black properties are maintained",
                    "description": "Test case where the deleted node is red and has no children, ensuring the parent node correctly removes the reference to the deleted node while maintaining red-black properties."
                }
            ]
        }
    ]
}