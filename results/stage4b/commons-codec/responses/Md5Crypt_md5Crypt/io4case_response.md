{
    "testCases": [
        {
            "group": "md5Crypt - Empty Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": ""
                        },
                        {
                            "parameter": "salt",
                            "value": null
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with an empty key, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Long Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "aVeryLongKeyThatExceedsNormalLengthForTestingPurpose"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$validSalt"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with a long key, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Special Characters in Key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "key@#$$%^&*()"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$validSalt"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with special characters in the key, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Max Length Salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$abcdefgh"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with maximum length salt (8 characters), expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Invalid Prefix",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$validSalt"
                        },
                        {
                            "parameter": "prefix",
                            "value": "invalidPrefix"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with an invalid prefix, expecting a valid MD5 hash result despite prefix."
                }
            ]
        },
        {
            "group": "md5Crypt - Random Instance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": null
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new Random()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with a standard Random instance, expecting a valid MD5 hash result."
                }
            ]
        }
    ]
}