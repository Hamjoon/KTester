{
    "tests": [
        {
            "group": "Boundary conditions - salt length limits",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"12345678\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "String that starts with \"$1$\" and contains the salt \"12345678\" (e.g., \"$1$12345678$...$\")",
                    "description": "Salt length exactly 8 characters is the maximum allowed; the method should accept it and produce a correctly formatted hash."
                },
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"a\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.APR1_PREFIX" }
                    ],
                    "expected": "String that starts with \"$apr1$\" and contains the salt \"a\" (e.g., \"$apr1$a$...$\")",
                    "description": "Salt length of 1 character is the minimum allowed; the method should accept it and produce a correctly formatted hash."
                }
            ]
        },
        {
            "group": "Salt character set validation - allowed characters",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"a./B9_\""} ,
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "String that starts with \"$1$\" and contains the salt \"a./B9_\" (e.g., \"$1$a./B9_$...$\")",
                    "description": "The salt contains only characters permitted by the MD5‑crypt specification (letters, digits, '.' and '/'); the method should accept it."
                }
            ]
        },
        {
            "group": "Salt character set validation - disallowed characters",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"bad$char\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The salt contains a '$' character that is not part of a leading prefix; the method must reject it."
                },
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"space char\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.APR1_PREFIX" }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The salt contains a space character, which is not allowed; the method must throw an exception."
                }
            ]
        },
        {
            "group": "Password edge cases - empty and large byte arrays",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{}" },
                        { "parameter": "salt", "value": "\"emptysalt\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "String that starts with \"$1$\" and contains the salt \"emptysalt\" (e.g., \"$1$emptysalt$...$\")",
                    "description": "An empty password byte array is technically allowed; the method should still produce a valid hash."
                },
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[1024]" },
                        { "parameter": "salt", "value": "\"bigSalt\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.APR1_PREFIX" }
                    ],
                    "expected": "String that starts with \"$apr1$\" and contains the salt \"bigSalt\" (e.g., \"$apr1$bigSalt$...$\")",
                    "description": "A large password (1 KB) should be processed without error and produce a correctly formatted hash."
                }
            ]
        },
        {
            "group": "Password with non‑ASCII characters (UTF‑8 bytes)",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "\"pässwörd\".getBytes(StandardCharsets.UTF_8)" },
                        { "parameter": "salt", "value": "\"utf8salt\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "String that starts with \"$1$\" and contains the salt \"utf8salt\" (e.g., \"$1$utf8salt$...$\")",
                    "description": "Password bytes contain Unicode characters; the method should treat the raw bytes and still return a valid hash."
                }
            ]
        },
        {
            "group": "Deterministic output for identical inputs",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"repeatSalt\"" },
                        { "parameter": "prefix", "value": "Md5Crypt.MD5_PREFIX" }
                    ],
                    "expected": "Two consecutive calls with the same arguments return exactly the same string",
                    "description": "Ensures that md5Crypt is pure (no hidden randomness) when a salt is supplied; the hash must be identical across invocations."
                }
            ]
        },
        {
            "group": "Invalid arguments - malformed prefix string",
            "cases": [
                {
                    "input": [
                        { "parameter": "keyBytes", "value": "new byte[]{'p','a','s','s'}" },
                        { "parameter": "salt", "value": "\"mysalt\"" },
                        { "parameter": "prefix", "value": "\" $1$ \"" }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Prefix contains leading/trailing whitespace; only the exact constants \"$1$\" or \"$apr1$\" are accepted."
                }
            ]
        }
    ]
}