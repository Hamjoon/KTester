{
    "group": "MurmurHash3.hash32(long, long, int) - additional edge and order sensitivity",
    "cases": [
        {
            "input": [
                { "parameter": "data1", "value": "Long.MIN_VALUE" },
                { "parameter": "data2", "value": "0L" },
                { "parameter": "seed", "value": "MurmurHash3.DEFAULT_SEED" }
            ],
            "expected": "deterministic int hash (different from all previously listed hashes). No exception is thrown.",
            "description": "Tests handling of the most negative long value combined with a zero long while using the default seed. This exercises the sign‑bit propagation through Long.reverseBytes and the mix steps."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0L" },
                { "parameter": "data2", "value": "Long.MIN_VALUE" },
                { "parameter": "seed", "value": "MurmurHash3.DEFAULT_SEED" }
            ],
            "expected": "deterministic int hash (different from the previous case, confirming that the order of the two long arguments matters). No exception is thrown.",
            "description": "Same values as the previous case but swapped; verifies that hash32 is not commutative with respect to data1 and data2."
        },
        {
            "input": [
                { "parameter": "data1", "value": "Long.MIN_VALUE" },
                { "parameter": "data2", "value": "Long.MAX_VALUE" },
                { "parameter": "seed", "value": "Integer.MIN_VALUE" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from all other cases). No exception is thrown.",
            "description": "Combines the extreme negative and positive long values together with the most negative int seed, stressing the algorithm's ability to mix opposite‑sign inputs."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0x00000000FFFFFFFFL" },
                { "parameter": "data2", "value": "0xFFFFFFFF00000000L" },
                { "parameter": "seed", "value": "12345" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from all other cases). No exception is thrown.",
            "description": "Uses a pattern where only the low 32 bits of the first long and the high 32 bits of the second long are set. This forces mix32 to process distinct low/high halves and checks that no accidental masking occurs."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0x0123456789ABCDEFL" },
                { "parameter": "data2", "value": "0xFEDCBA9876543210L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "deterministic int hash (different from the case that uses seed 12345, confirming seed sensitivity). No exception is thrown.",
            "description": "Repeats the mixed‑pattern data from an existing test but changes the seed to zero, ensuring that the seed influences the final hash even when the data payload is identical."
        },
        {
            "input": [
                { "parameter": "data1", "value": "0xFFFFFFFFFFFFFFFFL" },
                { "parameter": "data2", "value": "0x0000000000000000L" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "deterministic int hash (non‑zero, distinct from all other cases). No exception is thrown.",
            "description": "All‑bits‑set long for data1 combined with a zero long for data2 and a zero seed. This checks correct handling of unsigned overflow and the effect of a zero seed on a maximal data value."
        }
    ]
}