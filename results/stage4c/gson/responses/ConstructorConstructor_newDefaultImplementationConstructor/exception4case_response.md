{
    "group": "newDefaultImplementationConstructor Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "null"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.List"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test for null type parameter, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test for null rawType parameter, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.ArrayList"
                },
                {
                    "parameter": "rawType",
                    "value": "java.lang.Object"
                }
            ],
            "expected": "null",
            "description": "Test for rawType that is not a Collection or Map, expecting null."
        },
        {
            "input": [
                {
                    "parameter": "type",
                    "value": "java.util.EnumSet"
                },
                {
                    "parameter": "rawType",
                    "value": "java.util.EnumSet"
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
                    "value": "java.util.EnumMap"
                }
            ],
            "expected": "null",
            "description": "Test for EnumMap type which requires a special constructor, expecting null."
        }
    ]
}