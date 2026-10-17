{
    "group": "Valid inputs – deterministic salt",
    "cases": [
        {
            "input": [
                { "parameter": "keyBytes", "value": "password" },
                { "parameter": "salt", "value": "salt123" }
            ],
            "expected": "matches regex '^\\$apr1\\$salt123\\$[A-Za-z0-9./]{22}$'",
            "description": "Standard case: non‑null password and a valid 8‑character salt. The result must start with the APR1 prefix, contain the exact salt and a 22‑character base‑64 hash."
        },
        {
            "input": [
                { "parameter": "keyBytes", "value": "p@$$w0rd!" },
                { "parameter": "salt", "value": "abcDEF12" }
            ],
            "expected": "matches regex '^\\$apr1\\$abcDEF12\\$[A-Za-z0-9./]{22}$'",
            "description": "Password contains special characters; salt is exactly 8 characters. Verifies that special characters are handled correctly."
        },
        {
            "input": [
                { "parameter": "keyBytes", "value": "短いパスワード" },
                { "parameter": "salt", "value": "日本語12" }
            ],
            "expected": "matches regex '^\\$apr1\\$日本語12\\$[A-Za-z0-9./]{22}$'",
            "description": "Unicode characters in both password and salt; ensures UTF‑8 encoding is used."
        }
    ]
}