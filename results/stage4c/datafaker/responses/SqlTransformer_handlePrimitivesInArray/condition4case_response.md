{
    "group": "handlePrimitivesInArray",
    "cases": [
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "byte.class"
                },
                {
                    "parameter": "value",
                    "value": "[1, 2, 3]"
                }
            ],
            "expected": "1, 2, 3",
            "description": "Test with byte array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "short.class"
                },
                {
                    "parameter": "value",
                    "value": "[10, 20, 30]"
                }
            ],
            "expected": "10, 20, 30",
            "description": "Test with short array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "boolean.class"
                },
                {
                    "parameter": "value",
                    "value": "[true, false, true]"
                }
            ],
            "expected": "true, false, true",
            "description": "Test with boolean array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "int.class"
                },
                {
                    "parameter": "value",
                    "value": "[100, 200, 300]"
                }
            ],
            "expected": "100, 200, 300",
            "description": "Test with int array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "long.class"
                },
                {
                    "parameter": "value",
                    "value": "[1000L, 2000L, 3000L]"
                }
            ],
            "expected": "1000, 2000, 3000",
            "description": "Test with long array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "float.class"
                },
                {
                    "parameter": "value",
                    "value": "[1.1f, 2.2f, 3.3f]"
                }
            ],
            "expected": "1.1, 2.2, 3.3",
            "description": "Test with float array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "double.class"
                },
                {
                    "parameter": "value",
                    "value": "[1.11, 2.22, 3.33]"
                }
            ],
            "expected": "1.11, 2.22, 3.33",
            "description": "Test with double array to ensure all elements are correctly converted to string and joined."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "char.class"
                },
                {
                    "parameter": "value",
                    "value": "['a', 'b', 'c']"
                }
            ],
            "expected": "",
            "description": "Test with unsupported char array to ensure it returns an empty string as char is not handled."
        }
    ]
}