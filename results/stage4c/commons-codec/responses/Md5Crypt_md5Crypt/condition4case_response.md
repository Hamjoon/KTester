{
    "group": "md5Crypt Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"  },
                {
                    "parameter": "salt",
                    "value": "null"
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
            "expected": "Generated hash with random salt",
            "description": "Test with null salt to trigger random salt generation"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"  },
                {
                    "parameter": "salt",
                    "value": "$1$abcdefg"
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
            "expected": "Generated hash with provided salt",
            "description": "Test with valid salt that matches pattern"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"  },
                {
                    "parameter": "salt",
                    "value": "$1$invalid_salt"
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
            "expected": "IllegalArgumentException with message 'Invalid salt value: $1$invalid_salt'",
            "description": "Test with invalid salt that does not match pattern"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"  },
                {
                    "parameter": "salt",
                    "value": "$apr1$abcdefg"
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
            "expected": "Generated hash with provided salt using APR1 prefix",
            "description": "Test with APR1 prefix and valid salt"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"  },
                {
                    "parameter": "salt",
                    "value": "null"
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
            "expected": "Generated hash with random salt using APR1 prefix",
            "description": "Test with null salt and APR1 prefix to trigger random salt generation"
        }
    ]
}