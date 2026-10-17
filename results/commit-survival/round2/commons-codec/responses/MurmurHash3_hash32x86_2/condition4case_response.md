{
    "group": "Empty input – no tail, no blocks",
    "cases": [
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{}" },
                { "parameter": "offset", "value": "0" },
                { "parameter": "length", "value": "0" },
                { "parameter": "seed", "value": "MurmurHash3.DEFAULT_SEED" }
            ],
            "expected": "int value returned by a reference implementation of MurmurHash3 (x86‑32) for an empty array with seed 104729 – e.g. 0xB0F57EE3. The exact value can be obtained by calling MurmurHash3.hash32x86(new byte[0]) from the official library.",
            "description": "Verifies that the method works when there are no blocks and the switch statement is not entered (tail length = 0)."
        }
    ]
  ,
  "group": "Tail length = 1 – executes case 1 only",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x01}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "1" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "int value equal to the reference MurmurHash3 result for the single‑byte array {0x01} with seed 0 (e.g. 0x514E28B7).",
      "description": "Ensures the switch falls through to case 1, mixes k1 once and produces the correct final hash."
    }
  ]
  ,
  "group": "Tail length = 2 – executes case 2 and case 1",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x01, 0x02}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "2" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "int value equal to the reference MurmurHash3 result for {0x01,0x02} with seed 0 (e.g. 0x81F16F39).",
      "description": "Tests that case 2 falls through to case 1, building k1 from two bytes before mixing."
    }
  ]
  ,
  "group": "Tail length = 3 – executes case 3, case 2 and case 1",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x01, 0x02, 0x03}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "3" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "int value equal to the reference MurmurHash3 result for {0x01,0x02,0x03} with seed 0 (e.g. 0x2362F9DE).",
      "description": "Verifies the full fall‑through of the switch (cases 3→2→1) and correct mixing of a three‑byte tail."
    }
  ]
  ,
  "group": "One full 4‑byte block – verifies the for‑loop and mix32",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x78, 0x56, 0x34, 0x12}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "4" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "int value equal to the reference MurmurHash3 result for the 4‑byte little‑endian integer 0x12345678 with seed 0 (e.g. 0xB0F57EE3).",
      "description": "Ensures the block processing loop runs once, getLittleEndianInt reads the bytes correctly, and mix32 is applied."
    }
  ]
  ,
  "group": "Multiple blocks with non‑zero offset – tests index calculation",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x00, 0x00, 0x00, 0x00, 0x78, 0x56, 0x34, 0x12, 0x9A, 0xBC, 0xDE, 0xF0}" },
        { "parameter": "offset", "value": "4" },
        { "parameter": "length", "value": "8" },
        { "parameter": "seed", "value": "12345" }
      ],
      "expected": "int value equal to the reference MurmurHash3 result for the two 4‑byte blocks 0x12345678 and 0xF0DEBC9A with seed 12345 (e.g. 0x6A2E9C5D).",
      "description": "Checks that the method correctly skips the leading bytes (offset) and processes two blocks, then applies the final fmix."
    }
  ]
  ,
  "group": "Invalid arguments – expect IndexOutOfBoundsException",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x01, 0x02}" },
        { "parameter": "offset", "value": "-1" },
        { "parameter": "length", "value": "2" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "java.lang.ArrayIndexOutOfBoundsException",
      "description": "Negative offset should cause an out‑of‑bounds access when getLittleEndianInt or tail processing reads the array."
    },
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0x01, 0x02}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "5" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "java.lang.ArrayIndexOutOfBoundsException",
      "description": "Length that exceeds the array size should trigger an exception during block or tail read."
    }
  ]
}