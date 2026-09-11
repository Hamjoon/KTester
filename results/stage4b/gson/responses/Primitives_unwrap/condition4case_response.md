{
    "testCases": [
        {
            "group": "unwrap method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Integer.class"
                        }
                    ],
                    "expected": "int.class",
                    "description": "Test unwrapping of Integer class to its primitive type int."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Float.class"
                        }
                    ],
                    "expected": "float.class",
                    "description": "Test unwrapping of Float class to its primitive type float."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Byte.class"
                        }
                    ],
                    "expected": "byte.class",
                    "description": "Test unwrapping of Byte class to its primitive type byte."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Double.class"
                        }
                    ],
                    "expected": "double.class",
                    "description": "Test unwrapping of Double class to its primitive type double."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Long.class"
                        }
                    ],
                    "expected": "long.class",
                    "description": "Test unwrapping of Long class to its primitive type long."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Character.class"
                        }
                    ],
                    "expected": "char.class",
                    "description": "Test unwrapping of Character class to its primitive type char."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Boolean.class"
                        }
                    ],
                    "expected": "boolean.class",
                    "description": "Test unwrapping of Boolean class to its primitive type boolean."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Short.class"
                        }
                    ],
                    "expected": "short.class",
                    "description": "Test unwrapping of Short class to its primitive type short."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Void.class"
                        }
                    ],
                    "expected": "void.class",
                    "description": "Test unwrapping of Void class to its primitive type void."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "String.class"
                        }
                    ],
                    "expected": "String.class",
                    "description": "Test passing a non-wrapper type (String) to ensure it returns the same type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "null"
                        }
                    ],
                    "expected": "null",
                    "description": "Test passing null to ensure it returns null."
                }
            ]
        }
    ]
}