{
    "group": "getIterator Method Tests",
    "cases": [
        {
            "input": [],
            "expected": "EmptyIterator",
            "description": "Test with null input to return an empty iterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new ArrayList<>().iterator()"
                }
            ],
            "expected": "Iterator",
            "description": "Test with an Iterator input to return the same iterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new ArrayList<>()"
                }
            ],
            "expected": "Iterator",
            "description": "Test with an Iterable input to return its iterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new Object[] {1, 2, 3}"
                }
            ],
            "expected": "ObjectArrayIterator",
            "description": "Test with Object array input to return an ObjectArrayIterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new Vector<>().elements()"
                }
            ],
            "expected": "EnumerationIterator",
            "description": "Test with Enumeration input to return an EnumerationIterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap<>()"
                }
            ],
            "expected": "Iterator",
            "description": "Test with Map input to return an iterator over its values."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "mock(NodeList.class)"
                }
            ],
            "expected": "NodeListIterator",
            "description": "Test with NodeList input to return a NodeListIterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "mock(Node.class)"
                }
            ],
            "expected": "NodeListIterator",
            "description": "Test with Node input to return a NodeListIterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "mock(Dictionary.class)"
                }
            ],
            "expected": "EnumerationIterator",
            "description": "Test with Dictionary input to return an EnumerationIterator over its elements."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new int[] {1, 2, 3}"
                }
            ],
            "expected": "ArrayIterator",
            "description": "Test with primitive array input to return an ArrayIterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new CustomIterable()"
                }
            ],
            "expected": "SingletonIterator",
            "description": "Test with an object that does not match any condition but has an iterator method to return its iterator."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new Object()"
                }
            ],
            "expected": "SingletonIterator",
            "description": "Test with an object that does not match any condition to return a SingletonIterator."
        }
    ]
}