{
    "test cases": [
        {
            "group": "read method - basic functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with a simple JSON object {\"key\":\"value\"}"
                        }
                    ],
                    "expected": "{\"key\":\"value\"}",
                    "description": "Test reading a simple JSON object to ensure correct parsing of a terminal value."
                },
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with a simple JSON array [1, 2, 3]"
                        }
                    ],
                    "expected": "[1, 2, 3]",
                    "description": "Test reading a simple JSON array to ensure correct parsing of terminal values in an array."
                }
            ]
        },
        {
            "group": "read method - nested structures",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with nested JSON object {\"outer\":{\"inner\":\"value\"}}"
                        }
                    ],
                    "expected": "{\"outer\":{\"inner\":\"value\"}}",
                    "description": "Test reading a nested JSON object to ensure correct handling of nested structures."
                },
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with nested JSON array [[1, 2], [3, 4]]"
                        }
                    ],
                    "expected": "[[1, 2], [3, 4]]",
                    "description": "Test reading a nested JSON array to ensure correct handling of nested arrays."
                }
            ]
        },
        {
            "group": "read method - conditional branches",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with an empty JSON object {}"
                        }
                    ],
                    "expected": "{}",
                    "description": "Test reading an empty JSON object to verify handling of empty structures."
                },
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with an empty JSON array []"
                        }
                    ],
                    "expected": "[]",
                    "description": "Test reading an empty JSON array to verify handling of empty structures."
                },
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with a JSON object containing multiple entries {\"key1\":\"value1\", \"key2\":\"value2\"}"
                        }
                    ],
                    "expected": "{\"key1\":\"value1\", \"key2\":\"value2\"}",
                    "description": "Test reading a JSON object with multiple key-value pairs to verify correct parsing."
                }
            ]
        },
        {
            "group": "read method - error handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "JsonReader with invalid JSON input"
                        }
                    ],
                    "expected": "IOException",
                    "description": "Test reading invalid JSON input to verify that an IOException is thrown."
                }
            ]
        }
    ]
}