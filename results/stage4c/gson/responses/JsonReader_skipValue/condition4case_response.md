{
  "group": "JsonReader.skipValue",
  "cases": [
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_NONE"
        }
      ],
      "expected": "Ensure doPeek() is called and appropriate action is taken based on its result",
      "description": "Test when peeked is PEEKED_NONE to verify doPeek() is invoked and the method handles subsequent peeked value correctly."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_BEGIN_ARRAY"
        }
      ],
      "expected": "stackSize is incremented and count is increased",
      "description": "Test when peeked is PEEKED_BEGIN_ARRAY to ensure stack is updated and count is incremented."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_BEGIN_OBJECT"
        }
      ],
      "expected": "stackSize is incremented and count is increased",
      "description": "Test when peeked is PEEKED_BEGIN_OBJECT to ensure stack is updated and count is incremented."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_END_ARRAY"
        }
      ],
      "expected": "stackSize is decremented and count is decreased",
      "description": "Test when peeked is PEEKED_END_ARRAY to ensure stack is decremented and count is decreased."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_END_OBJECT"
        }
      ],
      "expected": "stackSize is decremented, count is decreased, and pathNames is updated if count is zero",
      "description": "Test when peeked is PEEKED_END_OBJECT to ensure stack is decremented, count is decreased, and pathNames is updated correctly."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_UNQUOTED"
        }
      ],
      "expected": "skipUnquotedValue() is called",
      "description": "Test when peeked is PEEKED_UNQUOTED to ensure skipUnquotedValue() function is invoked."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_SINGLE_QUOTED"
        }
      ],
      "expected": "skipQuotedValue('') is called",
      "description": "Test when peeked is PEEKED_SINGLE_QUOTED to ensure skipQuotedValue('') function is invoked."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_DOUBLE_QUOTED"
        }
      ],
      "expected": "skipQuotedValue('\"') is called",
      "description": "Test when peeked is PEEKED_DOUBLE_QUOTED to ensure skipQuotedValue('\"') function is invoked."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_UNQUOTED_NAME"
        }
      ],
      "expected": "skipUnquotedValue() is called and pathNames is updated if count is zero",
      "description": "Test when peeked is PEEKED_UNQUOTED_NAME to ensure skipUnquotedValue() is invoked and pathNames is updated correctly."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_SINGLE_QUOTED_NAME"
        }
      ],
      "expected": "skipQuotedValue('') is called and pathNames is updated if count is zero",
      "description": "Test when peeked is PEEKED_SINGLE_QUOTED_NAME to ensure skipQuotedValue('') is invoked and pathNames is updated correctly."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_DOUBLE_QUOTED_NAME"
        }
      ],
      "expected": "skipQuotedValue('\"') is called and pathNames is updated if count is zero",
      "description": "Test when peeked is PEEKED_DOUBLE_QUOTED_NAME to ensure skipQuotedValue('\"') is invoked and pathNames is updated correctly."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_NUMBER"
        }
      ],
      "expected": "pos is incremented by peekedNumberLength",
      "description": "Test when peeked is PEEKED_NUMBER to ensure pos is incremented by peekedNumberLength."
    },
    {
      "input": [
        {
          "parameter": "peeked",
          "value": "PEEKED_EOF"
        }
      ],
      "expected": "Method returns without error",
      "description": "Test when peeked is PEEKED_EOF to ensure the method returns without processing further."
    }
  ]
}