Invalid control character at: line 40 column 53 (char 1588)
{
    "tests": [
        {
            "group": "Empty Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[0]"
                        }
                    ],
                    "expected": "Non‑null string matching pattern '^\\\\$5\\\\$[A-Za-z0-9./]{1,16}\\\\$[A-Za-z0-9./]{43}$'",
                    "description": "An empty byte array should still produce a valid SHA‑256‑crypt string. The result must start with the $5$ prefix, contain a generated salt (1‑16 characters) and a 43‑character hash."
                }
            ]
        },
        {
            "group": "Typical Small Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "Non‑null string matching pattern '^\\\\$5\\\\$[A-Za-z0-9./]{1,16}\\\\$[A-Za-z0-9./]{43}$'",
                    "description": "A typical non‑empty key should be processed correctly and return a properly formatted crypt string."
                }
            ]
        },
        {
            "group": "Large Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[2048] // filled with incremental values 0‑255 repeated"
                        }
                    ],
                    "expected": "Non‑null string matching pattern '^\\\\$5\\\\$[A-Za-z0-9./]{1,16}\\\\$[A-Za-z0-9./]{43}$'",
                    "description": "A large input (2048 bytes) must be handled without errors and still produce a correctly formatted result."
                }
            ]
        },
        {
            "group": "Random Salt Variation",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"sameKey\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "Two successive calls return different strings (different salts)",
                    "description": "Calling sha256Crypt(byte[]) twice with the same key must generate a different salt each time, resulting in different crypt strings."
                }
            ]
        },
        {
            "group": "Output Immutability",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "byte[] original = \"immutable\".getBytes(StandardCharsets.UTF_8); byte[] copy = Arrays.copyOf(original, original.length);"
                        }
                    ],
                    "expected": "Method does not modify the original array; after the call original and copy remain equal",
                    "description": "The implementation must treat the input byte array as read‑only; the caller's data should not be altered."
                }
            ]
        },
        {
            "group": "Multiple Calls Consistency of Format",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"repeat\".getBytes(StandardCharsets.UTF_8)"
                        }
                    ],
                    "expected": "All returned strings start with \"$5$\" and contain exactly two '$' delimiters",
                    "description": "Regardless of randomness, every result from sha256Crypt(byte[]) must follow the crypt format: $5$[salt]$[hash]."
                }
            ]
        }
    ]
}