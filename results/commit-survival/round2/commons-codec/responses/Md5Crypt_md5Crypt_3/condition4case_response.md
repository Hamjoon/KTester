{
    "group": "Null Input Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "null"
                },
                {
                    "parameter": "random",
                    "value": "new java.util.Random()"
                }
            ],
            "expected": "NullPointerException",
            "description": "Passing a null keyBytes should cause the method to throw a NullPointerException regardless of the Random instance."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "random",
                    "value": "null"
                }
            ],
            "expected": "Result string matches pattern \"^\\\\$1\\\\$[A-Za-z0-9./]{8}\\\\$.*$\"",
            "description": "When the Random argument is null the method must fall back to a SecureRandom internally; the returned hash should still be correctly formatted with an 8‑character salt."
        }
    ]
  ,
    "group": "Random Salt Generation (non‑deterministic)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "random",
                    "value": "new java.security.SecureRandom()"
                }
            ],
            "expected": "Result string starts with \"$1$\" and contains exactly two '$' characters after the prefix (i.e., \"$1$<8‑char‑salt>$...\")",
            "description": "Using a SecureRandom should produce a correctly formatted MD5‑crypt string with a freshly generated 8‑character salt."
        }
    ]
  ,
    "group": "Deterministic Salt Generation (fixed seed)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"hello\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "random",
                    "value": "new java.util.Random(12345L)"
                }
            ],
            "expected": "Result string equals \"$1$\" + expectedSalt + \"$\" + expectedHash",
            "description": "Using a Random with a fixed seed makes the generated salt deterministic. The test computes the expected salt via B64.getRandomSalt(8, new java.util.Random(12345L)) and then verifies that the full crypt string contains that exact salt and the correct hash."
        }
    ]
  ,
    "group": "Empty Key Bytes",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[0]"
                },
                {
                    "parameter": "random",
                    "value": "new java.util.Random(0L)"
                }
            ],
            "expected": "Result string matches pattern \"^\\\\$1\\\\$[A-Za-z0-9./]{8}\\\\$.*$\"",
            "description": "An empty password should still produce a valid MD5‑crypt string; the salt is deterministic because of the fixed Random seed."
        }
    ]
  ,
    "group": "Special Characters in Password",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"p@ßw0rd!\\n\\t\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "random",
                    "value": "new java.util.Random(987654321L)"
                }
            ],
            "expected": "Result string starts with \"$1$\" + expectedSalt + \"$\" and the hash part is non‑null",
            "description": "Passwords containing Unicode and whitespace characters must be handled correctly; the method should still return a properly formatted hash."
        }
    ]
}