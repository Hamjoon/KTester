{
    "testCases": [
        {
            "group": "rebalance - Right-Right Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 1, right height 3"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node is rotated left",
                    "description": "Test case for the right-right case where the tree is unbalanced with a right-heavy node."
                }
            ]
        },
        {
            "group": "rebalance - Right-Left Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 1, right height 2"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node is rotated right followed by a left rotation",
                    "description": "Test case for the right-left case where the tree requires two rotations to balance."
                }
            ]
        },
        {
            "group": "rebalance - Left-Left Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 3, right height 1"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node is rotated right",
                    "description": "Test case for the left-left case where the tree is unbalanced with a left-heavy node."
                }
            ]
        },
        {
            "group": "rebalance - Left-Right Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 2, right height 1"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node is rotated left followed by a right rotation",
                    "description": "Test case for the left-right case where the tree requires two rotations to balance."
                }
            ]
        },
        {
            "group": "rebalance - Height Equal Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with equal left and right height"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node height is updated, no rotation occurs",
                    "description": "Test case where the heights of left and right children are equal, and the insert causes a height update."
                }
            ]
        },
        {
            "group": "rebalance - Height Difference of One Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 2, right height 1"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node height is updated, no rotation occurs",
                    "description": "Test case where the height difference is one, and the insert flag is false."
                }
            ]
        },
        {
            "group": "rebalance - No Change Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 1, right height 1"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node height remains unchanged, no rotation occurs",
                    "description": "Test case where the tree is balanced and the insert flag is false, ensuring no changes occur."
                }
            ]
        }
    ]
}