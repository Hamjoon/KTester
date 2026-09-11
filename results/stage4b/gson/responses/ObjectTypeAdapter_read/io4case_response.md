{
    "test cases": [
        {
            "group": "read method with JSON array of mixed types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "mockedJsonReaderWithMixedArray"
                        }
                    ],
                    "expected": "List<Object> containing mixed types (e.g., string, number, boolean)",
                    "description": "Test the read method with a JSON array containing mixed types to ensure it correctly parses and returns a List with various data types."
                }
            ]
        },
        {
            "group": "read method with JSON object with nested arrays",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "mockedJsonReaderWithObjectAndNestedArray"
                        }
                    ],
                    "expected": "Map<String, Object> containing a nested List",
                    "description": "Test the read method with a JSON object that includes nested arrays to verify that it correctly parses and returns a Map with nested List."
                }
            ]
        },
        {
            "group": "read method with JSON object with empty array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "mockedJsonReaderWithEmptyArrayInObject"
                        }
                    ],
                    "expected": "Map<String, Object> containing an empty List",
                    "description": "Test the read method with a JSON object that has an empty array to ensure it correctly parses and represents it as an empty List."
                }
            ]
        },
        {
            "group": "read method with JSON object with null values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "mockedJsonReaderWithObjectAndNullValues"
                        }
                    ],
                    "expected": "Map<String, Object> containing keys with null values",
                    "description": "Test the read method with a JSON object that includes null values for some keys to verify that it correctly parses and returns a Map with null entries."
                }
            ]
        },
        {
            "group": "read method with deeply nested JSON structure",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "JsonReader",
                            "value": "mockedJsonReaderWithDeeplyNestedStructure"
                        }
                    ],
                    "expected": "Map<String, Object> with deeply nested structure",
                    "description": "Test the read method with a deeply nested JSON structure to ensure it can handle multiple levels of nesting without errors."
                }
            ]
        }
    ]
}