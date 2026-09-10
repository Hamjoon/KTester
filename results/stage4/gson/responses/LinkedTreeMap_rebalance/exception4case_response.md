{
    "output": [
        {
            "group": "rebalance - Height Difference of Two Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 2, right height 4"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node is rotated left",
                    "description": "Test case for a node with a height difference of two where no insert occurs, triggering a left rotation."
                }
            ]
        },
        {
            "group": "rebalance - Height Difference of Negative Two Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height 4, right height 2"
                        },
                        {
                            "parameter": "insert",
                            "value": "false"
                        }
                    ],
                    "expected": "Node is rotated right",
                    "description": "Test case for a node with a height difference of negative two where no insert occurs, triggering a right rotation."
                }
            ]
        },
        {
            "group": "rebalance - No Rotation on Insert with Height Equal and Insert True",
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
                    "description": "Test case where the heights of left and right children are equal, and the insert causes a height update but no rotation."
                }
            ]
        }
    ]
}