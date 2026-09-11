{
    "output": [
        {
            "group": "put method - invalid bit index",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithInvalidBitIndex"
                        },
                        {
                            "parameter": "value",
                            "value": "value"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test case where an invalid bit index is encountered. Expecting an IllegalArgumentException to be thrown."
                }
            ]
        },
        {
            "group": "put method - key found with equal bit key but not root",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithEqualBit"
                        },
                        {
                            "parameter": "value",
                            "value": "updatedValue"
                        }
                    ],
                    "expected": "updatedValue",
                    "description": "Test case where a key is found with an equal bit key but not at the root. Expecting the method to update the value."
                }
            ]
        }
    ]
}