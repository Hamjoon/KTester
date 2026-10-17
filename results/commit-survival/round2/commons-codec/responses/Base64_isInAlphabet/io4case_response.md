{
    "group": "Base64.isInAlphabet",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 0
                }
            ],
            "expected": false,
            "description": "Lowest non‑negative octet; NUL (0) is not part of the Base64 alphabet, so decodeTable[0] == -1 and the method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 65
                }
            ],
            "expected": true,
            "description": "Octet corresponds to ASCII 'A', the first character of the standard Base64 alphabet; decodeTable[65] != -1, method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 64
                }
            ],
            "expected": false,
            "description": "Octet is ASCII '@', which is not in the Base64 alphabet; decodeTable[64] == -1, method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 122
                }
            ],
            "expected": true,
            "description": "Octet is ASCII 'z', a valid Base64 alphabet character; decodeTable[122] != -1, method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 43
                }
            ],
            "expected": true,
            "description": "Octet is ASCII '+', a valid Base64 alphabet character; decodeTable[43] != -1, method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 127
                }
            ],
            "expected": false,
            "description": "Octet is the highest index within the typical decode table (127); DEL character is not in the alphabet, decodeTable[127] == -1, method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": -128
                }
            ],
            "expected": false,
            "description": "Minimum byte value; negative octet fails the first condition (octet >= 0) and the method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": -1
                }
            ],
            "expected": false,
            "description": "Octet -1 (0xFF) is negative; first condition fails, method returns false."
        }
    ]
}