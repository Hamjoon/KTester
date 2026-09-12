{
    "test cases": [
        {
            "group": "newDefaultImplementationConstructor Additional Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "java.util.EnumSet"
                        },
                        {
                            "parameter": "rawType",
                            "value": "java.util.Set"
                        }
                    ],
                    "expected": "null",
                    "description": "Test for EnumSet type which requires a special constructor, expecting null."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "java.util.EnumMap<java.lang.Enum, java.lang.String>"
                        },
                        {
                            "parameter": "rawType",
                            "value": "java.util.Map"
                        }
                    ],
                    "expected": "null",
                    "description": "Test for EnumMap type which requires a special constructor, expecting null."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "java.util.HashMap<java.lang.String, java.lang.String>"
                        },
                        {
                            "parameter": "rawType",
                            "value": "java.util.Map"
                        }
                    ],
                    "expected": "LinkedTreeMap instance",
                    "description": "Test for Map type with String keys, expecting a LinkedTreeMap instance."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "java.util.LinkedList"
                        },
                        {
                            "parameter": "rawType",
                            "value": "java.util.List"
                        }
                    ],
                    "expected": "ArrayList instance",
                    "description": "Test for List type, expecting an ArrayList instance."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "java.util.HashSet"
                        },
                        {
                            "parameter": "rawType",
                            "value": "java.util.Set"
                        }
                    ],
                    "expected": "LinkedHashSet instance",
                    "description": "Test for Set type with HashSet, expecting a LinkedHashSet instance."
                }
            ]
        }
    ]
}