{
    "testCases": [
        {
            "group": "rebalance - Right-Right Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height < right height (-2) and right child has no left child"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node rotated left, tree balanced",
                    "description": "Test rebalancing where a Right-Right case occurs and insert is true, expecting a left rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height < right height (-2) and right child has no left child"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node rotated left, tree balanced",
                    "description": "Test rebalancing where a Right-Right case occurs and insert is false, expecting a left rotation."
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
                            "value": "Node with left height < right height (-2) and right child has a left child"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node rotated right then left, tree balanced",
                    "description": "Test rebalancing where a Right-Left case occurs and insert is true, expecting a right rotation followed by a left rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height < right height (-2) and right child has a left child"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node rotated right then left, tree balanced",
                    "description": "Test rebalancing where a Right-Left case occurs and insert is false, expecting a right rotation followed by a left rotation."
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
                            "value": "Node with left height > right height (2) and left child has no right child"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node rotated right, tree balanced",
                    "description": "Test rebalancing where a Left-Left case occurs and insert is true, expecting a right rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height > right height (2) and left child has no right child"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node rotated right, tree balanced",
                    "description": "Test rebalancing where a Left-Left case occurs and insert is false, expecting a right rotation."
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
                            "value": "Node with left height > right height (2) and left child has a right child"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node rotated left then right, tree balanced",
                    "description": "Test rebalancing where a Left-Right case occurs and insert is true, expecting a left rotation followed by a right rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height > right height (2) and left child has a right child"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node rotated left then right, tree balanced",
                    "description": "Test rebalancing where a Left-Right case occurs and insert is false, expecting a left rotation followed by a right rotation."
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
                            "value": "Node with equal left and right height (0)"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node height updated, no rotations",
                    "description": "Test rebalancing where heights are equal and insert is true, expecting height update without rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with equal left and right height (0)"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node height updated, no rotations",
                    "description": "Test rebalancing where heights are equal and insert is false, expecting height update without rotation."
                }
            ]
        },
        {
            "group": "rebalance - Height Difference Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height > right height (1) or left height < right height (-1)"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "Node height updated, no rotations",
                    "description": "Test rebalancing where heights differ by 1 and insert is true, expecting height update without rotation."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with left height > right height (1) or left height < right height (-1)"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "Node height updated, no rotations",
                    "description": "Test rebalancing where heights differ by 1 and insert is false, expecting height update without rotation."
                }
            ]
        }
    ]
}