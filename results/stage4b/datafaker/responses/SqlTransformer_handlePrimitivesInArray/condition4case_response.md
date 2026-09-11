{
    "testCases": [
        {
            "group": "handlePrimitivesInArray - byte array",
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
                    "description": "Test with a byte array containing positive values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "byte.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-1, 0, 1]"
                        }
                    ],
                    "expected": "-1, 0, 1",
                    "description": "Test with a byte array containing negative, zero, and positive values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - short array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "short.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[100, 200, 300]"
                        }
                    ],
                    "expected": "100, 200, 300",
                    "description": "Test with a short array containing positive values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - boolean array",
            "cases": [
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
                    "description": "Test with a boolean array containing true and false values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - int array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "int.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[1, 2, 3, 4]"
                        }
                    ],
                    "expected": "1, 2, 3, 4",
                    "description": "Test with an int array containing positive integers."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - long array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "long.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[10000000000L, 20000000000L]"
                        }
                    ],
                    "expected": "10000000000, 20000000000",
                    "description": "Test with a long array containing large positive values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - float array",
            "cases": [
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
                    "description": "Test with a float array containing decimal values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - double array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "double.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[1.1, 2.2, 3.3]"
                        }
                    ],
                    "expected": "1.1, 2.2, 3.3",
                    "description": "Test with a double array containing decimal values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - unsupported type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "String.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an unsupported type (String) which should return an empty string."
                }
            ]
        }
    ]
}