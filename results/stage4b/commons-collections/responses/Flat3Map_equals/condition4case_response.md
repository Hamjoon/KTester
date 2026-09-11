{
    "test name": "equals method test cases",
    "cases": [
        {
            "input": [],
            "expected": true,
            "description": "Test equals with the same object reference, should return true."
        },
        {
            "input": [
                {
                    "parameter": "delegateMap",
                    "value": "mockedDelegateMap"
                }
            ],
            "expected": "true if mockedDelegateMap equals the object, false otherwise",
            "description": "Test equals when delegateMap is not null, should delegate comparison to delegateMap."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "nonMapObject"
                }
            ],
            "expected": false,
            "description": "Test equals with a non-Map object, should return false."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "emptyMap"
                }
            ],
            "expected": true,
            "description": "Test equals with another empty map, should return true."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "mapWithDifferentSize"
                }
            ],
            "expected": false,
            "description": "Test equals with another map of different size, should return false."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "mapWithSameSizeButDifferentKeys"
                }
            ],
            "expected": false,
            "description": "Test equals with another map of the same size but different keys, should return false."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "mapWithSameSizeAndKeys"
                }
            ],
            "expected": false,
            "description": "Test equals with another map having same size and keys but different values, should return false."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "mapWithSameSizeAndKeysAndValues"
                }
            ],
            "expected": true,
            "description": "Test equals with another map having same size, keys, and values, should return true."
        },
        {
            "input": [
                {
                    "parameter": "otherMap",
                    "value": "mapWithSameSizeAndPartialValues"
                }
            ],
            "expected": false,
            "description": "Test equals with another map having same size and keys but only some values matching, should return false."
        }
    ]
}