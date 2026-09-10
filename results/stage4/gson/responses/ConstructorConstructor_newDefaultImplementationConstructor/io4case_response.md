{
    "test cases": [
        {
            "group": "newDefaultImplementationConstructor with Collection types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(ArrayList.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "List.class"
                        }
                    ],
                    "expected": "instance of ArrayList",
                    "description": "Test the method with a List type to ensure it returns a constructor that creates an ArrayList."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(HashSet.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Set.class"
                        }
                    ],
                    "expected": "instance of HashSet",
                    "description": "Test the method with a Set type to ensure it returns a constructor that creates a HashSet."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(LinkedList.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Queue.class"
                        }
                    ],
                    "expected": "instance of LinkedList",
                    "description": "Test the method with a Queue type to ensure it returns a constructor that creates a LinkedList."
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
                            "value": "TypeToken.get(HashMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "instance of HashMap",
                    "description": "Test the method with a Map type that has a parameterized type to ensure it returns a constructor that creates a HashMap."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(EnumMap.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test the method with an EnumMap type to ensure it returns null, as it does not have a public no-args constructor."
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
                            "value": "TypeToken.get(Object.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Object.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test the method with a non-collection and non-map type to ensure it returns null."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "TypeToken.get(Integer.class)"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Integer.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test the method with a non-collection and non-map type to ensure it returns null."
                }
            ]
        }
    ]
}