{
    "test cases": [
        {
            "group": "sha2Crypt - Valid Salt with Minimum Rounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=1000$validSalt"
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
                    "description": "Test case to check behavior with valid salt and minimum rounds (1000), expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Maximum Rounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$rounds=999999999$validSalt"
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
                    "description": "Test case to check behavior with valid salt and maximum rounds (999999999), expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Edge Case Block Size",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$validSalt"
                        },
                        {
                            "parameter": "saltPrefix",
                            "value": "$6$"
                        },
                        {
                            "parameter": "blocksize",
                            "value": 1
                        },
                        {
                            "parameter": "algorithm",
                            "value": "SHA-512"
                        }
                    ],
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt and a block size of 1, expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Valid Salt with Different Algorithms",
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
                            "value": "SHA-256"
                        }
                    ],
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt using SHA-256 algorithm, expecting a non-null string."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$validSalt"
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
                            "value": "SHA-1"
                        }
                    ],
                    "expected": "string",
                    "description": "Test case to check behavior with valid salt using SHA-1 algorithm, expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Empty Salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$$"
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
                    "description": "Test case to check behavior with valid salt containing an empty salt string, expecting a non-null string."
                }
            ]
        },
        {
            "group": "sha2Crypt - Invalid Salt with Custom Rounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "someKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$6$rounds=abc$validSalt"
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
                    "expected": "IllegalArgumentException",
                    "description": "Test case to check behavior with invalid salt format for custom rounds, expecting an IllegalArgumentException."
                }
            ]
        }
    ]
}