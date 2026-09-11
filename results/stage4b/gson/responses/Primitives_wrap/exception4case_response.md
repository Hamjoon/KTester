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
            "description": "Test case to verify that passing null as the type parameter throws a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Integer.class"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case to verify that wrapping Integer.class results in a ClassCastException since it is not a primitive type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "List.class"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case to verify that wrapping List.class results in a ClassCastException since it is not a primitive type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Map.class"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case to verify that wrapping Map.class results in a ClassCastException since it is not a primitive type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "String[].class"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case to verify that wrapping String[].class results in a ClassCastException since it is not a primitive type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "int[].class"
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case to verify that wrapping int[].class results in a ClassCastException since it is an array of primitive type."
        }
    ]
}