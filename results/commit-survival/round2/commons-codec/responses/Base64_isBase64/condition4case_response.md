{
    "group": "Base64.isBase64(byte)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 61
                }
            ],
            "expected": true,
            "description": "Octet equals PAD_DEFAULT ('='). The method should return true via the first OR condition."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 65
                }
            ],
            "expected": true,
            "description": "Octet is a valid Base64 character ('A'). It is within the decode table range and DECODE_TABLE[65] = 0 (not -1), so the method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 122
                }
            ],
            "expected": true,
            "description": "Octet is the last lowercase Base64 character ('z'). It is in range and maps to 51, therefore the method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 32
                }
            ],
            "expected": false,
            "description": "Octet is a space character, which is within the table range but maps to -1. The method should fall through to false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 0
                }
            ],
            "expected": false,
            "description": "Octet is the null byte (0). It is in range but DECODE_TABLE[0] = -1, so the method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": -1
                }
            ],
            "expected": false,
            "description": "Octet is negative. The first part of the second condition (octet >= 0) fails, leading to a false result."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 127
                }
            ],
            "expected": false,
            "description": "Octet is the highest possible byte value (DEL). It is within the array length but maps to -1, so the method returns false."
        }
    ]
}