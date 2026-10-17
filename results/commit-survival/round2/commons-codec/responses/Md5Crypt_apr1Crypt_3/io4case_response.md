{
    "tests": [
        {
            "group": "Null inputs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": null
                        },
                        {
                            "parameter": "salt",
                            "value": "anySalt"
                        }
                    ],
                    "expected": "throws NullPointerException",
                    "description": "Calling apr1Crypt with a null password should immediately raise a NullPointerException regardless of the salt."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "anyPassword"
                        },
                        {
                            "parameter": "salt",
                            "value": null
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "When the salt argument is null the method must generate an 8‑character random salt; the result should still follow the APR1 format."
                }
            ]
        },
        {
            "group": "Empty inputs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": ""
                        },
                        {
                            "parameter": "salt",
                            "value": "emptySalt"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$emptySalt\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "An empty password string is a valid input; the method must still produce a 22‑character hash."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "nonEmpty"
                        },
                        {
                            "parameter": "salt",
                            "value": ""
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "An empty salt should be accepted and appear as an empty field between the two '$' delimiters."
                }
            ]
        },
        {
            "group": "Salt length handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "TOOLONGSALT12345"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$TOOLONGS\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "If the supplied salt is longer than 8 characters the implementation must truncate it to the first 8 characters."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "abc"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$abc\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "A salt shorter than 8 characters is used as‑is; the method does not pad it."
                }
            ]
        },
        {
            "group": "Invalid characters in salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "bad$salt"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$bad\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "The '$' character terminates the salt; everything after the first '$' must be ignored."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "sp*ace"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$sp\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "Characters not in the allowed salt alphabet (e.g., '*') cause truncation at the first illegal character."
                }
            ]
        },
        {
            "group": "Byte‑array overload – deterministic salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "bytePassword"
                        },
                        {
                            "parameter": "salt",
                            "value": "byteSalt"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$byteSalt\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "The overload that receives a byte[] must produce the same format as the String overload when given the same UTF‑8 bytes."
                }
            ]
        },
        {
            "group": "Random‑salt overload – deterministic Random",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwdRandom"
                        },
                        {
                            "parameter": "random",
                            "value": "java.util.Random(seed=12345)"
                        }
                    ],
                    "expected": "matches regex '^\\\\$apr1\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{22}$'",
                    "description": "When a Random instance with a fixed seed is supplied, the generated salt must be reproducible; the test only asserts correct format."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pwdRandom"
                        },
                        {
                            "parameter": "random",
                            "value": "java.util.Random(seed=12345)"
                        }
                    ],
                    "expected": "identical to previous call with same seed",
                    "description": "Calling apr1Crypt twice with the same password and the same seeded Random must yield exactly the same hash string."
                }
            ]
        },
        {
            "group": "Consistency across calls",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "samePwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "sameSalt"
                        }
                    ],
                    "expected": "identical to second invocation with same arguments",
                    "description": "Two successive calls with identical password and salt must return the same hash, proving deterministic behavior."
                }
            ]
        },
        {
            "group": "Different salts produce different hashes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "samePwd"
                        },
                        {
                            "parameter": "salt",
                            "value": "saltOne"
                        }
                    ],
                    "expected": "hash differs from hash produced with salt 'saltTwo'",
                    "description": "Using two distinct salts for the same password must result in two different hash strings."
                }
            ]
        }
    ]
}