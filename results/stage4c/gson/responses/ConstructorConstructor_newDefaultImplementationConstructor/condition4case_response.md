{
    "group": "newDefaultImplementationConstructor Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.TreeSet"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.SortedSet"
                }
            ],
            "expected": "TreeSet instance",
            "description": "Test for SortedSet type, expecting a TreeSet instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.LinkedHashSet"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Set"
                }
            ],
            "expected": "LinkedHashSet instance",
            "description": "Test for Set type, expecting a LinkedHashSet instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.ArrayDeque"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Queue"
                }
            ],
            "expected": "ArrayDeque instance",
            "description": "Test for Queue type, expecting an ArrayDeque instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Collection"
                }
            ],
            "expected": "ArrayList instance",
            "description": "Test for Collection type, expecting an ArrayList instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.concurrent.ConcurrentSkipListMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.concurrent.ConcurrentNavigableMap"
                }
            ],
            "expected": "ConcurrentSkipListMap instance",
            "description": "Test for ConcurrentNavigableMap type, expecting a ConcurrentSkipListMap instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.concurrent.ConcurrentHashMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.concurrent.ConcurrentMap"
                }
            ],
            "expected": "ConcurrentHashMap instance",
            "description": "Test for ConcurrentMap type, expecting a ConcurrentHashMap instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.TreeMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.SortedMap"
                }
            ],
            "expected": "TreeMap instance",
            "description": "Test for SortedMap type, expecting a TreeMap instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.LinkedHashMap<java.lang.Integer, java.lang.String>"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Map"
                }
            ],
            "expected": "LinkedHashMap instance",
            "description": "Test for Map type with non-String keys, expecting a LinkedHashMap instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.LinkedTreeMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Map"
                }
            ],
            "expected": "LinkedTreeMap instance",
            "description": "Test for Map type with default case, expecting a LinkedTreeMap instance."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.Random"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.Random"
                }
            ],
            "expected": "null",
            "description": "Test for non-Collection and non-Map type, expecting null."
        }
    ]
}