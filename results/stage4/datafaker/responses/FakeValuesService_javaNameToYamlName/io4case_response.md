{
    "testCases": [
        {
            "group": "javaNameToYamlName Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "HTTPRequest"
                        }
                    ],
                    "expected": "http_request",
                    "description": "Test with a name containing uppercase letters at the beginning and middle to ensure proper conversion to snake_case format."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "URLParser"
                        }
                    ],
                    "expected": "url_parser",
                    "description": "Test with a name starting with an uppercase letter followed by another uppercase letter to check proper conversion to snake_case format."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "JSONData"
                        }
                    ],
                    "expected": "json_data",
                    "description": "Test with a name that includes an acronym in uppercase to verify it converts to snake_case correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "testCase"
                        }
                    ],
                    "expected": "test_case",
                    "description": "Test with a name that has a mix of camelCase to ensure it converts to snake_case format."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "CamelCaseExample"
                        }
                    ],
                    "expected": "camel_case_example",
                    "description": "Test with a name that has multiple uppercase letters in camelCase format to check proper conversion to snake_case."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "A"
                        }
                    ],
                    "expected": "a",
                    "description": "Test with a single uppercase letter to ensure it converts to lowercase correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "expression",
                            "value": "A_B_C"
                        }
                    ],
                    "expected": "a_b_c",
                    "description": "Test with an already snake_case string containing uppercase letters to ensure it converts to lowercase."
                }
            ]
        }
    ]
}