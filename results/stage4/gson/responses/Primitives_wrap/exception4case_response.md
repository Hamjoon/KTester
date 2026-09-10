{
    "output": [
        {
            "group": "wrap method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test case for passing a null value, expecting a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Array.class"
                        }
                    ],
                    "expected": "Array.class",
                    "description": "Test case for passing an array type, expecting the output to remain Array.class."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "Map.class",
                    "description": "Test case for passing a Map type, expecting the output to remain Map.class."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "List.class"
                        }
                    ],
                    "expected": "List.class",
                    "description": "Test case for passing a List type, expecting the output to remain List.class."
                }
            ]
        }
    ]
}