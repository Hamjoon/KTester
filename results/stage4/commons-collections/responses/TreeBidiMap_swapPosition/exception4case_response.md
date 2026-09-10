{
    "group": "swapPosition",
    "cases": [
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node17"
                },
                {
                    "parameter": "y",
                    "value": "Node18"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test swapping with a null parent node for x."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node19"
                },
                {
                    "parameter": "y",
                    "value": "Node20"
                },
                {
                    "parameter": "dataElement",
                    "value": "VALUE"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test swapping with a null parent node for y."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node21"
                },
                {
                    "parameter": "y",
                    "value": "Node22"
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
                    "value": "Node23"
                },
                {
                    "parameter": "y",
                    "value": "Node24"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "ConcurrentModificationException",
            "description": "Test swapping nodes when the tree structure has been modified concurrently."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node25"
                },
                {
                    "parameter": "y",
                    "value": "Node26"
                },
                {
                    "parameter": "dataElement",
                    "value": "VALUE"
                }
            ],
            "expected": "NullPointerException or appropriate error handling",
            "description": "Test swapping with one node as null, where x is null."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node27"
                },
                {
                    "parameter": "y",
                    "value": "Node28"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "NullPointerException or appropriate error handling",
            "description": "Test swapping with one node as null, where y is null."
        }
    ]
}