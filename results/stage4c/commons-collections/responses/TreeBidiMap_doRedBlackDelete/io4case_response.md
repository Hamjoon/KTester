{
    "group": "doRedBlackDelete method additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "deletedNode",
                    "value": "Node where the parent is null and replacement is not null"
                }
            ],
            "expected": "Replacement node becomes the new root",
            "description": "Test deletion when the node to be deleted is the root node with one child."
        },
        {
            "input": [
                {
                    "parameter": "deletedNode",
                    "value": "Node where the replacement node is red"
                }
            ],
            "expected": "Replacement node takes place of deleted node without needing fixup",
            "description": "Test deletion when the replacement node is red, ensuring no fixup is required."
        },
        {
            "input": [
                {
                    "parameter": "deletedNode",
                    "value": "Node with null parent and null replacement"
                }
            ],
            "expected": "Tree remains unchanged",
            "description": "Test deletion when the node to be deleted is null, ensuring no action is taken."
        },
        {
            "input": [
                {
                    "parameter": "deletedNode",
                    "value": "Node with black parent and red sibling"
                }
            ],
            "expected": "Sibling's color adjusted and tree properties maintained",
            "description": "Test deletion when the sibling of the node to be deleted is red, ensuring proper color adjustments."
        },
        {
            "input": [
                {
                    "parameter": "deletedNode",
                    "value": "Node with black parent and black sibling"
                }
            ],
            "expected": "Red-black fixup performed to maintain tree properties",
            "description": "Test deletion when both the parent and sibling of the node to be deleted are black."
        }
    ]
}