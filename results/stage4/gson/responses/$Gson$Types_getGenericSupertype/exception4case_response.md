{
    "output": [
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
                            "value": "String.class"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when both context and rawType are null, expecting a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "null"
                        },
                        {
                            "parameter": "supertype",
                            "value": "String.class"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when rawType is null, expecting a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when supertype is null, expecting a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "context",
                            "value": "String.class"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Object.class"
                        },
                        {
                            "parameter": "supertype",
                            "value": "Integer.class"
                        }
                    ],
                    "expected": "Integer.class",
                    "description": "Test when supertype is not related to rawType, expecting to return the supertype Integer."
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
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when all parameters are null, expecting a NullPointerException."
                }
            ]
        }
    ]
}