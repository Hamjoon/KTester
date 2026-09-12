{
    "group": "unwrap method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Object.class"
                }
            ],
            "expected": "Object.class",
            "description": "Test case for non-wrapper class Object, should return the same class"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "int.class"
                }
            ],
            "expected": "int.class",
            "description": "Test case for primitive type int, should return the same class"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "float.class"
                }
            ],
            "expected": "float.class",
            "description": "Test case for primitive type float, should return the same class"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case for null input, should throw NullPointerException"
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "CustomClass.class"
                }
            ],
            "expected": "CustomClass.class",
            "description": "Test case for non-wrapper, non-primitive custom class, should return the same class"
        }
    ]
}