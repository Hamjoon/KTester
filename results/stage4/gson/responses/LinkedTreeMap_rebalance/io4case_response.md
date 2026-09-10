{
    "testCases": [
        {
            "group": "rebalance - Insert with Left-Heavy Node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 3, right height 2"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node height is updated, no rotation occurs",
                    "description": "Test case for a left-heavy node where the insert causes a height update but no rotation is necessary."
                }
            ]
        },
        {
            "group": "rebalance - Insert with Right-Heavy Node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 2, right height 3"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node height is updated, no rotation occurs",
                    "description": "Test case for a right-heavy node where the insert causes a height update but no rotation is necessary."
                }
            ]
        },
        {
            "group": "rebalance - Insert with Balanced Node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 2, right height 2"
                        },
                        {
                            "parameter": "insert",
                            "value": "true"
                        }
                    ],
                    "expected": "Node height is updated, no rotation occurs",
                    "description": "Test case for a balanced node where the insert causes a height update but no rotation is necessary."
                }
            ]
        },
        {
            "group": "rebalance - Remove from Right-Right Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 1, right height 3"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node is rotated left",
                    "description": "Test case for the right-right case where the tree is unbalanced with a right-heavy node and an element is removed."
                }
            ]
        },
        {
            "group": "rebalance - Remove from Left-Left Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 3, right height 1"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node is rotated right",
                    "description": "Test case for the left-left case where the tree is unbalanced with a left-heavy node and an element is removed."
                }
            ]
        },
        {
            "group": "rebalance - No Rotation on Insert with Height Equal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with equal left and right height"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node height remains unchanged, no rotation occurs",
                    "description": "Test case where the heights of left and right children are equal, and the insert flag is false, ensuring no changes occur."
                }
            ]
        }
    ]
}