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
                    "description": "Test unwrapping of Short wrapper type to its primitive type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Boolean.class"
                        }
                    ],
                    "expected": "boolean.class",
                    "description": "Test unwrapping of Boolean wrapper type to its primitive type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Void.class"
                        }
                    ],
                    "expected": "void.class",
                    "description": "Test unwrapping of Void type to its primitive type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "String.class"
                        }
                    ],
                    "expected": "String.class",
                    "description": "Test passing a non-wrapper type to ensure it returns the same type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Object.class"
                        }
                    ],
                    "expected": "Object.class",
                    "description": "Test passing an Object type to ensure it returns the same type."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "null"
                        }
                    ],
                    "expected": "null",
                    "description": "Test passing a null value to ensure it returns null."
                }
            ]
        }
    ]
}