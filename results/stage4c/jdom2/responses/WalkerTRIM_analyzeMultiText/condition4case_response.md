{
  "group": "analyzeMultiText Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 0
        }
      ],
      "expected": "MultiText remains unchanged",
      "description": "Test with zero length, expecting no changes to MultiText."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance with Text containing only whitespaces"
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
      "expected": "MultiText remains unchanged",
      "description": "Test with a single Text content containing only whitespace, expecting no changes."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance with Text containing non-whitespace"
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
      "expected": "MultiText contains trimmed Text content",
      "description": "Test with a single Text content containing non-whitespace, expecting it to be appended with Trim.BOTH."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance with Text and CDATA contents"
        },
        {
          "parameter": "offset",
          "value": 0
        },
        {
          "parameter": "len",
          "value": 2
        }
      ],
      "expected": "MultiText contains both Text and CDATA contents trimmed appropriately",
      "description": "Test with a Text and a CDATA content, expecting Text with Trim.LEFT and CDATA with Trim.RIGHT."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance with EntityRef content"
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
      "expected": "MultiText contains the EntityRef content as raw",
      "description": "Test with EntityRef content, expecting it to be appended as raw."
    },
    {
      "input": [
        {
          "parameter": "MultiText",
          "value": "MockMultiText instance with mixed contents"
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
      "expected": "MultiText contains all contents trimmed appropriately",
      "description": "Test with mixed contents including Text, CDATA, and EntityRef, expecting each to be appended according to their type and position."
    }
  ]
}