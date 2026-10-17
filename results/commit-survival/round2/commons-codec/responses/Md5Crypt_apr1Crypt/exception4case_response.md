{
    "group": "apr1Crypt_byteArray_exceptions",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": null
                }
            ],
            "expected": "IllegalArgumentException",
            "description": "Passing a null byte array should cause the method to throw IllegalArgumentException because the password argument is required."
        }
    ]
}