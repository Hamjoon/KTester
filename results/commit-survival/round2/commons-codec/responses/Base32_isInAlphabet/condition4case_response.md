{
    "group": "isInAlphabet - branch coverage",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "-1"
                }
            ],
            "expected": "false",
            "description": "Octet is negative; the first condition (octet >= 0) fails, so the method must return false without touching decodeTable."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "70"
                }
            ],
            "expected": "false",
            "description": "Octet is non‑negative but greater than or equal to decodeTable.length (the test sets decodeTable length to 64). The second condition fails, so the method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "10"
                }
            ],
            "expected": "false",
            "description": "Octet is within the valid index range (0‑63) and decodeTable[10] is deliberately set to -1. The third condition fails, so the method returns false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "0"
                }
            ],
            "expected": "true",
            "description": "Octet is the lower bound (0) and decodeTable[0] is set to a value other than -1 (e.g., 0). All three conditions are true, so the method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "63"
                }
            ],
            "expected": "true",
            "description": "Octet is the upper bound (decodeTable.length‑1). decodeTable[63] is set to a non‑‑1 value, so the method returns true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": "63"
                }
            ],
            "expected": "false",
            "description": "Octet is the upper bound but decodeTable[63] is set to -1 for this case. The final condition fails, yielding false."
        }
    ]
}