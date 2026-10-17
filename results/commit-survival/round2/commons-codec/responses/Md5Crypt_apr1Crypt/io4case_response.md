{
    "group": "apr1Crypt_byteArray",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": [112, 97, 115, 115, 119, 111, 114, 100]  }
            ],
            "expected": "matches regex '^\\\\$apr1\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{22}$'",
            "description": "Typical ASCII password bytes; method should return a valid APR1‑MD5 hash string with the correct prefix, an 8‑character base‑64 salt and a 22‑character hash."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": [] 
                }
            ],
            "expected": "matches regex '^\\\\$apr1\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{22}$'",
            "description": "Empty byte array; still must produce a syntactically correct APR1‑MD5 hash."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": [0, 255, 16, 127, 128, 200] 
                }
            ],
            "expected": "matches regex '^\\\\$apr1\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{22}$'",
            "description": "Byte array containing null byte, high‑order bytes and non‑printable characters; hash format must remain valid."
        }
    ]
}