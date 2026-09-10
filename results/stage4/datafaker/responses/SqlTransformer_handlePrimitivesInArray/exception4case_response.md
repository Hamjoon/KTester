{
    "output": [
        {
            "group": "handlePrimitivesInArray - invalid component type",
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
                    "expected": "ClassCastException",
                    "description": "Test with an invalid component type (String.class) to ensure a ClassCastException is thrown."
                }
            ]
        },
        {
            "group": "handlePrimitivesInArray - null value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "componentType",
                            "value": "int.class"
                        },
                        {
                            "parameter": "value",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test with a null value to ensure a NullPointerException is thrown."
                }
            ]
        }
    ]
}