{
    "group": "Valid inputs – basic functionality",
    "cases": [
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{0,1,2,3,4,5,6,7}" },
                { "parameter": "offset", "value": "0" },
                { "parameter": "length", "value": "8" },
                { "parameter": "seed", "value": "104729" }
            ],
            "expected": "long[2] array (non‑null, length 2) containing the 128‑bit MurmurHash3 of the whole byte array with the default seed",
            "description": "Happy‑path test: hash the entire byte array using the default seed; verifies that a valid result is returned."
        },
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{10,20,30,40,50,60,70,80,90,100}" },
                { "parameter": "offset", "value": "2" },
                { "parameter": "length", "value": "5" },
                { "parameter": "seed", "value": "123456" }
            ],
            "expected": "long[2] array (non‑null, length 2) representing the hash of bytes {30,40,50,60,70}",
            "description": "Hash a sub‑range of the array (offset 2, length 5) with a custom positive seed."
        },
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{-1,-2,-3,-4}" },
                { "parameter": "offset", "value": "0" },
                { "parameter": "length", "value": "4" },
                { "parameter": "seed", "value": "-1" }
            ],
            "expected": "long[2] array (non‑null, length 2) – same result as using seed 0xffffffff",
            "description": "Use a negative seed to verify that the method masks the seed to an unsigned 32‑bit value."
        }
    ]
  ,
  "group": "Invalid inputs – argument validation",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "null" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "0" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "NullPointerException",
      "description": "The data array is null; the method should throw NPE before any processing."
    },
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{1,2,3}" },
        { "parameter": "offset", "value": "-1" },
        { "parameter": "length", "value": "2" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "IllegalArgumentException",
      "description": "Negative offset is illegal; method must reject it."
    },
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{1,2,3}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "-5" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "IllegalArgumentException",
      "description": "Negative length is illegal; method must reject it."
    },
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{1,2,3}" },
        { "parameter": "offset", "value": "2" },
        { "parameter": "length", "value": "2" },
        { "parameter": "seed", "value": "0" }
      ],
      "expected": "IndexOutOfBoundsException",
      "description": "offset + length exceeds the array size; method should throw an index‑out‑of‑bounds exception."
    }
  ]
  ,
  "group": "Seed masking consistency",
  "cases": [
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0,1,2,3,4,5,6,7}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "8" },
        { "parameter": "seed", "value": "-1" }
      ],
      "expected": "same as case with seed 0xffffffff (4294967295)",
      "description": "Confirm that a seed of -1 (0xffffffff as unsigned) yields the identical hash as explicitly passing 0xffffffff."
    },
    {
      "input": [
        { "parameter": "data", "value": "new byte[]{0,1,2,3,4,5,6,7}" },
        { "parameter": "offset", "value": "0" },
        { "parameter": "length", "value": "8" },
        { "parameter": "seed", "value": "4294967295" }
      ],
      "expected": "same as case with seed -1",
      "description": "Re‑affirm the unsigned masking by using the maximum unsigned 32‑bit value directly."
    }
  ]
}