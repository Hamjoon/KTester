{
    "group": "MurmurHash3.hash32(long, int) – deterministic behavior and seed sensitivity",
    "cases": [
        {
            "input": [
                { "parameter": "data", "value": "0L" },
                { "parameter": "seed", "value": "104729" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result1). Re‑invoking hash32(0L, 104729) must return the same result.",
            "description": "Verify that the method returns a consistent hash for the zero long value with the default seed."
        },
        {
            "input": [
                { "parameter": "data", "value": "Long.MAX_VALUE" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result2) that is different from the result of case 1.",
            "description": "Check handling of the maximum long value with a zero seed; result must be repeatable and distinct from the zero‑data case."
        },
        {
            "input": [
                { "parameter": "data", "value": "Long.MIN_VALUE" },
                { "parameter": "seed", "value": "-1" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result3) that differs from results of cases 1 and 2.",
            "description": "Validate that a negative long value and a negative seed are processed correctly and produce a unique hash."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x1122334455667788L" },
                { "parameter": "seed", "value": "12345" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result4).",
            "description": "Typical non‑trivial data value with a positive seed; ensures the byte‑reversal and two‑mix steps are exercised."
        },
        {
            "input": [
                { "parameter": "data", "value": "0x1122334455667788L" },
                { "parameter": "seed", "value": "12346" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result5) that is NOT equal to result4.",
            "description": "Same data as previous case but with a different seed; verifies that the seed influences the final hash."
        },
        {
            "input": [
                { "parameter": "data", "value": "0xFFFFFFFFFFFFFFFFL" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result6).",
            "description": "All‑bits‑set long value with zero seed, exercising the full 64‑bit reversal and both mix calls."
        },
        {
            "input": [
                { "parameter": "data", "value": "0L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "A deterministic 32‑bit int value (e.g., result7) that differs from case 1 (different seed).",
            "description": "Zero data with zero seed, confirming that changing only the seed changes the hash."
        }
    ]
}