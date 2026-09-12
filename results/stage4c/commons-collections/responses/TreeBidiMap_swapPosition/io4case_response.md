{
    "group": "Additional swapPosition Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=3, value='C')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=4, value='D')"
                },
                {
                    "parameter": "dataElement",
                    "value": "VALUE"
                }
            ],
            "expected": "Nodes x and y should swap positions without being direct parents",
            "description": "Test swapping two nodes that are not direct parent-child based on VALUE. Verify that the nodes swap positions correctly and their parent-child relationships are updated."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=5, value='E')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=6, value='F')"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "Nodes x and y should swap positions based on key",
            "description": "Test swapping two nodes with different keys. Verify that the swap happens based on the KEY data element."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=7, value='G')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=8, value='H')"
                },
                {
                    "parameter": "dataElement",
                    "value": "VALUE"
                }
            ],
            "expected": "x becomes y's parent",
            "description": "Test case where x is y's parent based on VALUE. Verify that x becomes y's parent correctly and the rest of the relationships are updated accordingly."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=9, value='I')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=10, value='J')"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "y becomes x's parent",
            "description": "Test case where y is x's parent based on KEY. Verify that y becomes x's parent correctly and the rest of the relationships are updated accordingly."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=11, value='K')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=12, value='L')"
                },
                {
                    "parameter": "dataElement",
                    "value": "VALUE"
                }
            ],
            "expected": "Root node changes if x or y is the root",
            "description": "Test scenario where either x or y is the root node based on VALUE. Verify that the root node reference updates correctly."
        }
    ]
}