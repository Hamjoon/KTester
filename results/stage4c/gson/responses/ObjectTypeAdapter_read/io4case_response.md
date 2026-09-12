{
    "group": "ObjectTypeAdapter.read(JsonReader) Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "[{\"key\": {\"nestedKey\": \"nestedValue\"}}]"
                }
            ],
            "expected": "[{\"key\": {\"nestedKey\": \"nestedValue\"}}]",
            "description": "Test reading a JSON array with a nested object containing another nested object to ensure proper double nesting."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "{\"key\": {\"nestedKey\": [1, 2, 3]}}"
                }
            ],
            "expected": "{\"key\": {\"nestedKey\": [1, 2, 3]}}",
            "description": "Test reading a JSON object with a nested object containing a list to ensure proper nesting."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "[{\"key\": {\"nestedKey\": null}}]"
                }
            ],
            "expected": "[{\"key\": {\"nestedKey\": null}}]",
            "description": "Test reading a JSON array with a nested object containing a null value to ensure it correctly represents the null."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "[[{\"deeplyNestedKey\": \"deepValue\"}]]"
                }
            ],
            "expected": "[[{\"deeplyNestedKey\": \"deepValue\"}]]",
            "description": "Test reading a JSON array with multiple levels of nested arrays and objects to ensure deep nesting is handled."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "{\"key1\": \"value1\", \"key2\": {\"nestedKey\": {\"deepNestedKey\": 123}}}"
                }
            ],
            "expected": "{\"key1\": \"value1\", \"key2\": {\"nestedKey\": {\"deepNestedKey\": 123}}}",
            "description": "Test reading a complex JSON object with multiple levels of nested objects to ensure all levels are parsed correctly."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "[{\"key\": [null, true, false, \"string\", 456.78]}]"
                }
            ],
            "expected": "[{\"key\": [null, true, false, \"string\", 456.78]}]",
            "description": "Test reading a JSON array with a nested object containing a list of mixed types to ensure all types are handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "JsonReader",
                    "value": "{\"emptyArray\": [], \"emptyObject\": {}}"
                }
            ],
            "expected": "{\"emptyArray\": [], \"emptyObject\": {}}",
            "description": "Test reading a JSON object with empty nested array and object to ensure they are represented correctly."
        }
    ]
}