{
    "group": "hash128x64(byte[]) delegation and edge‑case tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x01, 0x02, 0x03, 0x04}"
                }
            ],
            "expected": "The method returns the same long[2] array as MurmurHash3.hash128x64(data, 0, data.length, 0).",
            "description": "Typical non‑empty input – verifies that the one‑argument overload forwards the call correctly with offset 0, length =data.length and seed 0 and returns the exact same 128‑bit hash."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{}"
                }
            ],
            "expected": "The method returns the same long[2] array as MurmurHash3.hash128x64(data, 0, 0, 0) (i.e. the hash of an empty array).",
            "description": "Empty array – checks that the overload works for length 0 and that the delegation does not throw or alter the result."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Null input – the one‑argument overload should immediately throw NPE because it tries to read data.length."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[1024]" 
                }
            ],
            "expected": "The method returns the same long[2] array as MurmurHash3.hash128x64(data, 0, 1024, 0).",
            "description": "Large input (1 KB) – ensures that the delegation works for bigger payloads and does not hit any hidden length‑related branches."
        }
    ]
}