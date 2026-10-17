{
  "testCases": [
    {
      "group": "Rounds parameter handling",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$rounds=5000$mysalt\""
            }
          ],
          "expected": "Result string starts with \"$6$rounds=5000$mysalt$\" followed by a deterministic 86‑character hash (the full string length is 108 characters).",
          "description": "The salt contains a valid rounds specification; the method must honour the rounds value and include it in the output prefix."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$rounds=10000$\""
            }
          ],
          "expected": "Result string starts with \"$6$rounds=10000$$\" (i.e. an empty salt after the rounds specifier) followed by a deterministic hash.",
          "description": "A rounds specifier is provided but no actual salt characters; the implementation should treat the salt part as empty and still produce a valid hash."
        }
      ]
    },
    {
      "group": "Invalid salt patterns",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$5$mysalt\""
            }
          ],
          "expected": "IllegalArgumentException is thrown (wrong algorithm prefix – SHA‑256 prefix used with SHA‑512 method).",
          "description": "The salt starts with \"$5$\" which is the SHA‑256 prefix; sha512Crypt must reject it."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$invalid!salt\""
            }
          ],
          "expected": "IllegalArgumentException is thrown (salt contains illegal character '!').",
          "description": "Salt may only contain characters from the crypt base‑64 alphabet (A‑Z, a‑z, 0‑9, '.' and '/'). The exclamation mark is invalid."
        },
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$\" + \"a\".repeat(20)"
            }
          ],
          "expected": "IllegalArgumentException is thrown (salt length exceeds the maximum of 16 characters).",
          "description": "The implementation limits the raw salt to 16 characters; a longer salt must be rejected."
        }
      ]
    },
    {
      "group": "Unicode password handling",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"pässwörd\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$unicode\""
            }
          ],
          "expected": "Result string starts with \"$6$unicode$\" and is deterministic for the given Unicode password.",
          "description": "Ensures that the method correctly handles UTF‑8 encoded passwords containing non‑ASCII characters."
        }
      ]
    },
    {
      "group": "Deterministic repeatability",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"repeat\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$repeat\""
            }
          ],
          "expected": "Two consecutive calls with the same arguments return exactly the same string.",
          "description": "Verifies that sha512Crypt is pure (no hidden state) and produces a repeatable result for identical inputs."
        }
      ]
    },
    {
      "group": "Salt with trailing dollar sign",
      "cases": [
        {
          "input": [
            {
              "parameter": "keyBytes",
              "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
            },
            {
              "parameter": "salt",
              "value": "\"$6$mysalt$\""
            }
          ],
          "expected": "Result string starts with \"$6$mysalt$$\" (double dollar sign) followed by a deterministic hash.",
          "description": "When the supplied salt already ends with a dollar sign, the implementation should treat the salt part as empty after the delimiter and still generate a valid hash."
        }
      ]
    }
  ]
}