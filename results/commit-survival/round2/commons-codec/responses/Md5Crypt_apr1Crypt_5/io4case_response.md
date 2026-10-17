{
    "group": "apr1Crypt_byteArrayString",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'s','e','c','r','e','t'}"
                },
                {
                    "parameter": "salt",
                    "value": null
                }
            ],
            "expected": "^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
            "description": "When salt is null the method should generate a random 8‑character salt and return a string that matches the standard APR‑1 hash format."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'p','a','s','s','w','o','r','d'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"\""
                }
            ],
            "expected": "^\\$apr1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
            "description": "An empty salt string is not prefixed, so the method adds the APR‑1 prefix and then lets md5Crypt generate a random salt; the result must still conform to the APR‑1 format."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'p','a','s','s','w','o','r','d'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"mysalt\""
                }
            ],
            "expected": "^\\$apr1\\$mysalt\\$[./0-9A-Za-z]{22}$",
            "description": "A salt without the required prefix should be automatically prefixed with \"$apr1$\"; the final hash must contain the original salt text after the prefix."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'p','a','s','s','w','o','r','d'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"$apr1$mysalt\""
                }
            ],
            "expected": "^\\$apr1\\$mysalt\\$[./0-9A-Za-z]{22}$",
            "description": "When the supplied salt already starts with the APR‑1 prefix the method must not add another prefix; the resulting hash should be identical to the previous case."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'p','a','s','s','w','o','r','d'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"$1$mysalt\""
                }
            ],
            "expected": "^\\$apr1\\$\\$1\\$mysalt\\$[./0-9A-Za-z]{22}$",
            "description": "If the supplied salt starts with a different prefix (e.g., \"$1$\"), the method must still prepend the APR‑1 prefix, resulting in a double‑prefix string \"$apr1$$1$mysalt\" before hashing."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'p','a','s','s','w','o','r','d'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"mysalt\""
                }
            ],
            "expected": "same as Md5Crypt.apr1Crypt(new byte[]{'p','a','s','s','w','o','r','d'}, \"$apr1$mysalt\")",
            "description": "Providing a salt with and without the APR‑1 prefix must produce identical hashes; this case asserts equality between the two possible calls."
        }
    ]
}