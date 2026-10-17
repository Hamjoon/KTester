{
    "group": "MurmurHash3.hash32(long, long, int) - branch and value coverage",
    "cases": [
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" },
                { "parameter": "seed",  "value": "MurmurHash3.DEFAULT_SEED" }
            ],
            "expected": "deterministic int hash (e.g., 0x5A5A5A5A). The result must be the same on every execution and must not throw an exception.",
            "description": "Baseline test using the default seed and zero data. Verifies that the method returns a consistent hash value when all inputs are zero."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "0L" },
                { "parameter": "seed",  "value": "0" }
            ],
            "expected": "deterministic int hash (different from the hash obtained with the default seed). No exception is thrown.",
            "description": "Checks that changing the seed influences the final hash even when the data payload is all zeros."
        },
        {
            "input": [
                { "parameter": "data1", "value": "Long.MAX_VALUE" },
                { "parameter": "data2", "value": "Long.MAX_VALUE" },
                { "parameter": "seed",  "value": "Integer.MAX_VALUE" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from previous cases). No exception is thrown.",
            "description": "Stress test with the maximum positive long values and the maximum positive int seed to ensure the algorithm correctly handles large positive numbers."
        },
        {
            "input": [
                { "parameter": "data1", "value": "-1L" },
                { "parameter": "data2", "value": "-1L" },
                { "parameter": "seed",  "value": "-1" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from all other cases). No exception is thrown.",
            "description": "Uses all‑bits‑set values (‑1) for both longs and a negative seed to verify that sign‑extension and two's‑complement handling are correct."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0x0123456789ABCDEFL" },
                { "parameter": "data2", "value": "0xFEDCBA9876543210L" },
                { "parameter": "seed",  "value": "12345" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from previous cases). No exception is thrown.",
            "description": "Mixed pattern data with a non‑default seed to exercise all four calls to mix32 (low/high parts of both longs) and to trigger any internal conditional logic inside mix32/fmix32."
        }
    ]
}