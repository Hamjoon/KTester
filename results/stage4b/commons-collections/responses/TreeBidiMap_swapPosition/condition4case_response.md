{
    "testCases": [
        {
            "group": "swapPosition - Parent-Child Relationship",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node A (parent)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node B (child of A)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Node A's parent is now Node B, Node B's left child is Node A",
                    "description": "Test swapping when x is the parent of y"
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node B (child)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node A (parent of B)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Node B's parent is now Node A, Node A's left child is Node B",
                    "description": "Test swapping when y is the parent of x"
                }
            ]
        },
        {
            "group": "swapPosition - Non-Parent Relationship",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node C (not related)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node D (not related)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Node C and Node D swap positions without affecting other nodes",
                    "description": "Test swapping when x and y are not parent/child"
                }
            ]
        },
        {
            "group": "swapPosition - Handling Left and Right Children",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node E (left child of Parent)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node F (right child of Parent)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Node E becomes the right child and Node F becomes the left child of Parent",
                    "description": "Test swapping when x is left child and y is right child"
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node G (right child of Parent)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node H (left child of Parent)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Node G becomes the left child and Node H becomes the right child of Parent",
                    "description": "Test swapping when x is right child and y is left child"
                }
            ]
        },
        {
            "group": "swapPosition - Root Node Changes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Root Node (Node I)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node J (child of Root)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Root changes to Node J after swap",
                    "description": "Test swapping when root node is involved"
                }
            ]
        },
        {
            "group": "swapPosition - Color Swap",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node K (black)"
                        },
                        {
                            "parameter": "y",
                            "value": "Node L (red)"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Node K becomes red and Node L becomes black after swap",
                    "description": "Test color swap functionality during position swap"
                }
            ]
        },
        {
            "group": "swapPosition - Invalid DataElement",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node M"
                        },
                        {
                            "parameter": "y",
                            "value": "Node N"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "INVALID"
                        }
                    ],
                    "expected": "throws IllegalArgumentException",
                    "description": "Test behavior when an invalid DataElement is provided"
                }
            ]
        }
    ]
}