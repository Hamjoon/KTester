{
  "group": "matches method test cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(EXACT, '\"test\"')"
        }
      ],
      "expected": true,
      "description": "Test EXACT pattern with matching textual value."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(PREFIX, '\"te')"
        }
      ],
      "expected": true,
      "description": "Test PREFIX pattern with matching textual value."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "AnythingBut(ANYTHING_BUT, [\"'test'\"])"
        }
      ],
      "expected": true,
      "description": "Test ANYTHING_BUT pattern with non-matching textual value."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "AnythingButEqualsIgnoreCase(ANYTHING_BUT_IGNORE_CASE, [\"'TEST'\"])"
        }
      ],
      "expected": false,
      "description": "Test ANYTHING_BUT_IGNORE_CASE pattern with case-insensitive match."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(ANYTHING_BUT_SUFFIX, '\"est\"')"
        }
      ],
      "expected": false,
      "description": "Test ANYTHING_BUT_SUFFIX pattern with matching suffix."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(ANYTHING_BUT_PREFIX, '\"te')"
        }
      ],
      "expected": false,
      "description": "Test ANYTHING_BUT_PREFIX pattern with matching prefix."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "NumericJsonNode(42)"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(NUMERIC_EQ, '42')"
        }
      ],
      "expected": true,
      "description": "Test NUMERIC_EQ pattern with matching numeric value."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(EQUALS_IGNORE_CASE, '\"TEST\"')"
        }
      ],
      "expected": true,
      "description": "Test EQUALS_IGNORE_CASE pattern with case-insensitive match."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "ValuePatterns(WILDCARD, '\"t*st\"')"
        }
      ],
      "expected": true,
      "description": "Test WILDCARD pattern with matching wildcard pattern."
    },
    {
      "input": [
        {
          "parameter": "val",
          "value": "TextualJsonNode('\"test\"')"
        },
        {
          "parameter": "pattern",
          "value": "UnsupportedPatternType()"
        }
      ],
      "expected": "RuntimeException",
      "description": "Test default case with unsupported pattern type."
    }
  ]
}