{
    "group": "equals method exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "UnsupportedType"
                },
                {
                    "parameter": "b",
                    "value": "AnotherUnsupportedType"
                }
            ],
            "expected": false,
            "description": "Test when Type parameters are of completely unsupported types, should return false."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "null"
                },
                {
                    "parameter": "b",
                    "value": "String"
                }
            ],
            "expected": false,
            "description": "Test when one Type parameter is null and the other is a valid Class type, should return false."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "String"
                },
                {
                    "parameter": "b",
                    "value": "null"
                }
            ],
            "expected": false,
            "description": "Test when one Type parameter is a valid Class type and the other is null, should return false."
        },
        {
            "input": [
                {
                    "parameter": "a",
                    "value": "Object"
                },
                {
                    "parameter": "b",
                    "value": "GenericArrayType"
                }
            ],
            "expected": false,
            "description": "Test when one Type parameter is an Object and the other is a GenericArrayType, should return false."
        }
    ]
}