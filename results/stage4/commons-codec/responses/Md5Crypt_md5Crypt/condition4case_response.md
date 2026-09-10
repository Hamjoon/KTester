{
    "testCases": [
        {
            "group": "md5Crypt with null salt",
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
                    "description": "Test md5Crypt with null salt, expecting a valid hashed output with a random salt."
                }
            ]
        },
        {
            "group": "md5Crypt with invalid salt format",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
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
                    "expected": "IllegalArgumentException with message 'Invalid salt value: invalidSaltFormat'",
                    "description": "Test md5Crypt with an invalid salt format, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "md5Crypt with valid salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
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
                    "expected": "expected hashed output with provided salt",
                    "description": "Test md5Crypt with a valid salt, expecting a valid hashed output."
                }
            ]
        },
        {
            "group": "md5Crypt with different prefixes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "sampleKey"
                        },
                        {
                            "parameter": "salt",
                            "value": "$apr1$saltValue"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$apr1$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "expected hashed output with apr1 prefix",
                    "description": "Test md5Crypt with apr1 prefix and valid salt, expecting a valid hashed output."
                }
            ]
        }
    ]
}