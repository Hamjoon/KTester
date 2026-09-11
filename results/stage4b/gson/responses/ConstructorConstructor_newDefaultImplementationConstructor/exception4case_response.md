{
    "output": [
        {
            "group": "Test newDefaultImplementationConstructor for invalid types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<EnumSet<MyEnum>>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "EnumSet.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case to verify that an EnumSet type returns null due to special constructor handling."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<InterfaceType>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "InterfaceType.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case to verify that an interface type returns null."
                },
                {
                    "input": [
                        {
                            "parameter": "type",
                            "value": "new TypeToken<SomeAbstractClass>()"
                        },
                        {
                            "parameter": "rawType",
                            "value": "SomeAbstractClass.class"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case to verify that an abstract class type returns null."
                }
            ]
        }
    ]
}