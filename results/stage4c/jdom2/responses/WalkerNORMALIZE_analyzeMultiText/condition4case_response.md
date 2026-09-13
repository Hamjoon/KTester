{
  "group": "analyzeMultiText",
  "cases": [
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with Text Content containing only XML whitespace"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 1
        }
      ],
      "expected": "MultiText instance should remain unchanged",
      "description": "Test with Text Content that contains only XML whitespace. Ensure no text is appended to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with Text Content containing non-whitespace text"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 1
        }
      ],
      "expected": "MultiText instance should append non-whitespace text",
      "description": "Test with Text Content containing non-whitespace text. Ensure the non-whitespace text is appended to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with CDATA Content containing only XML whitespace"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 1
        }
      ],
      "expected": "MultiText instance should remain unchanged",
      "description": "Test with CDATA Content that contains only XML whitespace. Ensure no text is appended to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with CDATA Content containing non-whitespace text"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 1
        }
      ],
      "expected": "MultiText instance should append non-whitespace CDATA",
      "description": "Test with CDATA Content containing non-whitespace text. Ensure the non-whitespace CDATA is appended to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with EntityRef Content"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 1
        }
      ],
      "expected": "MultiText instance should append raw EntityRef",
      "description": "Test with EntityRef Content. Ensure the raw EntityRef is appended to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MultiText instance with mixed Content types"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 3
        }
      ],
      "expected": "MultiText instance should correctly append mixed content with spaces where needed",
      "description": "Test with a mix of Text, CDATA, and EntityRef Content. Ensure proper handling of whitespace and content appending."
    }
  ]
}