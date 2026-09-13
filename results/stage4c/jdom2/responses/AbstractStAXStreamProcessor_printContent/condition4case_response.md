{
  "group": "printContent method tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with no content and isCDATA() returns true"
        }
      ],
      "expected": "printCDATA method is called with new CDATA instance",
      "description": "Test case where walker has no content and isCDATA is true, expecting printCDATA to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with no content and isCDATA() returns false"
        }
      ],
      "expected": "printText method is called with new Text instance",
      "description": "Test case where walker has no content and isCDATA is false, expecting printText to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with CDATA content"
        }
      ],
      "expected": "printCDATA method is called with CDATA content",
      "description": "Test case where walker has CDATA content, expecting printCDATA to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with Comment content"
        }
      ],
      "expected": "printComment method is called with Comment content",
      "description": "Test case where walker has Comment content, expecting printComment to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with Element content"
        }
      ],
      "expected": "printElement method is called with Element content",
      "description": "Test case where walker has Element content, expecting printElement to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with EntityRef content"
        }
      ],
      "expected": "printEntityRef method is called with EntityRef content",
      "description": "Test case where walker has EntityRef content, expecting printEntityRef to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with ProcessingInstruction content"
        }
      ],
      "expected": "printProcessingInstruction method is called with ProcessingInstruction content",
      "description": "Test case where walker has ProcessingInstruction content, expecting printProcessingInstruction to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with Text content"
        }
      ],
      "expected": "printText method is called with Text content",
      "description": "Test case where walker has Text content, expecting printText to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with DocType content"
        }
      ],
      "expected": "printDocType method is called with DocType content",
      "description": "Test case where walker has DocType content, expecting printDocType to be called."
    },
    {
      "input": [
        {
          "parameter": "walker",
          "value": "Walker with unexpected content type"
        }
      ],
      "expected": "IllegalStateException is thrown with message 'Unexpected Content '",
      "description": "Test case where walker has an unexpected content type, expecting IllegalStateException to be thrown."
    }
  ]
}