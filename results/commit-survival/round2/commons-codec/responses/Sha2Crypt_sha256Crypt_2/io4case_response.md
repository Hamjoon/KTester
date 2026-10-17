{
  "testCases": [
    {
      "group": "Custom valid salt",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$mysalt$\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$mysalt\\\\$[A-Za-z0-9./]{43}\"",
          "description": "A normal password with an explicit 8‑character salt (no rounds) must produce a SHA‑256 crypt string that preserves the supplied salt."
        }
      ]
    },
    {
      "group": "Custom salt with rounds",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$rounds=12000$mysalt$\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$rounds=12000\\\\$mysalt\\\\$[A-Za-z0-9./]{43}\"",
          "description": "When a rounds specification is present the resulting hash must contain the same rounds value and the provided salt."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$rounds=5000$mysalt$\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$rounds=5000\\\\$mysalt\\\\$[A-Za-z0-9./]{43}\"",
          "description": "The default round count (5000) must be honoured when explicitly supplied; the output format must reflect the rounds parameter."
        }
      ]
    },
    {
      "group": "Salt without prefix",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"mysalt\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$mysalt\\\\$[A-Za-z0-9./]{43}\"",
          "description": "If the supplied salt lacks the \"$5$\" prefix the method should prepend it automatically and still produce a valid hash."
        }
      ]
    },
    {
      "group": "Invalid salt characters",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$bad*salt$\""
            }
          ],
          "expected": "IllegalArgumentException",
          "description": "A salt containing characters outside the allowed set (A‑Z a‑z 0‑9 . /) must cause an IllegalArgumentException."
        }
      ]
    },
    {
      "group": "Excessively long salt",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$abcdefghijklmnopqr$\""
            }
          ],
          "expected": "IllegalArgumentException",
          "description": "A salt longer than the maximum allowed length (16 characters) must be rejected with an IllegalArgumentException."
        }
      ]
    },
    {
      "group": "Empty salt string",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"\""
            }
          ],
          "expected": "IllegalArgumentException",
          "description": "An empty salt (not null) is considered malformed and should result in an IllegalArgumentException."
        }
      ]
    },
    {
      "group": "Large keyBytes",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "new byte[1024 * 1024]"  ,
              "note": "array filled with zeros"
            },
            {
              "parameter": "salt",
              "value": "\"$5$large$\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$large\\\\$[A-Za-z0-9./]{43}\"",
          "description": "The method must handle a 1 MiB password array without throwing and still return a correctly formatted hash."
        }
      ]
    },
    {
      "group": "Null keyBytes",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "null"
            },
            {
              "parameter": "salt",
              "value": "\"$5$nullkey$\""
            }
          ],
          "expected": "NullPointerException",
          "description": "Passing a null password byte array should cause a NullPointerException."
        }
      ]
    },
    {
      "group": "Unicode password",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pässwörd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$unicode$\""
            }
          ],
          "expected": "String that matches the pattern \"\\\\$5\\\\$unicode\\\\$[A-Za-z0-9./]{43}\"",
          "description": "A password containing non‑ASCII characters must be UTF‑8 encoded correctly and still produce a valid SHA‑256 crypt string."
        }
      ]
    }
  ]
}