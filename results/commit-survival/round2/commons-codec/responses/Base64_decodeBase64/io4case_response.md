{
    "testCases": [
        {
            "group": "Missing Padding Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "YWJjZg"
                        }
                    ],
                    "expected": [97, 98, 99],
                    "description": "Base64 string without trailing padding characters should still decode to the original bytes (\"abc\")."
                }
            ]
        },
        {
            "group": "Round‑Trip Binary Data",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "AAECAwQFBgcICQoLDA0ODw=="
                        }
                    ],
                    "expected": [
                        0, 1, 2, 3, 4, 5, 6, 7,
                        8, 9, 10, 11, 12, 13, 14, 15
                    ],
                    "description": "A full range of byte values (0‑15) encoded to Base64 and then decoded should return the original byte array, proving loss‑less round‑trip."
                }
            ]
        },
        {
            "group": "Non‑ASCII Input Bytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "éâß"
                        }
                    ],
                    "expected": [],
                    "description": "Byte values outside the standard Base64 alphabet (e.g., characters with code points > 127) are ignored, resulting in an empty output."
                }
            ]
        },
        {
            "group": "Input Mutation Safety",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "AQIDBAU="
                        }
                    ],
                    "expected": [1, 2, 3, 4, 5],
                    "description": "After decoding, mutating the original input array must not affect the returned byte array; the method should return a new independent array."
                }
            ]
        },
        {
            "group": "Mixed Valid/Invalid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "A!B?C D"
                        }
                    ],
                    "expected": [0, 16, 35],
                    "description": "The decoder should ignore illegal characters ('!','?',' ') and decode only the valid Base64 symbols (A, B, C, D)."
                }
            ]
        },
        {
            "group": "Whitespace Variants",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "\tSGVs bG8g\r\nV29y bGQh\f"
                        }
                    ],
                    "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
                    "description": "Tabs, carriage returns, line feeds and form‑feed characters must be ignored; the decoded result should match \"Hello World!\"."
                }
            ]
        },
        {
            "group": "Invalid Padding Position",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "YWJj=Zg=="
                        }
                    ],
                    "expected": [97, 98, 99],
                    "description": "When a padding character appears before the end of the input, decoding stops at the first padding; the preceding valid segment \"YWJj\" decodes to \"abc\"."
                }
            ]
        }
    ]
}