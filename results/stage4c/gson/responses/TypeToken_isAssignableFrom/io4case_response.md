{
    "group": "isAssignableFrom Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.List<java.lang.Integer>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.Number>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with 'from' having a subtype of 'to's type argument to ensure it returns false due to type variance."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.List<java.lang.Number>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<? extends java.lang.Number>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with 'from' and 'to' having wildcard type arguments to ensure it returns true if they are compatible."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.List<? super java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.List<java.lang.Object>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with 'from' having a wildcard type argument with 'super' bound to ensure it returns false when 'to' has a specific type."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.HashSet<java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Set<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with 'from' as a subclass implementing 'to' interface with matching type arguments to ensure it returns true."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.HashSet<java.lang.Integer>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Set<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with 'from' as a subclass implementing 'to' interface with non-matching type arguments to ensure it returns false."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.TreeSet<java.lang.String>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Set<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": true,
            "description": "Test with 'from' as a subclass implementing 'to' interface with matching type arguments to ensure it returns true."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "java.util.TreeSet<java.lang.Integer>"
                },
                {
                    "parameter": "to",
                    "value": "java.util.Set<java.lang.String>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{}"
                }
            ],
            "expected": false,
            "description": "Test with 'from' as a subclass implementing 'to' interface with non-matching type arguments to ensure it returns false."
        }
    ]
}