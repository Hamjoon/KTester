{
    "group": "unwrap method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Integer.class"
                }
            ],
            "expected": "int.class",
            "description": "Test case for unwrapping Integer class to int primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Float.class"
                }
            ],
            "expected": "float.class",
            "description": "Test case for unwrapping Float class to float primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Byte.class"
                }
            ],
            "expected": "byte.class",
            "description": "Test case for unwrapping Byte class to byte primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Double.class"
                }
            ],
            "expected": "double.class",
            "description": "Test case for unwrapping Double class to double primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Long.class"
                }
            ],
            "expected": "long.class",
            "description": "Test case for unwrapping Long class to long primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Character.class"
                }
            ],
            "expected": "char.class",
            "description": "Test case for unwrapping Character class to char primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Boolean.class"
                }
            ],
            "expected": "boolean.class",
            "description": "Test case for unwrapping Boolean class to boolean primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Short.class"
                }
            ],
            "expected": "short.class",
            "description": "Test case for unwrapping Short class to short primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Void.class"
                }
            ],
            "expected": "void.class",
            "description": "Test case for unwrapping Void class to void primitive type"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "String.class"
                }
            ],
            "expected": "String.class",
            "description": "Test case for non-wrapper class String, should return the same class"
        }
    ]
}