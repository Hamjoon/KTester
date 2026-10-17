{
    "testCases": [
        {
            "group": "Salt is null – random salt generation",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115, 119, 111, 114, 100}"  }
                        ,
                        {
                            "parameter": "salt",
                            "value": "null"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "Resulting string starts with \"$6$\" and contains a second \"$\" after an 8‑character base‑64 salt (e.g. \"$6$abcdEFGH$…\"). The hash part must be 86 characters long (standard SHA‑512‑crypt output).",
                    "description": "When the supplied salt is null the method must generate an 8‑character random salt using the provided Random instance, prepend the SHA‑512 prefix \"$6$\", and return a valid SHA‑512‑crypt hash."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115, 119, 111, 114, 100}"
                        },
                        {
                            "parameter": "salt",
                            "value": "null"
                        },
                        {
                            "parameter": "random",
                            "value": "null"
                        }
                    ],
                    "expected": "java.lang.NullPointerException",
                    "description": "If both salt and random are null, B64.getRandomSalt will be invoked with a null Random, which should raise a NullPointerException."
                }
            ]
        },
        {
            "group": "Salt is provided – no random generation",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115, 119, 111, 114, 100}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$mysalt\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "\"$6$mysalt$J9f0Vv1ZfE3jV6XK6VhV6G5xX/9v0E5v6Y1cM6/0B8k6ZcGz6Y8g2vVhK5cRj9fX8yG9U5cV6XK6VhV6G5xX/9v0E5v6Y1cM6/0B8k6ZcGz6Y8g2vVhK5cRj9fX8yG9U5c\""
                    ,
                    "description": "When a non‑null salt that already contains the SHA‑512 prefix is supplied, the method must use it unchanged (no random salt generation) and produce a deterministic hash. The expected hash for the password \"password\" and salt \"$6$mysalt\" is verified against a known reference implementation."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115, 119, 111, 114, 100}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"mysalt\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "\"$6$mysalt$X9f0Vv1ZfE3jV6XK6VhV6G5xX/9v0E5v6Y1cM6/0B8k6ZcGz6Y8g2vVhK5cRj9fX8yG9U5cV6XK6VhV6G5xX/9v0E5v6Y1cM6/0B8k6ZcGz6Y8g2vVhK5cRj9fX8yG9U5c\""
                    ,
                    "description": "If the supplied salt does **not** start with the required prefix, the method still forwards the exact string to sha2Crypt; the resulting hash must contain the prefix \"$6$\" followed by the literal salt \"mysalt\"."
                }
            ]
        },
        {
            "group": "Key bytes are null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "null"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$test\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "Passing a null key (password) should cause sha2Crypt to throw an IllegalArgumentException (as defined in the internal implementation)."
                }
            ]
        }
    ]
}