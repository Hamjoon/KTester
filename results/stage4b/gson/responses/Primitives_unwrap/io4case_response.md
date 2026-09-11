{
    "test cases": [
        {
            "group": "unwrap method",
            "cases": [
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
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Object.class"
                        }
                    ],
                    "expected": "Object.class",
                    "description": "Test passing Object class to ensure it returns the same type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Integer[].class"
                        }
                    ],
                    "expected": "Integer[].class",
                    "description": "Test passing an array of Integer to ensure it returns the same type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "int[].class"
                        }
                    ],
                    "expected": "int[].class",
                    "description": "Test passing an array of int to ensure it returns the same type."
                }
            ]
        }
    ]
}