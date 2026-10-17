{
    "group": "Valid salt handling",
    "cases": [
        {
            "input": [
                { "parameter": "keyBytes", "value": "password123" },
                { "parameter": "salt", "value": "abcd" }
            ],
            "expected": "matches regex '^\\$1\\$abcd\\$[./0-9A-Za-z]{22}$'",
            "description": "Typical case – non‑null key and a valid short salt (1‑8 allowed characters). The resulting hash must start with the MD5 prefix \"$1$\", the salt \"abcd\", a separating \"$\", and a 22‑character Base64‑like digest."
        },
        {
            "input": [
                { "parameter": "keyBytes", "value": "secret" },
                { "parameter": "salt", "value": "abcdefghijk" }
            ],
            "expected": "matches regex '^\\$1\\$abcdefgh\\$[./0-9A-Za-z]{22}$'",
            "description": "Salt longer than 8 characters – only the first 8 characters are used. The method should truncate the supplied salt to \"abcdefgh\"."
        },
        {
            "input": [
                { "parameter": "keyBytes", "value": "admin" },
                { "parameter": "salt", "value": "$1$xyz" }
            ],
            "expected": "matches regex '^\\$1\\$xyz\\$[./0-9A-Za-z]{22}$'",
            "description": "Salt already contains the MD5 prefix. The implementation must strip the leading \"$1$\" before using the remaining part \"xyz\" as the salt."
        },
        {
            "input": [
                { "parameter": "keyBytes", "value": "randomKey" },
                { "parameter": "salt", "value": null }
            ],
            "expected": "matches regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
            "description": "Null salt triggers generation of a random 8‑character salt. The result must start with \"$1$\", followed by exactly 8 allowed characters, another \"$\", and the 22‑character digest."
        }
    ]
}