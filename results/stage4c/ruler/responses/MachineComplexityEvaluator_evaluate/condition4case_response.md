{
  "group": "evaluate(ByteState) Test Cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "state",
          "value": "ByteState with no transitions"
        }
      ],
      "expected": 0,
      "description": "Test case where the ByteState has no transitions. This should return 0 as there's no complexity."
    },
    {
      "input": [
        {
          "parameter": "state",
          "value": "ByteState with transitions, no wildcards"
        }
      ],
      "expected": "calculated maxSize",
      "description": "ByteState has transitions but none lead to any wildcard patterns. The expected output should be the calculated maxSize based on transitions."
    },
    {
      "input": [
        {
          "parameter": "state",
          "value": "ByteState with wildcard transitions"
        }
      ],
      "expected": "maxComplexity",
      "description": "ByteState has transitions that lead to wildcard patterns. The size should exceed maxComplexity, returning maxComplexity."
    },
    {
      "input": [
        {
          "parameter": "state",
          "value": "ByteState with cyclic transitions"
        }
      ],
      "expected": "calculated maxSize",
      "description": "ByteState has cyclic transitions. The function should handle cycles without infinite loops and return the calculated maxSize."
    },
    {
      "input": [
        {
          "parameter": "state",
          "value": "ByteState with next name states"
        }
      ],
      "expected": "maxSizeFromNextNameStates",
      "description": "ByteState transitions lead to other NameStates. The function should recursively calculate complexity and return the max size from these states."
    }
  ]
}