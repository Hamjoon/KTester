{
    "group": "Base64.isInAlphabet_nullDecodeTable",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 0
                }
            ],
            "expected": "NullPointerException",
            "description": "decodeTable field is deliberately set to null (e.g., via reflection) before invoking isInAlphabet; accessing decodeTable.length causes a NullPointerException."
        }
    ]
}