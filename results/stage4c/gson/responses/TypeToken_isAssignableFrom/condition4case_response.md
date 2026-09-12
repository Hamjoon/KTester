{
    "group": "isAssignableFrom Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "from",
                    "value": null
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with 'from' as null to ensure it returns false."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.List<java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with 'from' and 'to' as identical parameterized types to ensure it returns true."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.ArrayList<java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with a subclass 'from' and superclass 'to' with matching type arguments to ensure it returns true."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.ArrayList<java.lang.Integer>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with a subclass 'from' and superclass 'to' with non-matching type arguments to ensure it returns false."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.HashMap<K, V>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Map<java.lang.String, java.lang.Integer>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with a class 'from' that implements a parameterized interface 'to' with type variables to ensure it returns true if type variables are correctly resolved."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.HashMap<java.lang.String, java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Map<java.lang.String, java.lang.Integer>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with a class 'from' that implements a parameterized interface 'to' with non-matching type arguments to ensure it returns false."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.List<E>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Collection<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with a class 'from' that implements a parameterized interface 'to' with type variables to ensure correct resolution and return true."
        }
    ]
}