{
  "group": "printContent",
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
          "parameter": "walker",
          "value": "MockWalkerWithNullContent"
        }
      ],
      "expected": "textRaw or textCDATA called with text",
      "description": "Test case where Walker returns null content, expecting text processing."
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
          "parameter": "walker",
          "value": "MockWalkerWithCDATAContent"
        }
      ],
      "expected": "printCDATA called",
      "description": "Test case where Walker returns CDATA content, expecting printCDATA method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithCommentContent"
        }
      ],
      "expected": "printComment called",
      "description": "Test case where Walker returns Comment content, expecting printComment method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithDocTypeContent"
        }
      ],
      "expected": "printDocType called",
      "description": "Test case where Walker returns DocType content, expecting printDocType method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithElementContent"
        }
      ],
      "expected": "printElement called",
      "description": "Test case where Walker returns Element content, expecting printElement method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithEntityRefContent"
        }
      ],
      "expected": "printEntityRef called",
      "description": "Test case where Walker returns EntityRef content, expecting printEntityRef method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithProcessingInstructionContent"
        }
      ],
      "expected": "printProcessingInstruction called",
      "description": "Test case where Walker returns ProcessingInstruction content, expecting printProcessingInstruction method call."
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
          "parameter": "walker",
          "value": "MockWalkerWithTextContent"
        }
      ],
      "expected": "printText called",
      "description": "Test case where Walker returns Text content, expecting printText method call."
    }
  ]
}