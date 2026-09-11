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
                            "value": "$5$rounds=2000$abcdefghij"
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
                    "description": "Test with valid salt and custom rounds (2000), should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$rounds=1001$abcdefghij"
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
                    "description": "Test with valid salt and rounds just above the minimum (1001), should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=5000$abcdefghij"
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
                    "description": "Test with valid salt, $6$ prefix and blocksize 64, should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5}"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$rounds=999999998$abcdefghij"
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
                    "description": "Test with valid salt and rounds just below the maximum (999999998), should return a valid hash string."
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
                            "value": 16
                        },
                        {
                            "parameter": "algorithm",
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "String",
                    "description": "Test with valid salt and blocksize smaller than default (16), should return a valid hash string."
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
                            "value": 64
                        },
                        {
                            "parameter": "algorithm",
                            "value": "SHA-256"
                        }
                    ],
                    "expected": "String",
                    "description": "Test with SHA-256 algorithm, should return a valid hash string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}"
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
                    "description": "Test with larger keyBytes, should return a valid hash string."
                }
            ]
        }
    ]
}