{
    "group": "nextToken method exception test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the input token is null. This checks the behavior when a null token is passed, which should result in a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "token",
                    "value": "new Token()"
                }
            ],
            "expected": "IOException",
            "description": "Test case where the reader throws an IOException during character reading. This checks the behavior when an IOException is thrown by the reader."
        }
    ]
}