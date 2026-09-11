{
    "test cases": [
        {
            "group": "wrap method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Object.class"
                        }
                    ],
                    "expected": "Object.class",
                    "description": "Test case to verify that wrapping Object.class returns Object.class (non-primitive type)."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "String[].class"
                        }
                    ],
                    "expected": "String[].class",
                    "description": "Test case to verify that wrapping String[].class returns String[].class (array type)."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "List.class"
                        }
                    ],
                    "expected": "List.class",
                    "description": "Test case to verify that wrapping List.class returns List.class (non-primitive type)."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "Map.class",
                    "description": "Test case to verify that wrapping Map.class returns Map.class (non-primitive type)."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "void.class"
                        }
                    ],
                    "expected": "Void.class",
                    "description": "Test case to verify that wrapping void.class returns Void.class."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "int[].class"
                        }
                    ],
                    "expected": "int[].class",
                    "description": "Test case to verify that wrapping int[].class returns int[].class (array of primitive type)."
                }
            ]
        }
    ]
}