{
    "group": "sha2Crypt Method Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "null"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": "32"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-256"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case where the salt is null, expecting an IllegalArgumentException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[] { 1, 2, 3 }"
                },
                {
                    "parameter": "salt",
                    "value": "invalidSalt"
                },
                {
                    "parameter": "saltPrefix",
                    "value": "$5$"
                },
                {
                    "parameter": "blocksize",
                    "value": "32"
                },
                {
                    "parameter": "algorithm",
                    "value": "SHA-256"
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Test case with an invalid salt format, expecting an IllegalArgumentException to be thrown."
        }
    ]
}