{
    "group": "swapPosition Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "null"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=2, value='B')"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test scenario where the first node x is null. This should trigger a NullPointerException when attempting to access methods on x."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=1, value='A')"
                },
                {
                    "parameter": "y",
                    "value": "null"
                },
                {
                    "parameter": "dataElement",
                    "value": "KEY"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test scenario where the second node y is null. This should trigger a NullPointerException when attempting to access methods on y."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=1, value='A')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=2, value='B')"
                },
                {
                    "parameter": "dataElement",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test scenario where the dataElement is null. This should trigger a NullPointerException when attempting to use the ordinal method on a null dataElement."
        },
        {
            "input": [
                {
                    "parameter": "x",
                    "value": "Node(key=1, value='A')"
                },
                {
                    "parameter": "y",
                    "value": "Node(key=2, value='B')"
                },
                {
                    "parameter": "dataElement",
                    "value": "INVALID"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test scenario where the dataElement is invalid. This should trigger an IllegalArgumentException when attempting to switch on an invalid dataElement."
        }
    ]
}