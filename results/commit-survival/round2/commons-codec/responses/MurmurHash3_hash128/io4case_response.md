{
    "group": "MurmurHash3.hash128(byte[])",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}"
                }
            ],
            "expected": "Result equals MurmurHash3.hash128x64(data) (identical two‑long array)",
            "description": "Array length exactly one 16‑byte block; verifies that the convenience overload produces the same 128‑bit hash as the full‑parameter version."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}"
                }
            ],
            "expected": "Result equals MurmurHash3.hash128x64(data) (identical two‑long array)",
            "description": "Array length exactly two 16‑byte blocks; exercises the main block‑processing loop multiple times."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{(byte)0xFF,(byte)0xEE,(byte)0xDD,(byte)0xCC}"
                }
            ],
            "expected": "Result equals MurmurHash3.hash128x64(data) (identical two‑long array)",
            "description": "Contains negative‑valued bytes (signed values) to ensure sign‑extension does not affect the hash computation."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "byte[1000] filled with sequential values 0..255 repeated"
                }
            ],
            "expected": "Result equals MurmurHash3.hash128x64(data) (identical two‑long array)",
            "description": "Large input (1000 bytes) covering many full 16‑byte blocks and a tail; tests algorithm’s ability to handle long streams without overflow or performance degradation."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x10,0x20,0x30,0x40,0x50}"
                }
            ],
            "expected": "Result equals MurmurHash3.hash128x64(data) and original array remains unchanged",
            "description": "Ensures that hash128 does not mutate the supplied byte array (immutability guarantee)."
        }
    ]
}