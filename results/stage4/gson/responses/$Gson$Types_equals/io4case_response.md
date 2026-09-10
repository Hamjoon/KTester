{
    "testCases": [
        {
            "group": "equals method parameterized type owner comparison",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new ParameterizedTypeImpl(List.class, String.class, String.class)"
                        },
                        {
                            "parameter": "b",
                            "value": "new ParameterizedTypeImpl(List.class, String.class, Integer.class)"
                        }
                    ],
                    "expected": false,
                    "description": "Test that equals returns false when comparing parameterized types with different owner types."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new ParameterizedTypeImpl(Map.class, String.class, Integer.class)"
                        },
                        {
                            "parameter": "b",
                            "value": "new ParameterizedTypeImpl(Map.class, String.class, Integer.class)"
                        }
                    ],
                    "expected": true,
                    "description": "Test that equals returns true when comparing parameterized types with the same owner and actual type arguments."
                }
            ]
        },
        {
            "group": "equals method generic array type comparison with wildcards",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new GenericArrayTypeImpl(new WildcardTypeImpl(new Type[]{String.class}, new Type[]{}))"
                        },
                        {
                            "parameter": "b",
                            "value": "new GenericArrayTypeImpl(new WildcardTypeImpl(new Type[]{Integer.class}, new Type[]{}))"
                        }
                    ],
                    "expected": false,
                    "description": "Test that equals returns false when comparing generic array types that have wildcard component types with different upper bounds."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new GenericArrayTypeImpl(new WildcardTypeImpl(new Type[]{String.class}, new Type[]{}))"
                        },
                        {
                            "parameter": "b",
                            "value": "new GenericArrayTypeImpl(new WildcardTypeImpl(new Type[]{String.class}, new Type[]{}))"
                        }
                    ],
                    "expected": true,
                    "description": "Test that equals returns true when comparing generic array types that have the same wildcard component types."
                }
            ]
        },
        {
            "group": "equals method type variable comparison with different names",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new TypeVariableImpl(String.class, 'X')"
                        },
                        {
                            "parameter": "b",
                            "value": "new TypeVariableImpl(String.class, 'Y')"
                        }
                    ],
                    "expected": false,
                    "description": "Test that equals returns false when comparing type variables with different names."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new TypeVariableImpl(String.class, 'X')"
                        },
                        {
                            "parameter": "b",
                            "value": "new TypeVariableImpl(String.class, 'X')"
                        }
                    ],
                    "expected": true,
                    "description": "Test that equals returns true when comparing type variables with the same declaration and name."
                }
            ]
        },
        {
            "group": "equals method unsupported types edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new Object()"
                        },
                        {
                            "parameter": "b",
                            "value": "new String('test')"
                        }
                    ],
                    "expected": false,
                    "description": "Test that equals returns false when comparing an unsupported type with a supported type."
                },
                {
                    "input": [
                        {
                            "parameter": "a",
                            "value": "new Object()"
                        },
                        {
                            "parameter": "b",
                            "value": "new Object()"
                        }
                    ],
                    "expected": false,
                    "description": "Test that equals returns false when comparing two different instances of unsupported types."
                }
            ]
        }
    ]
}