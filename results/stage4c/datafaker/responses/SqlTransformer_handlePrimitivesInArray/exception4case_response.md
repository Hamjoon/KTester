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
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for byte array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "short.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for short array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "boolean.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for boolean array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "int.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for int array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "long.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for long array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "float.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for float array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "double.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for double array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "char.class"
                },
                {
                    "parameter": "value",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null value for unsupported char array to trigger NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "java.lang.String.class"
                },
                {
                    "parameter": "value",
                    "value": "['a', 'b', 'c']"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test with unsupported String array to trigger ClassCastException."
        }
    ]
}