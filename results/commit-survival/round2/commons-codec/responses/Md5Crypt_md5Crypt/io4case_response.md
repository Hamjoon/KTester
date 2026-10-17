{
  "testCases": [
    {
      "group": "Empty keyBytes",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "" },
            { "parameter": "salt", "value": "$1$abc$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$abc\\$.*'",
          "description": "An empty password (zero‑length byte array) with a valid salt. The method should still produce a hash that starts with the supplied prefix and salt."
        }
      ]
    },
    {
      "group": "Salt length boundaries",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "testPass" },
            { "parameter": "salt", "value": "$1$a$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$a\\$.*'",
          "description": "Salt contains the minimum allowed length (1 character). The method must accept it and embed it unchanged in the result."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "testPass" },
            { "parameter": "salt", "value": "$1$abcdefgh$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$abcdefgh\\$.*'",
          "description": "Salt contains the maximum allowed length (8 characters). The method should use the whole salt without truncation."
        }
      ]
    },
    {
      "group": "Special characters in salt",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "specialKey" },
            { "parameter": "salt", "value": "$1$..//ab$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$\\.\\.\\/\\/ab\\$.*'",
          "description": "Salt includes the allowed special characters '.' and '/' which must be preserved in the output."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "specialKey2" },
            { "parameter": "salt", "value": "$1$./aB9Z9/\\$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$\\.\\/aB9Z9\\/\\$.*'",
          "description": "Salt uses a full set of allowed characters (including '.' and '/') at the maximum length of 8."
        }
      ]
    },
    {
      "group": "Custom prefix handling",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "customPrefixPass" },
            { "parameter": "salt", "value": "$my$xyz$" },
            { "parameter": "prefix", "value": "$my$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$my\\$xyz\\$.*'",
          "description": "A non‑standard prefix is supplied. The method must correctly escape the prefix for the regex and produce an output that starts with the custom prefix and extracted salt."
        }
      ]
    },
    {
      "group": "Null Random handling",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "pass" },
            { "parameter": "salt", "value": null },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "throws NullPointerException",
          "description": "When both salt and Random are null the method attempts to generate a random salt using a null Random instance, which should result in a NullPointerException."
        }
      ]
    },
    {
      "group": "Long salt truncation",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "truncateTest" },
            { "parameter": "salt", "value": "$1$abcdefghijk$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$abcdefgh\\$.*'",
          "description": "Salt string longer than 8 characters is supplied; the method must extract only the first 8 allowed characters ('abcdefgh') and ignore the rest."
        }
      ]
    },
    {
      "group": "Unicode keyBytes",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "pässwörd" },
            { "parameter": "salt", "value": "$1$uni$" },
            { "parameter": "prefix", "value": "$1$" },
            { "parameter": "random", "value": null }
          ],
          "expected": "returns string matching '^\\$1\\$uni\\$.*'",
          "description": "Key bytes contain non‑ASCII UTF‑8 characters. The method should handle them correctly and still produce a valid hash with the given salt."
        }
      ]
    }
  ]
}