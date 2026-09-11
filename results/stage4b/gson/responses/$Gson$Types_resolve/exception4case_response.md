{
    "group": "resolve with Class array",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "SomeClass.class"
                },
                {
                    "parameter": "contextRawType",
                    "value": "SomeClass.class"
                },
                {
                    "parameter": "toResolve",
                    "value": "null"
                },
                {
                    "parameter": "visitedTypeVariables",
                    "value": "new HashMap<TypeVariable<?>, Type>()"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test resolving with null as toResolve parameter."
        }
    ]
}