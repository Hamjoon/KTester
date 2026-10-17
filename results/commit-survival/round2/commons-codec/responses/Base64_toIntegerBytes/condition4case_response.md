{
    "testCases": [
        {
            "group": "Non‑byte‑aligned positive integer",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInt",
                            "value": "291"
                        }
                    ],
                    "expected": "[1, 35]",
                    "description": "291 (0x0123) needs 9 bits → not a multiple of 8. The first `if` condition is true, so the method should return the original `bigInt.toByteArray()` which is [0x01, 0x23] (decimal [1,35])."
                }
            ]
        },
        {
            "group": "Byte‑aligned positive integer",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInt",
                            "value": "255"
                        }
                    ],
                    "expected": "[-1]",
                    "description": "255 (0xFF) needs exactly 8 bits → byte‑aligned. The first `if` is false, the second `if` is true, causing the leading sign‑byte (0x00) to be stripped. The result should be a single byte 0xFF (signed -1)."
                }
            ]
        },
        {
            "group": "Zero value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInt",
                            "value": "0"
                        }
                    ],
                    "expected": "[]",
                    "description": "Zero has a bitLength of 0. After rounding, `bitlen` is 0, the method creates a zero‑length array and returns it."
                }
            ]
        },
        {
            "group": "Non‑byte‑aligned negative integer",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInt",
                            "value": "-128"
                        }
                    ],
                    "expected": "[-128]",
                    "description": "-128 requires 7 bits (not a multiple of 8). The first `if` evaluates to true, so the method returns the original `toByteArray()` which for -128 is a single byte 0x80 (signed -128)."
                }
            ]
        },
        {
            "group": "Byte‑aligned negative integer",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInt",
                            "value": "-255"
                        }
                    ],
                    "expected": "[1]",
                    "description": "-255 needs 8 bits → byte‑aligned. The first `if` is false, the second `if` is true, so the leading sign‑byte (0xFF) is removed and the remaining byte 0x01 (decimal 1) is returned."
                }
            ]
        }
    ]
}