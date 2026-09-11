{
    "testCases": [
        {
            "group": "equals method basic scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": null
                        },
                        {
                            "parameter": "b",
                            "value": null
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are null, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String"
                        },
                        {
                            "parameter": "b",
                            "value": "String"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same Class type, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String"
                        },
                        {
                            "parameter": "b",
                            "value": "Integer"
                        }
                    ],
                    "expected": false,
                    "description": "Test when Type parameters are different Class types, should return false."
                }
            ]
        },
        {
            "group": "equals method ParameterizedType scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<String>"
                        },
                        {
                            "parameter": "b",
                            "value": "List<String>"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same ParameterizedType, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<String>"
                        },
                        {
                            "parameter": "b",
                            "value": "List<Integer>"
                        }
                    ],
                    "expected": false,
                    "description": "Test when ParameterizedTypes have different actual type arguments, should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "List<String>"
                        },
                        {
                            "parameter": "b",
                            "value": "Set<String>"
                        }
                    ],
                    "expected": false,
                    "description": "Test when ParameterizedTypes have different raw types, should return false."
                }
            ]
        },
        {
            "group": "equals method GenericArrayType scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String[]"
                        },
                        {
                            "parameter": "b",
                            "value": "String[]"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same GenericArrayType, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "String[]"
                        },
                        {
                            "parameter": "b",
                            "value": "Integer[]"
                        }
                    ],
                    "expected": false,
                    "description": "Test when GenericArrayTypes have different component types, should return false."
                }
            ]
        },
        {
            "group": "equals method WildcardType scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "? extends Number"
                        },
                        {
                            "parameter": "b",
                            "value": "? extends Number"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same WildcardType with the same upper bounds, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "? extends Number"
                        },
                        {
                            "parameter": "b",
                            "value": "? extends Integer"
                        }
                    ],
                    "expected": false,
                    "description": "Test when WildcardTypes have different upper bounds, should return false."
                }
            ]
        },
        {
            "group": "equals method TypeVariable scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "T"
                        },
                        {
                            "parameter": "b",
                            "value": "T"
                        }
                    ],
                    "expected": true,
                    "description": "Test when both Type parameters are the same TypeVariable, should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "T"
                        },
                        {
                            "parameter": "b",
                            "value": "S"
                        }
                    ],
                    "expected": false,
                    "description": "Test when TypeVariables have different names, should return false."
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
                            "value": "String"
                        },
                        {
                            "parameter": "b",
                            "value": "Object"
                        }
                    ],
                    "expected": false,
                    "description": "Test when Type parameters are of unsupported types, should return false."
                }
            ]
        }
    ]
}