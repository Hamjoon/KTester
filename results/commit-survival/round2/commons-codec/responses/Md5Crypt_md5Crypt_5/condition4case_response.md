{
  "testCases": [
    {
      "group": "Valid inputs - standard MD5 prefix",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "\"mysalt\"" },
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "String that starts with \"$1$\" and contains the provided salt \"mysalt\" (e.g., \"$1$mysalt$...$\")",
          "description": "Verifies that a normal password and a simple salt produce a correctly formatted MD5‑crypt hash with the \"$1$\" prefix."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'s','e','c','r','e','t'}" },
            { "parameter": "salt", "value": "\"abc123\"" },
            { "parameter": "prefix", "value": "Md5Crypt.APR1_PREFIX" }
          ],
          "expected": "String that starts with \"$apr1$\" and contains the provided salt \"abc123\" (e.g., \"$apr1$abc123$...$\")",
          "description": "Ensures that using the APR‑1 prefix yields a hash that begins with \"$apr1$\" while preserving the supplied salt."
        }
      ]
    },
    {
      "group": "Valid inputs - salt already contains prefix",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'t','e','s','t'}" },
            { "parameter": "salt", "value": "\"$1$alreadyPrefixed\""},
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "String that starts with \"$1$\" and contains the salt without a duplicated prefix (e.g., \"$1$alreadyPrefixed$...$\")",
          "description": "Checks that when the supplied salt already includes the MD5 prefix, the method does not prepend the prefix a second time."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'t','e','s','t'}" },
            { "parameter": "salt", "value": "\"$apr1$alreadyPrefixed\""},
            { "parameter": "prefix", "value": "Md5Crypt.APR1_PREFIX" }
          ],
          "expected": "String that starts with \"$apr1$\" and contains the salt without a duplicated prefix (e.g., \"$apr1$alreadyPrefixed$...$\")",
          "description": "Same as above but for the APR‑1 prefix."
        }
      ]
    },
    {
      "group": "Invalid arguments - null or empty values",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "null" },
            { "parameter": "salt", "value": "\"mysalt\"" },
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "IllegalArgumentException",
          "description": "The method must reject a null password byte array."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "null" },
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "IllegalArgumentException",
          "description": "The method must reject a null salt string."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "\"mysalt\"" },
            { "parameter": "prefix", "value": "null" }
          ],
          "expected": "IllegalArgumentException",
          "description": "The method must reject a null prefix."
        },
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "\"\"" },
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "IllegalArgumentException",
          "description": "An empty salt is not allowed; the method should throw an exception."
        }
      ]
    },
    {
      "group": "Invalid arguments - unsupported prefix",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "\"mysalt\"" },
            { "parameter": "prefix", "value": "\"$unknown$\"" }
          ],
          "expected": "IllegalArgumentException",
          "description": "Only the two known prefixes (\"$1$\" and \"$apr1$\") are supported; any other prefix should cause an exception."
        }
      ]
    },
    {
      "group": "Invalid arguments - salt length limits",
      "cases": [
        {
          "input": [
            { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
            { "parameter": "salt", "value": "\"1234567890123456\"" },
            { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
          ],
          "expected": "IllegalArgumentException",
          "description": "The implementation limits the salt to a maximum of 8 characters; a longer salt must trigger an exception."
        }
      ]
    }
  ]
}