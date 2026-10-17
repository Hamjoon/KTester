{
    "group": "MurmurHash3.hash128(byte[])",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Passing a null byte array should cause a NullPointerException because the method forwards the argument to the overloaded version without a null‑check."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[0]"
                }
            ],
            "expected": "long[2] array (non‑null, length == 2)",
            "description": "Hashing an empty array must return a valid 128‑bit hash (two longs). The exact values are deterministic for the default seed (104729) and can be compared against a reference implementation."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0}"
                }
            ],
            "expected": "long[2] array (non‑null, length == 2)",
            "description": "A single‑byte array (value 0) exercises the main loop of the algorithm with a length that is not a multiple of 16, covering the tail‑processing branch."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x61, 0x62, 0x63}"  ,
                    "description": "ASCII characters \"abc\""
                }
            ],
            "expected": "long[2] array (non‑null, length == 2)",
            "description": "A typical multi‑byte input that spans more than one block, ensuring the main block‑processing branch is executed."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x10, 0x20, 0x30, 0x40, 0x50}"
                }
            ],
            "expected": "long[2] array (non‑null, length == 2) equal to hash128(data, 0, data.length, MurmurHash3.DEFAULT_SEED)",
            "description": "Verifies that the convenience overload uses the default seed and the same offset/length as the full‑parameter version."
        }
    ]
}