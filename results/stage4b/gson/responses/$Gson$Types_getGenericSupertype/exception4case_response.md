{
    "group": "getGenericSupertype",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "null"
                },
                {
                    "parameter": "supertype",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when context, rawType, and supertype are all null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "String.class"
                },
                {
                    "parameter": "rawType",
                    "value": "String.class"
                },
                {
                    "parameter": "supertype",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when supertype is null while context and rawType are valid, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "String.class"
                },
                {
                    "parameter": "supertype",
                    "value": "String.class"
                }
            ],
            "expected": "String.class",
            "description": "Test when context is null while rawType and supertype are valid, expecting the method to return the supertype."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "null"
                },
                {
                    "parameter": "supertype",
                    "value": "Object.class"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when context is null and rawType is null but supertype is valid, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "Object.class"
                },
                {
                    "parameter": "rawType",
                    "value": "Object.class"
                },
                {
                    "parameter": "supertype",
                    "value": "Object.class"
                }
            ],
            "expected": "Object.class",
            "description": "Test when context, rawType, and supertype are all Object.class, expecting the method to return context."
        }
    ]
}