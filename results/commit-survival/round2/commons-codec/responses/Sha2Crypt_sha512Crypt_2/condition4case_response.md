{
  "testCases": [
    {
      "group": "Null salt handling",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "new byte[]{'s','e','c','r','e','t'}"
            },
            {
              "parameter": "salt",
              "value": "null"
            }
          ],
          "expected": "Result string starts with \"$6$\" and contains a randomly generated 8‑character base‑64 salt after the prefix (e.g. \"$6$AbCdEfGh$...\")",
          "description": "When the supplied salt is null the method must generate a random 8‑character salt and prepend the SHA‑512 prefix. The returned hash must therefore begin with \"$6$\" followed by the random salt and a trailing \"$\" before the hash data."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "new byte[0]"
            },
            {
              "parameter": "salt",
              "value": "null"
            }
          ],
          "expected": "Result string starts with \"$6$\" and contains a random 8‑character salt; the hash part is still produced (non‑null).",
          "description": "Even if the password byte array is empty the method should still produce a valid SHA‑512‑crypt string with a random salt."
        }
      ]
    },
    {
      "group": "Provided non‑null salt handling",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$mysalt\""
            }
          ],
          "expected": "Result string equals \"$6$mysalt$\" + a deterministic 86‑character hash (e.g. \"$6$mysalt$KjG9rV...$\"), i.e. the returned value is always the same for the same inputs.",
          "description": "When a non‑null salt that already contains the SHA‑512 prefix is supplied, the method must use it unchanged and produce a deterministic hash. The returned string must start with the exact salt \"$6$mysalt$\"."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"secret\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"mysalt\""
            }
          ],
          "expected": "Result string starts with \"$6$mysalt$\" (the method will prepend the missing prefix automatically).",
          "description": "If the caller supplies a salt without the required \"$6$\" prefix, the internal sha2Crypt routine still receives the raw salt; the public method does not modify it, so the resulting hash will contain the raw salt after the prefix added by sha2Crypt. The test verifies that the final string contains \"$6$mysalt$\"."
        }
      ]
    },
    {
      "group": "Edge‑case salt values",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"edge\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"\""
            }
          ],
          "expected": "Result string starts with \"$6$$\" (i.e. an empty salt between the two dollar signs).",
          "description": "An empty string is a valid (though unusual) salt. The method should forward it unchanged, resulting in a hash that begins with \"$6$$\"."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$\""
            }
          ],
          "expected": "Result string starts with \"$6$$\" (the provided salt contains only the prefix, no actual salt characters).",
          "description": "When the supplied salt consists solely of the prefix, the implementation must handle the empty salt part correctly."
        }
      ]
    },
    {
      "group": "Invalid arguments",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "null"
            },
            {
              "parameter": "salt",
              "value": "\"$6$any\""
            }
          ],
          "expected": "NullPointerException is thrown",
          "description": "The method does not guard against a null password byte array; a NPE should be propagated from the underlying MessageDigest usage."
        }
      ]
    }
  ]
}