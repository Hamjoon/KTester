{
    "result": [
        {
            "group": "rebalance - Concurrent Modification Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node being modified while iterating"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "ConcurrentModificationException",
                    "description": "Test rebalancing where a concurrent modification occurs during the operation, expecting a ConcurrentModificationException."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node being modified while iterating"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "ConcurrentModificationException",
                    "description": "Test rebalancing where a concurrent modification occurs during the operation, expecting a ConcurrentModificationException."
                }
            ]
        },
        {
            "group": "rebalance - Invalid Object Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with invalid structure or state"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "InvalidObjectException",
                    "description": "Test rebalancing with an invalid node structure, expecting an InvalidObjectException."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node with invalid structure or state"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "InvalidObjectException",
                    "description": "Test rebalancing with an invalid node structure, expecting an InvalidObjectException."
                }
            ]
        },
        {
            "group": "rebalance - No Such Element Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node that does not exist in the tree"
                        },
                        {
                            "parameter": "insert",
                            "value": true
                        }
                    ],
                    "expected": "NoSuchElementException",
                    "description": "Test rebalancing with a non-existent node, expecting a NoSuchElementException."
                },
                {
                    "input": [
                        {
                            "parameter": "unbalanced",
                            "value": "Node that does not exist in the tree"
                        },
                        {
                            "parameter": "insert",
                            "value": false
                        }
                    ],
                    "expected": "NoSuchElementException",
                    "description": "Test rebalancing with a non-existent node, expecting a NoSuchElementException."
                }
            ]
        }
    ]
}