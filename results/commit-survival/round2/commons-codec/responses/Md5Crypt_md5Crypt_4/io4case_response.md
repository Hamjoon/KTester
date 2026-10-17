{
  "testCases": [
    {
      "group": "Edge case salt handling",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "password123" },
            { "parameter": "salt", "value": "" }
          ],
          "expected": "matches regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
          "description": "Empty salt string should be treated as missing; the method must generate a random 8‑character salt."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "password123" },
            { "parameter": "salt", "value": "$1$" }
          ],
          "expected": "matches regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
          "description": "Salt consisting solely of the MD5 prefix must be stripped, resulting in a generated random 8‑character salt."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "password123" },
            { "parameter": "salt", "value": "$abcd$" }
          ],
          "expected": "matches regex '^\\$1\\$abcd\\$[./0-9A-Za-z]{22}$'",
          "description": "Salt with leading and trailing '$' characters should be parsed to the inner value \"abcd\"."
        }
      ]
    },
    {
      "group": "Invalid character handling",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "secret" },
            { "parameter": "salt", "value": "ab!cd" }
          ],
          "expected": "matches regex '^\\$1\\$ab\\$[./0-9A-Za-z]{22}$'",
          "description": "Salt contains an invalid character ('!'); parsing stops before it, so only \"ab\" is used."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "secret" },
            { "parameter": "salt", "value": "!!!" }
          ],
          "expected": "matches regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
          "description": "Salt composed entirely of invalid characters results in no usable characters; a random 8‑character salt must be generated."
        }
      ]
    },
    {
      "group": "Key byte edge cases",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": null },
            { "parameter": "salt", "value": "salt123" }
          ],
          "expected": "throws NullPointerException",
          "description": "Passing a null keyBytes array should cause a NullPointerException as the method requires a non‑null password."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "" },
            { "parameter": "salt", "value": "salt123" }
          ],
          "expected": "matches regex '^\\$1\\$salt123\\$[./0-9A-Za-z]{22}$'",
          "description": "An empty password (zero‑length byte array) is valid; the hash must still be produced using the supplied salt."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "" },
            { "parameter": "salt", "value": null }
          ],
          "expected": "matches regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
          "description": "Empty password with a null salt – the method should generate a random 8‑character salt and produce a valid hash."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "aVeryLongPasswordThatExceedsTypicalLengthsAndContainsVariousCharacters123!@#" },
            { "parameter": "salt", "value": "longsalt" }
          ],
          "expected": "matches regex '^\\$1\\$longsalt\\$[./0-9A-Za-z]{22}$'",
          "description": "Very long password input should be handled correctly; the resulting hash must still conform to the expected format."
        }
      ]
    }
  ]
}