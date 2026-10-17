{
    "tests": [
        {
            "group": "sha512Crypt_noSalt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "String matching pattern \"\\\\$6\\\\$[A-Za-z0-9./]{8,16}\\\\$[A-Za-z0-9./]{86}\" (starts with $6$, contains a generated salt of 8‑16 characters and an 86‑character hash)",
                    "description": "When salt is null the method must generate a random salt and return a valid SHA‑512‑crypt string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[0]"
                        }
                    ],
                    "expected": "String matching pattern \"\\\\$6\\\\$[A-Za-z0-9./]{8,16}\\\\$[A-Za-z0-9./]{86}\"",
                    "description": "Empty password bytes are allowed; the method should still produce a correctly formatted hash."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{(byte)0xFF, (byte)0x00, (byte)0x7A}"
                        }
                    ],
                    "expected": "String matching pattern \"\\\\$6\\\\$[A-Za-z0-9./]{8,16}\\\\$[A-Za-z0-9./]{86}\"",
                    "description": "Non‑ASCII byte values are processed as raw bytes; result must be a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "Two successive calls produce different salts, therefore the returned strings are not equal.",
                    "description": "Default salt generation must be random; invoking the method twice with the same key should yield different results."
                }
            ]
        },
        {
            "group": "sha512Crypt_invalidInput",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null byte array should result in a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "IllegalArgumentException (message contains \"Invalid salt\")",
                    "description": "When a salt with the wrong prefix (e.g., \"$5$\" for SHA‑256) is supplied via the overloaded method, sha512Crypt should reject it. This case uses the overload sha512Crypt(byte[], String) with an invalid prefix."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "IllegalArgumentException (message contains \"Invalid salt length\")",
                    "description": "Supplying a salt that is too short (e.g., \"$6$ab$\") must cause an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "IllegalArgumentException (message contains \"Invalid salt length\")",
                    "description": "Supplying a salt that is too long (e.g., 17 characters) must cause an IllegalArgumentException."
                }
            ]
        }
    ]
}