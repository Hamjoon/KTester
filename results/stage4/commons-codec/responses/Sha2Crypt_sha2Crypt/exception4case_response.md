{
    "output": [
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
        }
    ]
}