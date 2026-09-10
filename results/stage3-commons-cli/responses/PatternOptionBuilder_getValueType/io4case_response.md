{
    "test cases": [
        {
            "group": "getValueType",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "A"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character 'A' which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "1"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '1' which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "!"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '!' which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "@"
                        }
                    ],
                    "expected": "java.lang.Object",
                    "description": "Re-testing character '@' which should return OBJECT_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": ":"
                        }
                    ],
                    "expected": "java.lang.String",
                    "description": "Re-testing character ':' which should return STRING_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "%"
                        }
                    ],
                    "expected": "java.lang.Number",
                    "description": "Re-testing character '%' which should return NUMBER_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "+"
                        }
                    ],
                    "expected": "java.lang.Class",
                    "description": "Re-testing character '+' which should return CLASS_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "#"
                        }
                    ],
                    "expected": "java.util.Date",
                    "description": "Re-testing character '#' which should return DATE_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "<"
                        }
                    ],
                    "expected": "java.io.FileInputStream",
                    "description": "Re-testing character '<' which should return EXISTING_FILE_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": ">"
                        }
                    ],
                    "expected": "java.io.File",
                    "description": "Re-testing character '>' which should return FILE_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "*"
                        }
                    ],
                    "expected": "java.io.File[]",
                    "description": "Re-testing character '*' which should return FILES_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "/"
                        }
                    ],
                    "expected": "java.net.URL",
                    "description": "Re-testing character '/' which should return URL_VALUE to ensure coverage."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": " "
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character ' ' (space) which should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "\n"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character '\\n' (newline) which should return null."
                }
            ]
        }
    ]
}