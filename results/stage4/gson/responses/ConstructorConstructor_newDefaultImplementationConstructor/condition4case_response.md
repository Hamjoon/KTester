{
    "testCases": [
        {
            "group": "newDefaultImplementationConstructor with Collection types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(TreeSet.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "SortedSet.class"
                        }
                    ],
                    "expected": "instance of TreeSet",
                    "description": "Test the method with a SortedSet type to ensure it returns a constructor that creates a TreeSet."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(LinkedHashSet.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Set.class"
                        }
                    ],
                    "expected": "instance of LinkedHashSet",
                    "description": "Test the method with a Set type to ensure it returns a constructor that creates a LinkedHashSet."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ArrayDeque.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Queue.class"
                        }
                    ],
                    "expected": "instance of ArrayDeque",
                    "description": "Test the method with a Queue type to ensure it returns a constructor that creates an ArrayDeque."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ArrayList.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Collection.class"
                        }
                    ],
                    "expected": "instance of ArrayList",
                    "description": "Test the method with a Collection type to ensure it returns a constructor that creates an ArrayList."
                }
            ]
        },
        {
            "group": "newDefaultImplementationConstructor with Map types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ConcurrentSkipListMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "ConcurrentNavigableMap.class"
                        }
                    ],
                    "expected": "instance of ConcurrentSkipListMap",
                    "description": "Test the method with a ConcurrentNavigableMap type to ensure it returns a constructor that creates a ConcurrentSkipListMap."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ConcurrentHashMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "ConcurrentMap.class"
                        }
                    ],
                    "expected": "instance of ConcurrentHashMap",
                    "description": "Test the method with a ConcurrentMap type to ensure it returns a constructor that creates a ConcurrentHashMap."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(TreeMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "SortedMap.class"
                        }
                    ],
                    "expected": "instance of TreeMap",
                    "description": "Test the method with a SortedMap type to ensure it returns a constructor that creates a TreeMap."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(LinkedHashMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "instance of LinkedHashMap",
                    "description": "Test the method with a Map type that has a parameterized type to ensure it returns a constructor that creates a LinkedHashMap."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(LinkedTreeMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "instance of LinkedTreeMap",
                    "description": "Test the method with a Map type that does not have a parameterized type to ensure it returns a constructor that creates a LinkedTreeMap."
                }
            ]
        },
        {
            "group": "newDefaultImplementationConstructor with invalid types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(String.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "String.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test the method with a non-collection and non-map type to ensure it returns null."
                }
            ]
        }
    ]
}