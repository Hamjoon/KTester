{
    "group": "md5Crypt Null Input Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "null"
                },
                {
                    "parameter": "salt",
                    "value": "$1$abcde"
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
            "description": "Test with null keyBytes to trigger NullPointerException"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"
                },
                {
                    "parameter": "salt",
                    "value": "null"
                },
                {
                    "parameter": "prefix",
                    "value": "null"
                },
                {
                    "parameter": "random",
                    "value": "new SecureRandom()"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null prefix to trigger NullPointerException"
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "[97, 98, 99]"
                },
                {
                    "parameter": "salt",
                    "value": "$1$abcde"
                },
                {
                    "parameter": "prefix",
                    "value": "$1$"
                },
                {
                    "parameter": "random",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null random to trigger NullPointerException"
        }
    ]
}