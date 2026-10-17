{
    "group": "apr1Crypt_byteArrayString_exceptions",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": null
                },
                {
                    "parameter": "salt",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Passing a null keyBytes array should cause a NullPointerException, irrespective of the salt value (null in this case)."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": null
                },
                {
                    "parameter": "salt",
                    "value": "\"mysalt\""
                }
            ],
            "expected": "NullPointerException",
            "description": "A null keyBytes array must still trigger a NullPointerException even when a non‑null salt is supplied."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'s','e','c','r','e','t'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"$apr1$\""
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Supplying a salt that is exactly the APR‑1 prefix (i.e., no actual salt characters) should be considered malformed and result in an IllegalArgumentException from the underlying md5Crypt implementation."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{'s','e','c','r','e','t'}"
                },
                {
                    "parameter": "salt",
                    "value": "\"$1$invalid$\""
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "A salt that starts with a different prefix leads to a double‑prefix string \"$apr1$$1$invalid$\"; this malformed salt should cause an IllegalArgumentException."
        }
    ]
}