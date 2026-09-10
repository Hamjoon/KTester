{
    "testCases": [
        {
            "group": "sha2Crypt - Null Salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
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
                    "description": "Test case to check behavior when salt is null, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "sha2Crypt - Invalid Salt Format",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "invalidSaltFormat"
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
                    "description": "Test case to check behavior when salt format is invalid, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Custom Rounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=1500$validSalt"
                        },
                        {
                            "parameter": "saltPrefix",
                            "value": "$6$"
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
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt and custom rounds, expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Default Rounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$validSalt"
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
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt and default rounds, expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Rounds Exceeding Maximum",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=1000000000$validSalt"
                        },
                        {
                            "parameter": "saltPrefix",
                            "value": "$6$"
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
                    "expected": "string",
                    "description": "Test case to check behavior with rounds exceeding maximum, expecting rounds to be capped at ROUNDS_MAX."
                }
            ]
        },
        {
            "group": "sha2Crypt - Rounds Below Minimum",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=500$validSalt"
                        },
                        {
                            "parameter": "saltPrefix",
                            "value": "$6$"
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
                    "expected": "string",
                    "description": "Test case to check behavior with rounds below minimum, expecting rounds to be set to ROUNDS_MIN."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Different Block Sizes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$validSalt"
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
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt and a different block size, expecting a non-null string."
                }
            ]
        }
    ]
}