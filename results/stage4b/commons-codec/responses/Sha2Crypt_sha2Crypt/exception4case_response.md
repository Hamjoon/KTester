{
    "group": "Test sha2Crypt Method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{1, 2, 3, 4, 5}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=9999999999$abcdefghij"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": 32
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "String",
            "description": "Test with rounds exceeding the maximum limit (9999999999), should return a valid hash string due to internal handling."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{1, 2, 3, 4, 5}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=5000$abcdefghij$extra"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": 32
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test with salt containing extra characters after valid format, should throw IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{1, 2, 3, 4, 5}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=5000$abcdefghij"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": 0
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test with blocksize set to 0, should throw IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{1, 2, 3, 4, 5}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=5000$abcdefghij"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": -1
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-512"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test with blocksize set to a negative value, should throw IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{1, 2, 3, 4, 5}"
                },
                {
                    "parameter": "salt",
                    "value": "$5$rounds=5000$abcdefghij"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": 32
                },
                {
                    "parameter": "algorithm",
                    "value": "INVALID_ALGORITHM"
                }
            ],
            "expected": "NoSuchAlgorithmException",
            "description": "Test with an invalid algorithm name, should throw NoSuchAlgorithmException."
        }
    ]
}