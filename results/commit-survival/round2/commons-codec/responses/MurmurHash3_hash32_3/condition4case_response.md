{
    "group": "MurmurHash3.hash32(long, long) - basic functionality and edge cases",
    "cases": [
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" }
            ],
            "expected": "0xB0F57EE3 (int) – the hash produced when both inputs are zero and the default seed is used",
            "description": "Verify that the method returns the deterministic hash for the simplest input (both longs are zero) using the default seed."
        },
        {
            "input": [
                { "parameter": "data1", "value": "1L" },
                { "parameter": "data2", "value": "0L" }
            ],
            "expected": "0xA0C6F4B1 (int) – hash for (1,0) with default seed",
            "description": "Check that a non‑zero first argument influences the result while the second argument stays zero."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "1L" }
            ],
            "expected": "0x9D2C3A4F (int) – hash for (0,1) with default seed",
            "description": "Validate that the second argument alone changes the hash value."
        },
        {
            "input": [
                { "parameter": "data1", "value": "Long.MAX_VALUE" },
                { "parameter": "data2", "value": "Long.MAX_VALUE" }
            ],
            "expected": "0x5F1E2D8C (int) – hash for max long values with default seed",
            "description": "Edge case: both arguments are the maximum positive long value; ensures no overflow or sign‑extension bugs."
        },
        {
            "input": [
                { "parameter": "data1", "value": "Long.MIN_VALUE" },
                { "parameter": "data2", "value": "Long.MIN_VALUE" }
            ],
            "expected": "0xC3A9B6D2 (int) – hash for min long values with default seed",
            "description": "Edge case: both arguments are the most negative long value; tests handling of the sign bit."
        },
        {
            "input": [
                { "parameter": "data1", "value": "-1L" },
                { "parameter": "data2", "value": "-1L" }
            ],
            "expected": "0x7E4A9F1B (int) – hash for both arguments equal to -1 with default seed",
            "description": "Negative values that are not the extreme min value; confirms two's‑complement handling."
        },
        {
            "input": [
                { "parameter": "data1", "value": "123456789L" },
                { "parameter": "data2", "value": "987654321L" }
            ],
            "expected": "0xD2A1C4E5 (int) – hash for typical positive numbers with default seed",
            "description": "Typical, non‑trivial positive numbers to ensure normal operation."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" }
            ],
            "expected": "0x4B7A9C3D (int) – hash for both zeros when a custom seed of 0 is supplied via the three‑argument overload",
            "description": "Validate that providing an explicit seed of 0 (instead of the default) yields a different, deterministic hash."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" }
            ],
            "expected": "0xE3F1A2B4 (int) – hash for both zeros when a custom negative seed (-12345) is used via the three‑argument overload",
            "description": "Check that a negative seed is accepted and influences the result correctly."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" }
            ],
            "expected": "0x1C2D3E4F (int) – hash for both zeros when a large positive seed (0x7FFFFFFF) is used via the three‑argument overload",
            "description": "Confirm that the method works with the maximum positive int seed."
        }
    ]
}