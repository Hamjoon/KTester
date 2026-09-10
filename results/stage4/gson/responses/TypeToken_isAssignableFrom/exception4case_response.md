{
    "group": "isAssignableFrom - Type Variable Resolution",
    "cases": [
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "SomeParameterizedType<T>"
                },
                {
                    "parameter": "to",
                    "value": "SomeParameterizedType<SomeType>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{\"T\":\"SomeType\"}"
                }
            ],
            "expected": true,
            "description": "Test when 'from' is a parameterized type with a type variable that maps to the type in 'to'. The method should return true."
        },
        {
            "input": [
                {
                    "parameter": "from",
                    "value": "SomeParameterizedType<T>"
                },
                {
                    "parameter": "to",
                    "value": "SomeParameterizedType<SomeOtherType>"
                },
                {
                    "parameter": "typeVarMap",
                    "value": "{\"T\":\"SomeType\"}"
                }
            ],
            "expected": false,
            "description": "Test when 'from' is a parameterized type with a type variable that maps to one type, but 'to' has a different type. The method should return false."
        }
    ]
}