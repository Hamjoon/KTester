{
    "group": "MurmurHash3.hash32(long, int) – additional boundary and seed variations",
    "cases": [
        {
            "input": [
                { "parameter": "data", "value": "1L" },
                { "parameter": "seed", "value": "Integer.MAX_VALUE" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultA) that is reproducible on repeated calls and differs from hashes of other data/seed combos.",
            "description": "Small positive long with the maximum positive seed tests handling of low‑order bits and extreme seed value."
        },
        {
            "input": [
                { "parameter": "data", "value": "-1L" },
                { "parameter": "seed", "value": "Integer.MIN_VALUE" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultB) distinct from the hash of (-1L, 0) already covered.",
            "description": "All‑bits‑set long with the most negative seed verifies correct sign‑extension and mixing for extreme seed values."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x00000000FFFFFFFFL" },
                { "parameter": "seed", "value": "123" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultC) that differs from hashes of data where high and low 32‑bit halves are swapped.",
            "description": "Data where only the low 32 bits are set ensures the first mix step processes non‑zero low part while the second mix processes zero high part."
        },
        {
            "input": [
                { "parameter": "data", "value": "0xFFFFFFFF00000000L" },
                { "parameter": "seed", "value": "456" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultD) distinct from resultC, confirming that high‑half‑only data is handled correctly.",
            "description": "Data where only the high 32 bits are set tests the second mix step with a non‑zero value while the first mix receives zero."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x7FFFFFFFFFFFFFFFL" },
                { "parameter": "seed", "value": "-12345" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultE) that is different from the hash of Long.MAX_VALUE with seed 0 (existing case).",
            "description": "Maximum positive long with a negative seed checks proper handling of signed seeds and ensures seed influences the final hash."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x8000000000000000L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultF) that differs from the hash of Long.MIN_VALUE with seed -1 (existing case).",
            "description": "Minimum long value (only the sign bit set) with zero seed validates correct byte‑reversal and mixing for this edge pattern."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x0102030405060708L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultG) distinct from hashes of other patterned data.",
            "description": "Sequential byte pattern tests that the byte‑reversal step correctly reorders bytes before mixing."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x0807060504030201L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., resultH) that is not equal to resultG, confirming that different byte orders produce different hashes.",
            "description": "Reverse sequential byte pattern ensures that the reversal logic is not a no‑op and influences the hash."
        }
    ]
}