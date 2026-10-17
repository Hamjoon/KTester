{
    "group": "isInAlphabet - exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "0"
                }
            ],
            "expected": "NullPointerException",
            "description": "decodeTable is null; octet is non‑negative, so evaluation proceeds to decodeTable.length, causing a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "127"
                }
            ],
            "expected": "NullPointerException",
            "description": "decodeTable is null and octet is within the positive byte range; the method attempts to read decodeTable.length, resulting in a NullPointerException."
        }
    ]
}