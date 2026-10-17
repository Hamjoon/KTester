{
    "group": "Positive byte‑aligned integer with leading sign byte (128)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "bigInt",
                    "value": "128"
                }
            ],
            "expected": "[-128]",
            "description": "128 needs exactly 8 bits (byte‑aligned) but its most‑significant bit is 1, so `BigInteger.toByteArray()` produces a leading sign‑byte 0x00. The method strips this sign‑byte, returning a single byte 0x80 (signed -128)."
        }
    ]
}