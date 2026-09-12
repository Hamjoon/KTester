{
    "group": "sha2Crypt Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "$6$rounds=1000$abcd"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$6$"
                },
                {
                    "parameter": "blocksize",
                    "value": "64"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "Valid Hash",
            "description": "SHA-512 with minimum rounds (1000) and valid salt format, expecting a valid hash output."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "$6$rounds=999999999$abcd"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$6$"
                },
                {
                    "parameter": "blocksize",
                    "value": "64"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "Valid Hash",
            "description": "SHA-512 with maximum rounds (999999999) and valid salt format, expecting a valid hash output."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "$6$rounds=5000$abcd"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$6$"
                },
                {
                    "parameter": "blocksize",
                    "value": "64"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "Valid Hash",
            "description": "SHA-512 with default rounds (5000) and valid salt format, expecting a valid hash output."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "$6$abcd"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$6$"
                },
                {
                    "parameter": "blocksize",
                    "value": "64"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "Valid Hash",
            "description": "SHA-512 with valid salt format without rounds specification, expecting a valid hash output with default rounds."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=5000$"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": "32"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-256"
                }
            ],
            "expected": "Valid Hash",
            "description": "Test case with a valid salt prefix and rounds but no actual salt, expecting a valid hash output with default rounds."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] {}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$abcd"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": "32"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-256"
                }
            ],
            "expected": "Valid Hash",
            "description": "Test case with empty keyBytes, expecting a valid hash output."
        }
    ]
}