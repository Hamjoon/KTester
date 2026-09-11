{
    "testCases": [
        {
            "group": "getIterator - Null Input",
            "cases": [
                {
                    "input": [],
                    "expected": "EmptyIterator.RESETTABLE_INSTANCE",
                    "description": "Test case where the input is null. The method should return an empty iterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Instance of Iterator",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new ArrayList<>().iterator()"
                        }
                    ],
                    "expected": "Same iterator instance as input",
                    "description": "Test case where the input is an instance of Iterator. The method should return the same iterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Instance of Iterable",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new ArrayList<>(Arrays.asList(1, 2, 3))"
                        }
                    ],
                    "expected": "Iterator containing elements 1, 2, 3",
                    "description": "Test case where the input is an instance of Iterable. The method should return an iterator for the elements of the Iterable."
                }
            ]
        },
        {
            "group": "getIterator - Input is Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new String[] {\"a\", \"b\", \"c\"}"
                        }
                    ],
                    "expected": "Iterator containing elements 'a', 'b', 'c'",
                    "description": "Test case where the input is an array. The method should return an iterator that iterates over the array elements."
                }
            ]
        },
        {
            "group": "getIterator - Input is Enumeration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Vector<>(Arrays.asList(1, 2, 3)).elements()"
                        }
                    ],
                    "expected": "Iterator containing elements 1, 2, 3",
                    "description": "Test case where the input is an instance of Enumeration. The method should return an iterator for the elements of the Enumeration."
                }
            ]
        },
        {
            "group": "getIterator - Input is Map",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new HashMap<>(Map.of(1, 'a', 2, 'b')).values()"
                        }
                    ],
                    "expected": "Iterator containing values 'a', 'b'",
                    "description": "Test case where the input is a Map. The method should return an iterator over the values of the Map."
                }
            ]
        },
        {
            "group": "getIterator - Input is NodeList",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "mock(NodeList.class)"
                        }
                    ],
                    "expected": "NodeListIterator instance",
                    "description": "Test case where the input is an instance of NodeList. The method should return a NodeListIterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "mock(Node.class)"
                        }
                    ],
                    "expected": "NodeListIterator instance",
                    "description": "Test case where the input is an instance of Node. The method should return a NodeListIterator."
                }
            ]
        },
        {
            "group": "getIterator - Input is Dictionary",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Hashtable<>(Map.of(1, 'a', 2, 'b'))"
                        }
                    ],
                    "expected": "Iterator containing values 'a', 'b'",
                    "description": "Test case where the input is an instance of Dictionary. The method should return an iterator for the elements of the Dictionary."
                }
            ]
        },
        {
            "group": "getIterator - Input is Class Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new int[] {1, 2, 3}"
                        }
                    ],
                    "expected": "Iterator containing elements 1, 2, 3",
                    "description": "Test case where the input is a primitive array. The method should return an iterator that iterates over the array elements."
                }
            ]
        },
        {
            "group": "getIterator - Input with iterator() method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new CustomIterable()"
                        }
                    ],
                    "expected": "Iterator returned by CustomIterable's iterator method",
                    "description": "Test case where the input has an 'iterator' method that returns an Iterator. The method should return the iterator from that method."
                }
            ]
        },
        {
            "group": "getIterator - Input with no matching type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "obj",
                            "value": "new Object()"
                        }
                    ],
                    "expected": "SingletonIterator containing the input object",
                    "description": "Test case where the input does not match any specific type. The method should return a singleton iterator containing the input object."
                }
            ]
        }
    ]
}