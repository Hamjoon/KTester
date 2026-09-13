{
  "group": "PrintDocumentTests",
  "cases": [
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": true,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": true,
            "content": []
          }
        }
      ],
      "expected": "out.add(eventfactory.createStartDocument(null, null))",
      "description": "Test case where the document declaration is omitted and the document has a root element with no content."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": true,
            "encoding": "UTF-8",
            "lineSeparator": "\n"
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": true,
            "content": []
          }
        }
      ],
      "expected": [
        "out.add(eventfactory.createStartDocument(null, \"1.0\"))",
        "out.add(eventfactory.createCharacters(\"\\n\"))"
      ],
      "description": "Test case where the encoding is omitted, and a line separator is specified."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": "\n"
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": true,
            "content": []
          }
        }
      ],
      "expected": [
        "out.add(eventfactory.createStartDocument(\"UTF-8\", \"1.0\"))",
        "out.add(eventfactory.createCharacters(\"\\n\"))"
      ],
      "description": "Test case where neither declaration nor encoding is omitted, and a line separator is specified."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": false,
            "content": [
              {
                "type": "Comment",
                "content": "This is a comment"
              }
            ]
          }
        }
      ],
      "expected": "printComment is called with the comment content",
      "description": "Test case where the document has no root element, and the content includes a comment."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": false,
            "content": [
              {
                "type": "DocType",
                "content": "DOCTYPE html"
              }
            ]
          }
        }
      ],
      "expected": "printDocType is called with the doctype content",
      "description": "Test case where the document has no root element, and the content includes a doctype."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": false,
            "content": [
              {
                "type": "Element",
                "content": "<element></element>"
              }
            ]
          }
        }
      ],
      "expected": "printElement is called with the element content",
      "description": "Test case where the document has no root element, and the content includes an element."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": false,
            "content": [
              {
                "type": "ProcessingInstruction",
                "content": "<?xml-stylesheet type=\"text/xsl\" href=\"style.xsl\"?>"
              }
            ]
          }
        }
      ],
      "expected": "printProcessingInstruction is called with the processing instruction content",
      "description": "Test case where the document has no root element, and the content includes a processing instruction."
    },
    {
      "input": [
        {
          "parameter": "fstack",
          "value": {
            "omitDeclaration": false,
            "omitEncoding": false,
            "encoding": "UTF-8",
            "lineSeparator": null
          }
        },
        {
          "parameter": "doc",
          "value": {
            "hasRootElement": false,
            "content": [
              {
                "type": "Text",
                "content": "Some text content"
              }
            ]
          }
        }
      ],
      "expected": "out.add(eventfactory.createCharacters(\"Some text content\"))",
      "description": "Test case where the document has no root element, and the content includes text."
    }
  ]
}