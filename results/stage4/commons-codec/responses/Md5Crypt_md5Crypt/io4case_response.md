{
    "testCases": [
        {
            "group": "md5Crypt with empty keyBytes",
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
                    "expected": "expected hashed output with random salt",
                    "description": "Test md5Crypt with empty keyBytes, expecting a valid hashed output with a random salt."
                }
            ]
        },
        {
            "group": "md5Crypt with empty salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
                        },
                        {
                            "parameter": "salt",
                            "value": ""
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
                    "expected": "IllegalArgumentException with message 'Invalid salt value: '",
                    "description": "Test md5Crypt with empty salt string, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "md5Crypt with long keyBytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "a long key that exceeds typical lengths"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$saltValue"
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
                    "expected": "expected hashed output with long keyBytes",
                    "description": "Test md5Crypt with a long keyBytes, expecting a valid hashed output."
                }
            ]
        },
        {
            "group": "md5Crypt with special characters in keyBytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "!@#$%^&*()_+"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$saltValue"
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
                    "expected": "expected hashed output with special characters in keyBytes",
                    "description": "Test md5Crypt with special characters in keyBytes, expecting a valid hashed output."
                }
            ]
        },
        {
            "group": "md5Crypt with different random instances",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
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
                    "expected": "expected hashed output with random salt",
                    "description": "Test md5Crypt with different instances of Random, expecting consistent hashed outputs."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
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
                    "expected": "expected hashed output with random salt",
                    "description": "Test md5Crypt with a different Random instance, expecting valid hashed output."
                }
            ]
        }
    ]
}