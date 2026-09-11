{
    "group": "md5Crypt - Invalid Salt Value",
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
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "sampleKeyBytes"
                },
                {
                    "parameter": "salt",
                    "value": "$1$invalidSalt"
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
            "description": "Test md5Crypt with a salt that does not match the expected pattern, expecting an IllegalArgumentException."
        }
    ]
}