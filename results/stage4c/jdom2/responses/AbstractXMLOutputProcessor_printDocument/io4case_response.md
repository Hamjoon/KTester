{
  "group": "Additional printDocument Method Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "out",
          "value": "MockWriter"
        },
        {
          "parameter": "fstack",
          "value": "MockFormatStack"
        },
        {
          "parameter": "nstack",
          "value": "MockNamespaceStack"
        },
        {
          "parameter": "doc",
          "value": "Document with a CDATA content"
        }
      ],
      "expected": "CDATA content is ignored",
      "description": "Test with a document containing a CDATA content to ensure it is ignored as per method logic"
    },
    {
      "input": [
        {
          "parameter": "out",
          "value": "MockWriter"
        },
        {
          "parameter": "fstack",
          "value": "MockFormatStack"
        },
        {
          "parameter": "nstack",
          "value": "MockNamespaceStack"
        },
        {
          "parameter": "doc",
          "value": "Document with mixed content including Element, Comment, and whitespace Text"
        }
      ],
      "expected": "Element and Comment are processed, whitespace Text is written",
      "description": "Test with a document containing mixed content types to verify correct processing and writing behavior"
    },
    {
      "input": [
        {
          "parameter": "out",
          "value": "MockWriter"
        },
        {
          "parameter": "fstack",
          "value": "MockFormatStack"
        },
        {
          "parameter": "nstack",
          "value": "MockNamespaceStack"
        },
        {
          "parameter": "doc",
          "value": "Document with EntityRef content"
        }
      ],
      "expected": "EntityRef content is ignored",
      "description": "Test with a document containing an EntityRef content to ensure it is ignored as per method logic"
    },
    {
      "input": [
        {
          "parameter": "out",
          "value": "MockWriter"
        },
        {
          "parameter": "fstack",
          "value": "MockFormatStack"
        },
        {
          "parameter": "nstack",
          "value": "MockNamespaceStack"
        },
        {
          "parameter": "doc",
          "value": "Document with root element and nested elements"
        }
      ],
      "expected": "Nested elements are processed and written correctly",
      "description": "Test with a document containing a root element with nested elements to verify recursive processing"
    },
    {
      "input": [
        {
          "parameter": "out",
          "value": "MockWriter"
        },
        {
          "parameter": "fstack",
          "value": "MockFormatStack"
        },
        {
          "parameter": "nstack",
          "value": "MockNamespaceStack"
        },
        {
          "parameter": "doc",
          "value": "Document with a single root element and no content"
        }
      ],
      "expected": "printElement is called with the root element",
      "description": "Test with a document containing only a single root element to verify element processing"
    }
  ]
}