{
    "testCases": [
        {
            "group": "getValueType",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "@"
                        }
                    ],
                    "expected": "java.lang.Object",
                    "description": "Test case for character '@' which should return OBJECT_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": ":"
                        }
                    ],
                    "expected": "java.lang.String",
                    "description": "Test case for character ':' which should return STRING_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "%"
                        }
                    ],
                    "expected": "java.lang.Number",
                    "description": "Test case for character '%' which should return NUMBER_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "+"
                        }
                    ],
                    "expected": "java.lang.Class",
                    "description": "Test case for character '+' which should return CLASS_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "#"
                        }
                    ],
                    "expected": "java.util.Date",
                    "description": "Test case for character '#' which should return DATE_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "<"
                        }
                    ],
                    "expected": "java.io.FileInputStream",
                    "description": "Test case for character '<' which should return EXISTING_FILE_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": ">"
                        }
                    ],
                    "expected": "java.io.File",
                    "description": "Test case for character '>' which should return FILE_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "*"
                        }
                    ],
                    "expected": "java.io.File[]",
                    "description": "Test case for character '*' which should return FILES_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "/"
                        }
                    ],
                    "expected": "java.net.URL",
                    "description": "Test case for character '/' which should return URL_VALUE."
                },
                {
                    "input": [
                        {
                            "parameter": "ch",
                            "value": "Z"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case for an invalid character 'Z' which should return null."
                }
            ]
        }
    ]
}