{
    "output": [
        {
            "group": "put method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "keyWithInvalidBit"
                        },
                        {
                            "parameter": "value",
                            "value": "valueForInvalidBit"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test putting a key that results in an invalid bit index should throw an IllegalArgumentException."
                }
            ]
        }
    ]
}