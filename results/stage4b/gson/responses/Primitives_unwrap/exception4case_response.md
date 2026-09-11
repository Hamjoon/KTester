{
    "group": "unwrap method",
    "cases": [
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
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Long[].class"
                }
            ],
            "expected": "Long[].class",
            "description": "Test passing an array of Long to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Float[].class"
                }
            ],
            "expected": "Float[].class",
            "description": "Test passing an array of Float to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Double[].class"
                }
            ],
            "expected": "Double[].class",
            "description": "Test passing an array of Double to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Byte[].class"
                }
            ],
            "expected": "Byte[].class",
            "description": "Test passing an array of Byte to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Short[].class"
                }
            ],
            "expected": "Short[].class",
            "description": "Test passing an array of Short to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Character[].class"
                }
            ],
            "expected": "Character[].class",
            "description": "Test passing an array of Character to ensure it returns the same type."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "Boolean[].class"
                }
            ],
            "expected": "Boolean[].class",
            "description": "Test passing an array of Boolean to ensure it returns the same type."
        }
    ]
}