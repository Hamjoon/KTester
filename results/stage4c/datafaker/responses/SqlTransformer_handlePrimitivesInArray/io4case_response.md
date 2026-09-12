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
                    "value": "[]"
                }
            ],
            "expected": "",
            "description": "Test with an empty byte array to ensure it returns an empty string."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "short.class"
                },
                {
                    "parameter": "value",
                    "value": "[Short.MAX_VALUE, Short.MIN_VALUE]"
                }
            ],
            "expected": "32767, -32768",
            "description": "Test with short array containing boundary values to ensure correct conversion and joining."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "boolean.class"
                },
                {
                    "parameter": "value",
                    "value": "[]"
                }
            ],
            "expected": "",
            "description": "Test with an empty boolean array to ensure it returns an empty string."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "int.class"
                },
                {
                    "parameter": "value",
                    "value": "[Integer.MAX_VALUE, Integer.MIN_VALUE]"
                }
            ],
            "expected": "2147483647, -2147483648",
            "description": "Test with int array containing boundary values to ensure correct conversion and joining."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "long.class"
                },
                {
                    "parameter": "value",
                    "value": "[Long.MAX_VALUE, Long.MIN_VALUE]"
                }
            ],
            "expected": "9223372036854775807, -9223372036854775808",
            "description": "Test with long array containing boundary values to ensure correct conversion and joining."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "float.class"
                },
                {
                    "parameter": "value",
                    "value": "[Float.MAX_VALUE, Float.MIN_VALUE]"
                }
            ],
            "expected": "3.4028235E38, 1.4E-45",
            "description": "Test with float array containing boundary values to ensure correct conversion and joining."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "double.class"
                },
                {
                    "parameter": "value",
                    "value": "[Double.MAX_VALUE, Double.MIN_VALUE]"
                }
            ],
            "expected": "1.7976931348623157E308, 4.9E-324",
            "description": "Test with double array containing boundary values to ensure correct conversion and joining."
        },
        {
            "input": [
                {
                    "parameter": "componentType",
                    "value": "int.class"
                },
                {
                    "parameter": "value",
                    "value": "[0, -0]"
                }
            ],
            "expected": "0, 0",
            "description": "Test with int array containing zero and negative zero to ensure correct conversion and joining."
        }
    ]
}