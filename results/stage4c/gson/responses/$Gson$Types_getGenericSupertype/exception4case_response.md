{
    "group": "getGenericSupertype Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.List"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where context is null. Should throw NullPointerException as context is required for type resolution."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "null"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.List"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where rawType is null. Should throw NullPointerException as rawType is required for type resolution."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "supertype",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where supertype is null. Should throw NullPointerException as supertype is required for type resolution."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.HashMap"
                }
            ],
            "expected": "return supertype",
            "description": "Test case where supertype is unrelated to rawType. Should return the supertype itself as it cannot be resolved further."
        }
    ]
}