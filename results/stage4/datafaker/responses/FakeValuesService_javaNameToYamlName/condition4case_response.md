{
    "group": "javaNameToYamlName Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simpleName"
                }
            ],
            "expected": "simpleName",
            "description": "Test with a simple lowercase name to verify that it returns the same string as there are no uppercase letters."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SimpleName"
                }
            ],
            "expected": "simple_name",
            "description": "Test with a name starting with an uppercase letter to ensure it converts to snake_case format."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "AnotherExampleName"
                }
            ],
            "expected": "another_example_name",
            "description": "Test with a name containing multiple uppercase letters to check proper conversion to snake_case format."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Single"
                }
            ],
            "expected": "single",
            "description": "Test with a single uppercase letter to ensure it converts to lowercase correctly."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "already_snake_case"
                }
            ],
            "expected": "already_snake_case",
            "description": "Test with an already snake_case string to verify that it returns the same string without modification."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test with an empty string to verify that it returns an empty string."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "XMLHttpRequest"
                }
            ],
            "expected": "xml_http_request",
            "description": "Test with a name containing mixed case letters to ensure proper conversion to snake_case format."
        }
    ]
}