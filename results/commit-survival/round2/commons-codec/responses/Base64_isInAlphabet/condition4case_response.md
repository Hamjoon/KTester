{
    "group": "Base64.isInAlphabet",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": -5
                }
            ],
            "expected": false,
            "description": "Octet is negative; first condition (octet >= 0) fails, method should short‑circuit and return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 10
                }
            ],
            "expected": false,
            "description": "Octet is within the range of the decode table length, but the decode table entry at that index is -1, causing the final condition to fail and the method to return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 8
                }
            ],
            "expected": false,
            "description": "Octet is greater than or equal to decodeTable.length (decodeTable length is 8). The second condition fails, method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 3
                }
            ],
            "expected": true,
            "description": "Octet is non‑negative, less than decodeTable.length, and decodeTable[octet] is not -1. All conditions are satisfied, method should return true."
        }
    ]
}