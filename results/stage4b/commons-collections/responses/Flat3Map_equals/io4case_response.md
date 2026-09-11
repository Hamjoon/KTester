{"group": "equals method additional tests", "cases": [
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithDifferentValuesForAllKeys"
            }
        ],
        "expected": false,
        "description": "Test equals with another map having the same size and keys but all values different, should return false."
    },
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithSameKeysAndOneDifferentValue"
            }
        ],
        "expected": false,
        "description": "Test equals with another map having the same keys but only one value different, should return false."
    },
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithSameSizeAndKeysAndNullValue"
            }
        ],
        "expected": true,
        "description": "Test equals with another map having same size, keys, and one value as null, should return true if the corresponding value in Flat3Map is also null."
    },
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithSameSizeAndKeysAndSomeNullValues"
            }
        ],
        "expected": false,
        "description": "Test equals with another map having same size and keys but some values null and others different, should return false."
    },
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithSameSizeAndKeysButDifferentOrder"
            }
        ],
        "expected": true,
        "description": "Test equals with another map having same size and keys but in different order, should return true as order does not matter for map equality."
    },
    {
        "input": [
            {
                "parameter": "otherMap",
                "value": "mapWithSameKeysAndValuesButDifferentType"
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
]}