{
    "testCases": [
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
                            "value": null
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
                    "description": "Test with null salt, should throw IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "invalid_salt"
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
                    "description": "Test with invalid salt format, should throw IllegalArgumentException."
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
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "String",
                    "description": "Test with valid salt and default rounds, should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$rounds=1000$abcdefghij"
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
                    "description": "Test with minimum rounds (1000), should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$rounds=999999999$abcdefghij"
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
                    "description": "Test with maximum rounds (999999999), should return a valid hash string."
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
                            "value": "$6$"
                        },
                        {
                            "parameter": "blocksize",
                            "value": 64
                        },
                        {
                            "parameter": "algorithm",
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "String",
                    "description": "Test with saltPrefix as $6$ and blocksize as 64, should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{}"
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
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "String",
                    "description": "Test with empty keyBytes, should return a valid hash string."
                }
            ]
        }
    ]
}