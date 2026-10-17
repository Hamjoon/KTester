{
    "testCases": [
        {
            "group": "Normal operation – typical password",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "password123"
                        }
                    ],
                    "expected": "String that matches the pattern '^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "Verify that a normal ASCII password is encrypted using the APR‑1 algorithm and the result starts with the APR1 prefix and follows the expected salt+hash format."
                }
            ]
        },
        {
            "group": "Normal operation – password with special characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "p@$$w0rd!#%&*"
                        }
                    ],
                    "expected": "String that matches the pattern '^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "Ensure that passwords containing symbols are processed correctly and produce a valid APR‑1 hash."
                }
            ]
        },
        {
            "group": "Edge case – empty password",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": ""
                        }
                    ],
                    "expected": "String that matches the pattern '^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "An empty string should still be accepted and result in a valid APR‑1 hash (the algorithm treats an empty password as a zero‑length byte array)."
                }
            ]
        },
        {
            "group": "Exception handling – null password",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": null
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null reference must cause a NullPointerException because the method attempts to call getBytes() on the argument."
                }
            ]
        },
        {
            "group": "Consistency – same password produces different salts",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "consistentPassword"
                        }
                    ],
                    "expected": "Two successive calls return strings with different 8‑character salts (the part after '$apr1$' and before the second '$').",
                    "description": "When no explicit salt is supplied, the implementation generates a random salt; verify that two calls with the same password yield different salts, confirming the random‑salt branch is exercised."
                }
            ]
        },
        {
            "group": "Format validation – prefix correctness",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "anyPassword"
                        }
                    ],
                    "expected": "Result string starts with '$apr1$'",
                    "description": "Confirm that the method uses the APR‑1 specific prefix, not the generic MD5 prefix."
                }
            ]
        },
        {
            "group": "Unicode handling – non‑ASCII characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "pässwördß"
                        }
                    ],
                    "expected": "String that matches the APR‑1 pattern '^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "The method must correctly encode Unicode characters using UTF‑8 before hashing; the output should still be a valid APR‑1 hash."
                }
            ]
        }
    ]
}