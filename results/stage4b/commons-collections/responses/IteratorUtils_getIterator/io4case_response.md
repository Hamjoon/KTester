{
    "test cases": [
        {
            "group": "getIterator - Input is Empty Iterable",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new ArrayList<>()"
                        }
                    ],
                    "expected": "EmptyIterator.RESETTABLE_INSTANCE",
                    "description": "Test case where the input is an empty Iterable. The method should return an empty iterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Empty Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new String[] {}"
                        }
                    ],
                    "expected": "EmptyIterator.RESETTABLE_INSTANCE",
                    "description": "Test case where the input is an empty array. The method should return an empty iterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Empty Enumeration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Vector<>().elements()"
                        }
                    ],
                    "expected": "EmptyIterator.RESETTABLE_INSTANCE",
                    "description": "Test case where the input is an empty Enumeration. The method should return an empty iterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Primitive Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new char[] {'a', 'b', 'c'}"
                        }
                    ],
                    "expected": "Iterator containing elements 'a', 'b', 'c'",
                    "description": "Test case where the input is a primitive char array. The method should return an iterator that iterates over the array elements."
                }
            ]
        },
        {
            "group": "getIterator - Input is Custom Object with No Iterator Method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Object()"
                        }
                    ],
                    "expected": "SingletonIterator containing the input object",
                    "description": "Test case where the input is a custom object that does not have an iterator method. The method should return a singleton iterator containing the input object."
                }
            ]
        },
        {
            "group": "getIterator - Input is List with Null Element",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new ArrayList<>(Arrays.asList(1, null, 3))"
                        }
                    ],
                    "expected": "Iterator containing elements 1, null, 3",
                    "description": "Test case where the input is a List containing a null element. The method should return an iterator that includes the null element."
                }
            ]
        },
        {
            "group": "getIterator - Input is Map with Null Value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new HashMap<>(Map.of(1, null, 2, 'b'))"
                        }
                    ],
                    "expected": "Iterator containing values null, 'b'",
                    "description": "Test case where the input is a Map with a null value. The method should return an iterator that includes the null value."
                }
            ]
        }
    ]
}