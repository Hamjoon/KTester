{
    "groups": [
        {
            "group": "Salt without required prefix",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115}"
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
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "The method must reject a salt that does not start with the required \"$6$\" prefix; it should throw IllegalArgumentException before delegating to sha2Crypt."
                }
            ]
        },
        {
            "group": "Salt exceeding maximum length",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$12345678901234567\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "SHA‑512‑crypt limits the salt to 16 characters (excluding the prefix). Supplying a 17‑character salt should cause an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Salt containing illegal characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$invalid$salt\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "The salt may not contain the '$' character after the prefix (it terminates the salt). Providing an internal '$' should be rejected with IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Salt with malformed rounds specification",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{112, 97, 115, 115}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$rounds=abc$mysalt\""
                        },
                        {
                            "parameter": "random",
                            "value": "new java.security.SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "If the optional rounds parameter is present it must be a numeric value. Supplying a non‑numeric value (\"abc\") must cause an IllegalArgumentException."
                }
            ]
        }
    ]
}