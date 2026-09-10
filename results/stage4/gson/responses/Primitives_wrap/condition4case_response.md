{
    "group": "wrap method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "int.class"
                }
            ],
            "expected": "Integer.class",
            "description": "Test case for wrapping the primitive type int, expecting the output to be Integer.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "float.class"
                }
            ],
            "expected": "Float.class",
            "description": "Test case for wrapping the primitive type float, expecting the output to be Float.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "byte.class"
                }
            ],
            "expected": "Byte.class",
            "description": "Test case for wrapping the primitive type byte, expecting the output to be Byte.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "double.class"
                }
            ],
            "expected": "Double.class",
            "description": "Test case for wrapping the primitive type double, expecting the output to be Double.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "long.class"
                }
            ],
            "expected": "Long.class",
            "description": "Test case for wrapping the primitive type long, expecting the output to be Long.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "char.class"
                }
            ],
            "expected": "Character.class",
            "description": "Test case for wrapping the primitive type char, expecting the output to be Character.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "boolean.class"
                }
            ],
            "expected": "Boolean.class",
            "description": "Test case for wrapping the primitive type boolean, expecting the output to be Boolean.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "short.class"
                }
            ],
            "expected": "Short.class",
            "description": "Test case for wrapping the primitive type short, expecting the output to be Short.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "void.class"
                }
            ],
            "expected": "Void.class",
            "description": "Test case for wrapping the primitive type void, expecting the output to be Void.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "String.class"
                }
            ],
            "expected": "String.class",
            "description": "Test case for wrapping a non-primitive type String, expecting the output to remain String.class."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Object.class"
                }
            ],
            "expected": "Object.class",
            "description": "Test case for wrapping a non-primitive type Object, expecting the output to remain Object.class."
        }
    ]
}