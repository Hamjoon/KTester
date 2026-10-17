{
    "testCases": [
        {
            "group": "Null Password Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "null"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(123L)"
                        }
                    ],
                    "expected": "throws NullPointerException",
                    "description": "When the password byte array is null the method should immediately fail with NPE."
                }
            ]
        },
        {
            "group": "Empty Password Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(0L)"
                        }
                    ],
                    "expected": "Result string starts with \"$1$\" + expectedSalt + \"$\" and the hash part is non‑null",
                    "description": "An empty password must still produce a valid MD5‑crypt hash; the salt is generated from the supplied Random."
                }
            ]
        },
        {
            "group": "Deterministic Salt Generation",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(42L)"
                        }
                    ],
                    "expected": "Result string equals \"$1$\" + \"eJ5rYjG/\" + \"$\" + \"c7U8lU6hVh1KcK3J6JQKk0\"",
                    "description": "Using a fixed Random seed (42) the B64.getRandomSalt(8, random) always returns the same 8‑character salt (eJ5rYjG/). The full hash is therefore deterministic."
                }
            ]
        },
        {
            "group": "Different Random Seeds Produce Different Salts",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"samePass\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(1L)"
                        }
                    ],
                    "expected": "Result string starts with \"$1$\" + salt1 + \"$\" where salt1 != salt2",
                    "description": "Two calls with different Random seeds must generate different salts, resulting in different hash strings."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"samePass\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(2L)"
                        }
                    ],
                    "expected": "Result string starts with \"$1$\" + salt2 + \"$\" where salt2 != salt1",
                    "description": "Second call with a different seed to verify that salts differ from the first call."
                }
            ]
        },
        {
            "group": "Large Password Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[1024]"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random(555L)"
                        }
                    ],
                    "expected": "Result string starts with \"$1$\" + expectedSalt + \"$\" and the hash part is non‑null",
                    "description": "A 1 KB password (all zero bytes) should still be processed without overflow and produce a correctly formatted hash."
                }
            ]
        },
        {
            "group": "SecureRandom Instance as Random",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"secure\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "Result string starts with \"$1$\" + 8‑char salt + \"$\" and the hash part is non‑null",
                    "description": "The method must accept any subclass of java.util.Random, including SecureRandom, and still generate a valid hash."
                }
            ]
        },
        {
            "group": "Null Random Parameter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"noprand\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "random",
                            "value": "null"
                        }
                    ],
                    "expected": "throws NullPointerException",
                    "description": "Passing a null Random should cause an immediate NPE because the method needs it to generate the salt."
                }
            ]
        }
    ]
}