{
    "testCases": [
        {
            "group": "Test newDefaultImplementationConstructor for Collection types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<List<String>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "List.class"
                        }
                    ],
                    "expected": "instance of ArrayList",
                    "description": "Test case to verify that a List type returns an ArrayList instance."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<Deque<String>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Deque.class"
                        }
                    ],
                    "expected": "instance of ArrayDeque",
                    "description": "Test case to verify that a Deque type returns an ArrayDeque instance."
                }
            ]
        },
        {
            "group": "Test newDefaultImplementationConstructor for Map types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<SortedMap<Integer, String>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "SortedMap.class"
                        }
                    ],
                    "expected": "instance of TreeMap",
                    "description": "Test case to verify that a SortedMap type with Integer keys returns a TreeMap instance."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<Map<Integer, String>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "Map.class"
                        }
                    ],
                    "expected": "instance of LinkedHashMap",
                    "description": "Test case to verify that a Map type with Integer keys returns a LinkedHashMap instance."
                }
            ]
        },
        {
            "group": "Test newDefaultImplementationConstructor for invalid types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<EnumSet<MyEnum>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "EnumSet.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case to verify that an EnumSet type returns null due to special constructor handling."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<InterfaceType>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "InterfaceType.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case to verify that an interface type returns null."
                }
            ]
        }
    ]
}