{
    "group": "getGenericSupertype Tests",
    "cases": [
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
                    "value": "java.util.ArrayList"
                }
            ],
            "expected": "java.util.ArrayList",
            "description": "Test case where supertype is the same as rawType. Should return context."
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
                    "value": "java.util.List"
                }
            ],
            "expected": "java.util.List",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
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
                    "value": "java.util.Map"
                }
            ],
            "expected": "java.util.Map",
            "description": "Test case where supertype is an interface directly implemented by rawType. Should return the implemented interface."
        },
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
                    "value": "java.util.AbstractMap"
                }
            ],
            "expected": "java.util.AbstractMap",
            "description": "Test case where supertype is the superclass of rawType. Should return the superclass."
        },
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
                    "value": "java.lang.Object"
                }
            ],
            "expected": "java.lang.Object",
            "description": "Test case where supertype is the ultimate superclass of all classes. Should return Object."
        },
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
                    "value": "java.util.Collection"
                }
            ],
            "expected": "java.util.Collection",
            "description": "Test case where supertype is an interface not directly implemented by rawType but is in the hierarchy. Should resolve through interfaces."
        },
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
                    "value": "java.io.Serializable"
                }
            ],
            "expected": "java.io.Serializable",
            "description": "Test case where supertype is a marker interface implemented by rawType. Should return Serializable."
        },
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
                    "value": "java.util.RandomAccess"
                }
            ],
            "expected": "java.util.RandomAccess",
            "description": "Test case where supertype is an interface not implemented by rawType. Should return the supertype itself as it cannot be resolved further."
        }
    ]
}