{
  "group": "resolve method exception tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "context",
          "value": "null"
        },
        {
          "parameter": "contextRawType",
          "value": "SomeClass.class"
        },
        {
          "parameter": "toResolve",
          "value": "SomeType"
        },
        {
          "parameter": "visitedTypeVariables",
          "value": "{}"
        }
      ],
      "expected": "NullPointerException",
      "description": "Test resolving with null as the context parameter."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "SomeClass"
        },
        {
          "parameter": "contextRawType",
          "value": "null"
        },
        {
          "parameter": "toResolve",
          "value": "SomeType"
        },
        {
          "parameter": "visitedTypeVariables",
          "value": "{}"
        }
      ],
      "expected": "NullPointerException",
      "description": "Test resolving with null as the contextRawType parameter."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "SomeClass"
        },
        {
          "parameter": "contextRawType",
          "value": "SomeClass.class"
        },
        {
          "parameter": "toResolve",
          "value": "null"
        },
        {
          "parameter": "visitedTypeVariables",
          "value": "{}"
        }
      ],
      "expected": "NullPointerException",
      "description": "Test resolving with null as the toResolve parameter."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "SomeClass"
        },
        {
          "parameter": "contextRawType",
          "value": "SomeClass.class"
        },
        {
          "parameter": "toResolve",
          "value": "SomeType"
        },
        {
          "parameter": "visitedTypeVariables",
          "value": "null"
        }
      ],
      "expected": "NullPointerException",
      "description": "Test resolving with null as the visitedTypeVariables parameter."
    }
  ]
}