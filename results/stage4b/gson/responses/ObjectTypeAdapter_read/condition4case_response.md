{
  "test cases": [
    {
      "group": "read method with JSON array input",
      "cases": [
        {
          "input": [
            {
              "parameter": "JsonReader",
              "value": "mockedJsonReaderWithArray"
            }
          ],
          "expected": "List<Object> containing parsed values",
          "description": "Test the read method with a JSON array input to verify that it correctly parses and returns a List."
        }
      ]
    },
    {
      "group": "read method with JSON object input",
      "cases": [
        {
          "input": [
            {
              "parameter": "JsonReader",
              "value": "mockedJsonReaderWithObject"
            }
          ],
          "expected": "Map<String, Object> containing parsed key-value pairs",
          "description": "Test the read method with a JSON object input to verify that it correctly parses and returns a Map."
        }
      ]
    },
    {
      "group": "read method with nested JSON structure",
      "cases": [
        {
          "input": [
            {
              "parameter": "JsonReader",
              "value": "mockedJsonReaderWithNestedStructure"
            }
          ],
          "expected": "List<Object> containing nested values",
          "description": "Test the read method with a nested JSON structure to ensure it handles multiple levels of nesting correctly."
        }
      ]
    },
    {
      "group": "read method with terminal JSON values",
      "cases": [
        {
          "input": [
            {
              "parameter": "JsonReader",
              "value": "mockedJsonReaderWithTerminalValue"
            }
          ],
          "expected": "Parsed terminal value",
          "description": "Test the read method with a JSON input that contains terminal values (like strings, numbers) to ensure it correctly parses them."
        }
      ]
    },
    {
      "group": "read method with empty JSON input",
      "cases": [
        {
          "input": [
            {
              "parameter": "JsonReader",
              "value": "mockedJsonReaderWithEmptyInput"
            }
          ],
          "expected": "null",
          "description": "Test the read method with an empty JSON input to verify that it returns null or handles it gracefully."
        }
      ]
    }
  ]
}