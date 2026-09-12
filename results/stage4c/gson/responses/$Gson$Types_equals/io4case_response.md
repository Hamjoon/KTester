{
    "group": "equals(Type, Type) additional method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, Class.forName('java.lang.Object'))"
                },
                {
                    "parameter": "b",
                    "value": "ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, Class.forName('java.lang.Object'))"
                }
            ],
            "expected": true,
            "description": "Both types are identical ParameterizedType instances with owner type, should return true."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, Class.forName('java.lang.Object'))"
                },
                {
                    "parameter": "b",
                    "value": "ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, Class.forName('java.lang.Integer'))"
                }
            ],
            "expected": false,
            "description": "Different ParameterizedType instances with different owner types, should return false."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "GenericArrayTypeImpl(ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, null))"
                },
                {
                    "parameter": "b",
                    "value": "GenericArrayTypeImpl(ParameterizedTypeImpl(Class.forName('java.util.List'), new Type[]{Class.forName('java.lang.String')}, null))"
                }
            ],
            "expected": true,
            "description": "Both types are identical GenericArrayType instances with ParameterizedType component types, should return true."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "WildcardTypeImpl(new Type[]{Class.forName('java.lang.Number')}, new Type[]{Class.forName('java.lang.Integer')})"
                },
                {
                    "parameter": "b",
                    "value": "WildcardTypeImpl(new Type[]{Class.forName('java.lang.Number')}, new Type[]{Class.forName('java.lang.Double')})"
                }
            ],
            "expected": false,
            "description": "Different WildcardType instances with different lower bounds, should return false."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "TypeVariableImpl('T', new Type[]{Class.forName('java.lang.Number')}, Class.forName('java.lang.Object'))"
                },
                {
                    "parameter": "b",
                    "value": "TypeVariableImpl('T', new Type[]{Class.forName('java.lang.Number')}, Class.forName('java.lang.Object'))"
                }
            ],
            "expected": true,
            "description": "Both types are identical TypeVariable instances with the same bounds and generic declaration, should return true."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "TypeVariableImpl('T', new Type[]{Class.forName('java.lang.Number')}, Class.forName('java.lang.Object'))"
                },
                {
                    "parameter": "b",
                    "value": "TypeVariableImpl('T', new Type[]{Class.forName('java.lang.Double')}, Class.forName('java.lang.Object'))"
                }
            ],
            "expected": false,
            "description": "Different TypeVariable instances with different bounds, should return false."
        }
    ]
}