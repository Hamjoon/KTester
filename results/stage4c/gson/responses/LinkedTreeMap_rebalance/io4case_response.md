{
    "group": "rebalance",
    "cases": [
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with delta 2 and leftDelta -1"
                },
                {
                    "parameter": "insert",
                    "value": false
                }
            ],
            "expected": "rotateLeft is called on left child, then rotateRight on node",
            "description": "Test case for AVL left-right rotation when delta is 2 and leftDelta is -1 during non-insertion."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with delta -2 and rightDelta 1"
                },
                {
                    "parameter": "insert",
                    "value": false
                }
            ],
            "expected": "rotateRight is called on right child, then rotateLeft on node",
            "description": "Test case for AVL right-left rotation when delta is -2 and rightDelta is 1 during non-insertion."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with delta 1"
                },
                {
                    "parameter": "insert",
                    "value": true
                }
            ],
            "expected": "Node height is updated",
            "description": "Test case when delta is 1 during insertion, node height is updated."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with delta -1"
                },
                {
                    "parameter": "insert",
                    "value": true
                }
            ],
            "expected": "Node height is updated",
            "description": "Test case when delta is -1 during insertion, node height is updated."
        }
    ]
}