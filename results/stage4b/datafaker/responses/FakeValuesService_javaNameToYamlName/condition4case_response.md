{
    "testCases": [
        {
            "group": "javaNameToYamlName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "simpleName"
                        }
                    ],
                    "expected": "simpleName",
                    "description": "Test with a simple name that has no uppercase letters, expecting the same string as output."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "AnotherExample"
                        }
                    ],
                    "expected": "another_example",
                    "description": "Test with a name that has uppercase letters at the beginning of words, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "ExampleWithMultipleUppercaseLetters"
                        }
                    ],
                    "expected": "example_with_multiple_uppercase_letters",
                    "description": "Test with a name that has multiple uppercase letters, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "SingleUppercase"
                        }
                    ],
                    "expected": "single_uppercase",
                    "description": "Test with a name that has a single uppercase letter at the beginning, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "lowercase"
                        }
                    ],
                    "expected": "lowercase",
                    "description": "Test with a name that is entirely lowercase, expecting the same string as output."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string, expecting the same empty string as output."
                }
            ]
        }
    ]
}