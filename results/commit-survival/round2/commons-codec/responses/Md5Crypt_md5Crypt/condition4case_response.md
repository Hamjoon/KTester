{
  "testCases": [
    {
      "group": "Salt handling",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "password123" },
            { "parameter": "salt", "value": null },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": "new java.util.Random(0)" }
          ],
          "expected": "returns string matching '^\\$1\\$[\\.\\/a-zA-Z0-9]{8}\\$.*'",
          "description": "When salt is null the method must generate an 8‑character random salt using the supplied Random instance; the result should start with the MD5 prefix and the generated salt."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "mySecret" },
            { "parameter": "salt", "value": "$1$abcd1234$extraInfo" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$abcd1234\\$.*'",
          "description": "A full hash‑like salt string is supplied; the method must extract the first 1‑8 allowed characters after the prefix (\"abcd1234\") and use it in the output."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "anyKey" },
            { "parameter": "salt", "value": "invalidSaltWithoutPrefix" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "throws IllegalArgumentException with message containing \"Invalid salt value\"",
          "description": "The supplied salt does not match the required pattern; the method must throw an IllegalArgumentException."
        }
      ]
    },
    {
      "group": "Key length parity (odd vs even)",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "oddKey" },              

            { "parameter": "salt", "value": "$1$xyz$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$xyz\\$.*'",
          "description": "Key length 6 (even) – exercises both branches of the `(ii & 1) == 1` test inside the second while‑loop."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "oddKeyLen" },          

            { "parameter": "salt", "value": "$1$abc$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$abc\\$.*'",
          "description": "Key length 9 (odd) – also forces both true and false paths of the `(ii & 1) == 1` condition across iterations."
        }
      ]
    },
    {
      "group": "Prefix variations (MD5 vs APR1)",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "samplePass" },
            { "parameter": "salt", "value": null },
            { "parameter": "prefix", "value": "$apr1$" },
            { "parameter": "random", "value": "new java.util.Random(12345)" }
          ],
          "expected": "returns string matching '^\\$apr1\\$[\\.\\/a-zA-Z0-9]{8}\\$.*'",
          "description": "Using the APR1 prefix with a null salt; a random 8‑character salt must be generated and the result must start with \"$apr1$\"."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "samplePass" },
            { "parameter": "salt", "value": "$apr1$salty$extra" },
            { "parameter": "prefix", "value": "$apr1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$apr1\\$salty\\$.*'",
          "description": "Explicit APR1 salt string; the method must extract \"salty\" and produce an output beginning with that prefix and salt."
        }
      ]
    }
  ]
}