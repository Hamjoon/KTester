{
    "test_cases": [
        {
            "group": "Invalid arguments - empty prefix string",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"mysalt\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "An empty prefix string is not a supported value; the method must reject it."
                }
            ]
        },
        {
            "group": "Invalid arguments - malformed prefix with extra characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"mysalt\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$1$extra\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Prefix contains extra characters beyond the exact constants; only \"$1$\" or \"$apr1$\" are allowed."
                }
            ]
        },
        {
            "group": "Invalid arguments - case‑sensitive prefix mismatch",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"mysalt\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "\"$APR1$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Prefix is case‑sensitive; \"$APR1$\" is not recognized as a valid constant."
                }
            ]
        },
        {
            "group": "Invalid arguments - salt becomes empty after stripping prefix",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$1$\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "Md5Crypt.MD5_PREFIX"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When the supplied salt consists only of the prefix, stripping it leaves an empty salt, which is illegal."
                }
            ]
        },
        {
            "group": "Invalid arguments - salt contains newline character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"\\n\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "Md5Crypt.MD5_PREFIX"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "A newline character is not part of the allowed salt character set."
                }
            ]
        },
        {
            "group": "Invalid arguments - salt contains tab character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"\\t\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "Md5Crypt.APR1_PREFIX"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "A tab character is prohibited in the salt according to the MD5‑crypt specification."
                }
            ]
        },
        {
            "group": "Invalid arguments - salt contains non‑ASCII character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"ä\""
                        },
                        {
                            "parameter": "prefix",
                            "value": "Md5Crypt.MD5_PREFIX"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Only ASCII characters (letters, digits, '.' and '/') are allowed in the salt; a non‑ASCII character should cause an exception."
                }
            ]
        }
    ]
}