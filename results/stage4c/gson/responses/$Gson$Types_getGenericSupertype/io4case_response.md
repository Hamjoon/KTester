{
    "group": "getGenericSupertype Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.HashMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.HashMap"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.concurrent.ConcurrentMap"
                }
            ],
            "expected": "java.util.concurrent.ConcurrentMap",
            "description": "Test case where supertype is an interface not directly implemented by rawType but potentially in hierarchy. Should resolve through interfaces."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.TreeMap"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.TreeMap"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.SortedMap"
                }
            ],
            "expected": "java.util.SortedMap",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.LinkedList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.LinkedList"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.Deque"
                }
            ],
            "expected": "java.util.Deque",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.HashSet"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.HashSet"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.Set"
                }
            ],
            "expected": "java.util.Set",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.HashSet"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.HashSet"
                },
                {
                    "parameter": "supertype",
                    "value": "java.lang.Cloneable"
                }
            ],
            "expected": "java.lang.Cloneable",
            "description": "Test case where supertype is a marker interface implemented by rawType. Should return Cloneable."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.RandomAccess"
                }
            ],
            "expected": "java.util.RandomAccess",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.lang.String"
                },
                {
                    "parameter": "rawType",
                    "value": "java.lang.String"
                },
                {
                    "parameter": "supertype",
                    "value": "java.lang.CharSequence"
                }
            ],
            "expected": "java.lang.CharSequence",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
        {
            "input": [
                {
                    "parameter": "context",
                    "value": "java.util.PriorityQueue"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.PriorityQueue"
                },
                {
                    "parameter": "supertype",
                    "value": "java.util.Queue"
                }
            ],
            "expected": "java.util.Queue",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        }
    ]
}