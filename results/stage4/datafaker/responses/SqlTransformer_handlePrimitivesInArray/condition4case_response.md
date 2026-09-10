{
    "testCases": [
        {
            "group": "handlePrimitivesInArray - byte",
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
                            "value": "[-1, -2, -3]"
                        }
                    ],
                    "expected": "-1, -2, -3",
                    "description": "Test with a byte array containing negative values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - short",
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
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "short.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-100, -200, -300]"
                        }
                    ],
                    "expected": "-100, -200, -300",
                    "description": "Test with a short array containing negative values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - boolean",
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
                    "description": "Test with a boolean array containing mixed true and false values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - int",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "int.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[1, 2, 3]"
                        }
                    ],
                    "expected": "1, 2, 3",
                    "description": "Test with an int array containing positive values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "int.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-1, -2, -3]"
                        }
                    ],
                    "expected": "-1, -2, -3",
                    "description": "Test with an int array containing negative values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - long",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "long.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[10000000000, 20000000000, 30000000000]"
                        }
                    ],
                    "expected": "10000000000, 20000000000, 30000000000",
                    "description": "Test with a long array containing large positive values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "long.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-10000000000, -20000000000, -30000000000]"
                        }
                    ],
                    "expected": "-10000000000, -20000000000, -30000000000",
                    "description": "Test with a long array containing large negative values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - float",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "float.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[1.1, 2.2, 3.3]"
                        }
                    ],
                    "expected": "1.1, 2.2, 3.3",
                    "description": "Test with a float array containing positive values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "float.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-1.1, -2.2, -3.3]"
                        }
                    ],
                    "expected": "-1.1, -2.2, -3.3",
                    "description": "Test with a float array containing negative values."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - double",
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
                    "description": "Test with a double array containing positive values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "double.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[-1.1, -2.2, -3.3]"
                        }
                    ],
                    "expected": "-1.1, -2.2, -3.3",
                    "description": "Test with a double array containing negative values."
                }
            ]
        }
    ]
}