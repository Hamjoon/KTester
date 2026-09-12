{
    "group": "rebalance",
    "cases": [
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with null right child when delta is -2"
                },
                {
                    "parameter": "insert",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case when right child is null and delta is -2, which should trigger a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with null left child when delta is 2"
                },
                {
                    "parameter": "insert",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case when left child is null and delta is 2, which should trigger a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with null right child when delta is -2"
                },
                {
                    "parameter": "insert",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case when right child is null and delta is -2 during non-insertion, which should trigger a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "unbalanced",
                    "value": "Node with null left child when delta is 2"
                },
                {
                    "parameter": "insert",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case when left child is null and delta is 2 during non-insertion, which should trigger a NullPointerException."
        }
    ]
}