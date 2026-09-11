{
    "test cases": [
        {
            "group": "md5Crypt - Null Salt",
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
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with null salt, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Invalid Salt Format",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKeyBytes"
                        },
                        {
                            "parameter": "salt",
                            "value": "invalidSaltFormat"
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
                    "expected": "IllegalArgumentException",
                    "description": "Test md5Crypt with an invalid salt format, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "md5Crypt - Valid Salt",
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
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt with a valid salt, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Edge Case Key Length",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "smallKey"
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
                    "description": "Test md5Crypt with a small key length, expecting a valid MD5 hash result."
                }
            ]
        },
        {
            "group": "md5Crypt - Multiple Rounds",
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
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "string result",
                    "description": "Test md5Crypt to ensure that it processes multiple rounds correctly, expecting a valid MD5 hash result."
                }
            ]
        }
    ]
}