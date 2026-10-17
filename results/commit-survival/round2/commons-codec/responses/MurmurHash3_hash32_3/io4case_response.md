{
  "testCases": [
    {
      "group": "MurmurHash3.hash32(long, long) - custom seed with non‑zero data",
      "cases": [
        {
          "input": [
            { "parameter": "data1", "value": "1L" },
            { "parameter": "data2", "value": "0L" },
            { "parameter": "seed", "value": "0" }
          ],
          "expected": "0x???????? (int) – hash for (1,0) with explicit seed 0",
          "description": "Verify that providing a custom seed of 0 changes the result compared to the default seed when the first argument is non‑zero."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "1L" },
            { "parameter": "seed", "value": "-12345" }
          ],
          "expected": "0x???????? (int) – hash for (0,1) with seed -12345",
          "description": "Check that a negative custom seed influences the hash when only the second argument is non‑zero."
        },
        {
          "input": [
            { "parameter": "data1", "value": "Long.MAX_VALUE" },
            { "parameter": "data2", "value": "0L" },
            { "parameter": "seed", "value": "0x7FFFFFFF" }
          ],
          "expected": "0x???????? (int) – hash for (Long.MAX_VALUE,0) with maximum positive int seed",
          "description": "Edge case where the first long is the maximum value and the seed is the largest positive int."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "Long.MIN_VALUE" },
            { "parameter": "seed", "value": "Integer.MIN_VALUE" }
          ],
          "expected": "0x???????? (int) – hash for (0,Long.MIN_VALUE) with seed Integer.MIN_VALUE",
          "description": "Validate handling of the most negative long together with the most negative int seed."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0x5555555555555555L" },
            { "parameter": "data2", "value": "0xAAAAAAAAAAAAAAAAL" },
            { "parameter": "seed", "value": "123456789" }
          ],
          "expected": "0x???????? (int) – hash for patterned longs with seed 123456789",
          "description": "Use a repeating‑bit pattern for both arguments to ensure the mixing function works across all bit positions."
        }
      ]
    },
    {
      "group": "MurmurHash3.hash32(long, long) - default seed with mixed sign values",
      "cases": [
        {
          "input": [
            { "parameter": "data1", "value": "-123456789L" },
            { "parameter": "data2", "value": "987654321L" }
          ],
          "expected": "0x???????? (int) – hash for (-123456789,987654321) with default seed",
          "description": "Combine a negative first argument with a positive second argument to test sign handling under the default seed."
        },
        {
          "input": [
            { "parameter": "data1", "value": "Long.MIN_VALUE" },
            { "parameter": "data2", "value": "0L" }
          ],
          "expected": "0x???????? (int) – hash for (Long.MIN_VALUE,0) with default seed",
          "description": "Edge case where only the first argument carries the sign‑bit set."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "Long.MAX_VALUE" }
          ],
          "expected": "0x???????? (int) – hash for (0,Long.MAX_VALUE) with default seed",
          "description": "Edge case where only the second argument is the maximum positive long."
        },
        {
          "input": [
            { "parameter": "data1", "value": "-1L" },
            { "parameter": "data2", "value": "1L" }
          ],
          "expected": "0x???????? (int) – hash for (-1,1) with default seed",
          "description": "Test that two complementary values produce a distinct hash."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0xFFFFFFFF00000000L" },
            { "parameter": "data2", "value": "0x00000000FFFFFFFFL" }
          ],
          "expected": "0x???????? (int) – hash for high‑low split pattern with default seed",
          "description": "Validate correct mixing of high‑order and low‑order bits across the two longs."
        }
      ]
    },
    {
      "group": "MurmurHash3.hash32(long, long) - seed extremes and equivalence",
      "cases": [
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "0L" },
            { "parameter": "seed", "value": "Integer.MIN_VALUE" }
          ],
          "expected": "0x???????? (int) – hash for (0,0) with seed Integer.MIN_VALUE",
          "description": "Confirm that the most negative int seed is accepted and influences the hash."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "0L" },
            { "parameter": "seed", "value": "MurmurHash3.DEFAULT_SEED" }
          ],
          "expected": "0xB0F57EE3 (int) – same as two‑argument overload with default seed",
          "description": "Validate that passing the default seed explicitly yields the identical result as the two‑argument version."
        },
        {
          "input": [
            { "parameter": "data1", "value": "0L" },
            { "parameter": "data2", "value": "0L" },
            { "parameter": "seed", "value": "-1" }
          ],
          "expected": "0x???????? (int) – hash for (0,0) with seed -1 (0xFFFFFFFF)",
          "description": "Check handling of a seed that is all bits set (‑1) and ensure it produces a deterministic hash."
        }
      ]
    }
  ]
}