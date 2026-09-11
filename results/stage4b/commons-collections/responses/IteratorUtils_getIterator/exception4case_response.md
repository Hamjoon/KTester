{
    "result": [
        {
            "group": "getIterator - Input with ReflectiveOperationException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Object() { public Iterator iterator() { throw new ReflectiveOperationException(); } }"
                        }
                    ],
                    "expected": "SingletonIterator containing the input object",
                    "description": "Test case where the input has an 'iterator' method that throws a ReflectiveOperationException. The method should return a singleton iterator containing the input object."
                }
            ]
        },
        {
            "group": "getIterator - Input with RuntimeException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Object() { public Iterator iterator() { throw new RuntimeException(); } }"
                        }
                    ],
                    "expected": "SingletonIterator containing the input object",
                    "description": "Test case where the input has an 'iterator' method that throws a RuntimeException. The method should return a singleton iterator containing the input object."
                }
            ]
        }
    ]
}