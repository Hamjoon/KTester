{
  "group": "isInAlphabet - edge and additional cases",
  "cases": [
    {
      "input": [
        {
          "parameter": "octet",
          "value": "-128"
        }
      ],
      "expected": "false",
      "description": "Octet is the minimum byte value (-128). The first condition (octet >= 0) fails, so the method must return false regardless of decodeTable."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "0"
        }
      ],
      "expected": "false",
      "description": "decodeTable is an empty array (length 0). Although octet is non‑negative, the second condition (octet < decodeTable.length) fails, yielding false."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "0"
        }
      ],
      "expected": "false",
      "description": "decodeTable length is 1 and decodeTable[0] is set to -1. All three conditions are evaluated; the third fails, so the method returns false."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "0"
        }
      ],
      "expected": "true",
      "description": "decodeTable length is 1 and decodeTable[0] contains a non‑‑1 value (e.g., 42). All conditions are satisfied, resulting in true."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "64"
        }
      ],
      "expected": "false",
      "description": "Octet equals decodeTable.length (64). The second condition (octet < decodeTable.length) fails, so the method returns false."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "100"
        }
      ],
      "expected": "false",
      "description": "decodeTable length is 100; octet is exactly the length, making the second condition fail and returning false."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "127"
        }
      ],
      "expected": "false",
      "description": "decodeTable length is 128 and decodeTable[127] is set to -1. The final condition fails, so false is returned."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "127"
        }
      ],
      "expected": "true",
      "description": "decodeTable length is 128 and decodeTable[127] holds a non‑‑1 value (e.g., 1). All conditions succeed, yielding true."
    },
    {
      "input": [
        {
          "parameter": "octet",
          "value": "127"
        }
      ],
      "expected": "false",
      "description": "decodeTable length is 100 (smaller than 128). Octet 127 is >= decodeTable.length, so the second condition fails and the method returns false."
    }
  ]
}