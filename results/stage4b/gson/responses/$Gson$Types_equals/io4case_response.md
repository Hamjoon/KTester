{
    "testCases": [
        {
            "group": "equals method ParameterizedType edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<?>"
                        },
                        {
                            "parameter": "b",
                            "value": "List<?>"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same wildcard ParameterizedType, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<? extends Number>"
                        },
                        {
                            "parameter": "b",
                            "value": "List<? extends Number>"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same wildcard ParameterizedType with the same upper bounds, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<? super Integer>"
                        },
                        {
                            "parameter": "b",
                            "value": "List<? super Number>"
                        }
                    ],
                    "expected": false,
                    "description": "Test when ParameterizedTypes have different lower bounds, should return false."
                }
            ]
        },
        {
            "group": "equals method GenericArrayType edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String[][]"
                        },
                        {
                            "parameter": "b",
                            "value": "String[][]"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same multidimensional GenericArrayType, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String[][]"
                        },
                        {
                            "parameter": "b",
                            "value": "Integer[][]"
                        }
                    ],
                    "expected": false,
                    "description": "Test when multidimensional GenericArrayTypes have different component types, should return false."
                }
            ]
        },
        {
            "group": "equals method TypeVariable edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "T<V>"
                        },
                        {
                            "parameter": "b",
                            "value": "T<V>"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same TypeVariable with a bound, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "T<V>"
                        },
                        {
                            "parameter": "b",
                            "value": "T<W>"
                        }
                    ],
                    "expected": false,
                    "description": "Test when TypeVariables have different bounds, should return false."
                }
            ]
        },
        {
            "group": "equals method unsupported types scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "Object"
                        },
                        {
                            "parameter": "b",
                            "value": "GenericArrayType"
                        }
                    ],
                    "expected": false,
                    "description": "Test when Type parameters are of unsupported types (Object vs GenericArrayType), should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "Map<String, String>"
                        },
                        {
                            "parameter": "b",
                            "value": "Map<Object, Object>"
                        }
                    ],
                    "expected": false,
                    "description": "Test when Type parameters are of unsupported types (Map vs Map with different types), should return false."
                }
            ]
        }
    ]
}