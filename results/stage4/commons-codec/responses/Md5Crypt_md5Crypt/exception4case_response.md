{
    "group": "md5Crypt with null keyBytes",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": null
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
            "expected": "NullPointerException",
            "description": "Test md5Crypt with null keyBytes, expecting a NullPointerException."
        }
    ]
}