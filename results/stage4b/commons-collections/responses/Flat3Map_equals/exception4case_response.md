{
    "output": [
        {
            "group": "equals method additional tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "otherMap",
                            "value": "mapWithSameSizeAndKeysButDifferentTypes"
                        }
                    ],
                    "expected": false,
                    "description": "Test equals with another map having same keys and values but with different types, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "otherMap",
                            "value": "mapWithNullKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test equals with another map containing a null key, should return false if Flat3Map does not have a corresponding null key."
                },
                {
                    "input": [
                        {
                            "parameter": "otherMap",
                            "value": "mapWithNullValue"
                        }
                    ],
                    "expected": false,
                    "description": "Test equals with another map containing a null value, should return false if Flat3Map has a non-null value for the same key."
                }
            ]
        }
    ]
}