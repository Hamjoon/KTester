{
  "group": "ObjectTypeAdapter.read(JsonReader) Test Cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "[]"
        }
      ],
      "expected": "Empty List",
      "description": "Test reading an empty JSON array to ensure it returns an empty List."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "{}"
        }
      ],
      "expected": "Empty Map",
      "description": "Test reading an empty JSON object to ensure it returns an empty Map."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "[1, 2, 3]"
        }
      ],
      "expected": "[1, 2, 3]",
      "description": "Test reading a simple JSON array of integers to ensure it returns the correct List."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "{\"key1\": \"value1\", \"key2\": \"value2\"}"
        }
      ],
      "expected": "{\"key1\": \"value1\", \"key2\": \"value2\"}",
      "description": "Test reading a simple JSON object to ensure it returns the correct Map."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "[{\"nestedKey\": \"nestedValue\"}]"
        }
      ],
      "expected": "[{\"nestedKey\": \"nestedValue\"}]",
      "description": "Test reading a JSON array with a nested object to ensure it correctly nests the object within a List."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "{\"outerKey\": [\"listItem1\", \"listItem2\"]}"
        }
      ],
      "expected": "{\"outerKey\": [\"listItem1\", \"listItem2\"]}",
      "description": "Test reading a JSON object with a nested array to ensure it correctly nests the List within a Map."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "[{\"key\": [\"nestedListItem1\", \"nestedListItem2\"]}]"
        }
      ],
      "expected": "[{\"key\": [\"nestedListItem1\", \"nestedListItem2\"]}]",
      "description": "Test reading a JSON array with a nested object containing a list to ensure proper nesting."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "true"
        }
      ],
      "expected": "true",
      "description": "Test reading a JSON boolean value to ensure it returns the correct boolean."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "null"
        }
      ],
      "expected": "null",
      "description": "Test reading a JSON null value to ensure it returns null."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "\"stringValue\""
        }
      ],
      "expected": "\"stringValue\"",
      "description": "Test reading a JSON string value to ensure it returns the correct string."
    },
    {
      "input": [
        {
          "parameter": "JsonReader",
          "value": "123"
        }
      ],
      "expected": "123",
      "description": "Test reading a JSON number value to ensure it returns the correct number."
    }
  ]
}