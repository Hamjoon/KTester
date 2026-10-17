{
    "result": [
        {
            "group": "test_md5Crypt_nullKeyBytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "null"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"someSalt\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "keyBytes is null – the method accesses keyBytes.length and later uses it, which must throw NPE."
                }
            ]
        },
        {
            "group": "test_md5Crypt_nullPrefix",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$1$valid\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "null"
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "prefix is null – the method calls prefix.getBytes(...), which must throw NPE."
                }
            ]
        },
        {
            "group": "test_md5Crypt_nullRandomWhenSaltNull",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'a'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "null"
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "salt is null, so the method tries to generate a random salt via B64.getRandomSalt(8, random). Passing a null Random leads to NPE."
                }
            ]
        },
        {
            "group": "test_md5Crypt_invalidSalt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'x'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"invalid_salt\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Salt does not start with the required prefix and contains illegal characters; regex fails, causing IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'x'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Salt contains the prefix but no following characters (length 0), violating the {1,8} requirement."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'x'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$1$123456789\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Salt has more than 8 characters after the prefix, exceeding the allowed length and causing IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'x'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$apr1$valid\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.util.Random()"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Salt uses a different prefix than the supplied prefix; regex does not match and throws IllegalArgumentException."
                }
            ]
        }
    ]
}