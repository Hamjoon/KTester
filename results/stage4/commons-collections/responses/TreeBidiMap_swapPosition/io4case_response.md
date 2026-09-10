{
    "testCases": [
        {
            "group": "swapPosition",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node1"
                        },
                        {
                            "parameter": "y",
                            "value": "Node2"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Node1 and Node2 swapped, with correct parent-child relationships",
                    "description": "Test swapping two nodes where x is the parent of y, using VALUE as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node3"
                        },
                        {
                            "parameter": "y",
                            "value": "Node4"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Node3 and Node4 swapped, with correct parent-child relationships",
                    "description": "Test swapping two nodes where y is the parent of x, using KEY as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node5"
                        },
                        {
                            "parameter": "y",
                            "value": "Node6"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "INVALID"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test swapping with an invalid DataElement enum value."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node7"
                        },
                        {
                            "parameter": "y",
                            "value": "Node8"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Node7 and Node8 swapped without changing root node",
                    "description": "Test swapping two unrelated nodes that are siblings, using KEY as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node9"
                        },
                        {
                            "parameter": "y",
                            "value": "Node10"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Node9 and Node10 swapped without changing root node",
                    "description": "Test swapping two nodes that are not related, using VALUE as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node11"
                        },
                        {
                            "parameter": "y",
                            "value": "Node12"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "Root node updated correctly after swap",
                    "description": "Test swapping where one of the nodes is the root node, using KEY as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node13"
                        },
                        {
                            "parameter": "y",
                            "value": "Node14"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "Root node updated correctly after swap",
                    "description": "Test swapping where both nodes are root nodes, using VALUE as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "null"
                        },
                        {
                            "parameter": "y",
                            "value": "Node15"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "KEY"
                        }
                    ],
                    "expected": "NullPointerException or appropriate error handling",
                    "description": "Test swapping with one node as null, using KEY as DataElement."
                },
                {
                    "input": [
                        {
                            "parameter": "x",
                            "value": "Node16"
                        },
                        {
                            "parameter": "y",
                            "value": "null"
                        },
                        {
                            "parameter": "dataElement",
                            "value": "VALUE"
                        }
                    ],
                    "expected": "NullPointerException or appropriate error handling",
                    "description": "Test swapping with one node as null, using VALUE as DataElement."
                }
            ]
        }
    ]
}