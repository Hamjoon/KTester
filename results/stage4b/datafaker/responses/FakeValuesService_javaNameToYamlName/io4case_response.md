{
    "testCases": [
        {
            "group": "javaNameToYamlName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "simpleNameWith123"
                        }
                    ],
                    "expected": "simple_name_with123",
                    "description": "Test with a name containing numbers and no uppercase letters, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "Class1Name"
                        }
                    ],
                    "expected": "class1_name",
                    "description": "Test with a name that has a number and an uppercase letter, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "A"
                        }
                    ],
                    "expected": "a",
                    "description": "Test with a single uppercase letter, expecting conversion to lowercase."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "MultipleUppercaseLettersInAName"
                        }
                    ],
                    "expected": "multiple_uppercase_letters_in_a_name",
                    "description": "Test with a name that has multiple uppercase letters interspersed, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "NoUppercaseAtAll"
                        }
                    ],
                    "expected": "no_uppercase_at_all",
                    "description": "Test with a name that has uppercase at the start but no other uppercase letters, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "XMLParser"
                        }
                    ],
                    "expected": "xml_parser",
                    "description": "Test with a name that is an acronym, expecting conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "AlreadySnake_case"
                        }
                    ],
                    "expected": "already_snake_case",
                    "description": "Test with a name that is already in snake_case, expecting conversion to lowercase."
                }
            ]
        }
    ]
}