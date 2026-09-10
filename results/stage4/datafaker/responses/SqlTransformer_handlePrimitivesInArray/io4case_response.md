{
    "testCases": [
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
                            "value": "[false, false, false]"
                        }
                    ],
                    "expected": "false, false, false",
                    "description": "Test with a boolean array containing all false values."
                },
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "boolean.class"
                        },
                        {
                            "parameter": "value",
                            "value": "[true]"
                        }
                    ],
                    "expected": "true",
                    "description": "Test with a boolean array containing a single true value."
                }
            ]
        },
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty byte array."
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty short array."
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty int array."
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty long array."
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty float array."
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
                            "value": "[]"
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty double array."
                }
            ]
        }
    ]
}