{
    "groups": [
        {
            "group": "Overloaded method equivalence",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{10, 20, 30, 40, 50}"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "5"
                        },
                        {
                            "parameter": "seed",
                            "value": "MurmurHash3.DEFAULT_SEED"
                        }
                    ],
                    "expected": "identical to MurmurHash3.hash128x64(new byte[]{10,20,30,40,50})",
                    "description": "The three‑argument overload should produce the same 128‑bit hash as the single‑argument overload when called with the full array and the default seed."
                }
            ]
        },
        {
            "group": "Empty input handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[0]"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "0"
                        },
                        {
                            "parameter": "seed",
                            "value": "0"
                        }
                    ],
                    "expected": "[0, 0]",
                    "description": "Hash of an empty byte array with seed 0 should return two zero longs (the canonical empty‑input result)."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[0]"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "0"
                        },
                        {
                            "parameter": "seed",
                            "value": "12345"
                        }
                    ],
                    "expected": "non‑null long[2] array (different from [0,0] because seed is non‑zero)",
                    "description": "Even with zero length, a non‑zero seed must influence the result; the method must not throw and must return a two‑element long array."
                }
            ]
        },
        {
            "group": "Sub‑array hashing",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{0,1,2,3,4,5,6,7,8,9}"
                        },
                        {
                            "parameter": "offset",
                            "value": "2"
                        },
                        {
                            "parameter": "length",
                            "value": "5"
                        },
                        {
                            "parameter": "seed",
                            "value": "42"
                        }
                    ],
                    "expected": "identical to MurmurHash3.hash128x64(new byte[]{2,3,4,5,6},0,5,42)",
                    "description": "Hashing a slice of the original array must be equivalent to hashing a newly created array that contains only that slice."
                }
            ]
        },
        {
            "group": "Offset/length boundary checks",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{1,2,3}"
                        },
                        {
                            "parameter": "offset",
                            "value": "-1"
                        },
                        {
                            "parameter": "length",
                            "value": "2"
                        },
                        {
                            "parameter": "seed",
                            "value": "0"
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Negative offset should cause an IndexOutOfBoundsException."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{1,2,3}"
                        },
                        {
                            "parameter": "offset",
                            "value": "2"
                        },
                        {
                            "parameter": "length",
                            "value": "-1"
                        },
                        {
                            "parameter": "seed",
                            "value": "0"
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Negative length should cause an IndexOutOfBoundsException."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{1,2,3}"
                        },
                        {
                            "parameter": "offset",
                            "value": "2"
                        },
                        {
                            "parameter": "length",
                            "value": "2"
                        },
                        {
                            "parameter": "seed",
                            "value": "0"
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "offset + length exceeding the array size must throw IndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "Null data handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "null"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "0"
                        },
                        {
                            "parameter": "seed",
                            "value": "0"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null byte array must result in a NullPointerException."
                }
            ]
        },
        {
            "group": "Large positive seed masking",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{0,1,2,3,4,5,6,7}"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "8"
                        },
                        {
                            "parameter": "seed",
                            "value": "4294967296"  , 
                            "note": "2^32, one more than max unsigned 32‑bit"
                        }
                    ],
                    "expected": "same as case with seed 0",
                    "description": "A seed equal to 2^32 should be masked to 0 (seed & 0xffffffffL) and therefore produce the same hash as seed 0."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{0,1,2,3,4,5,6,7}"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "8"
                        },
                        {
                            "parameter": "seed",
                            "value": "4294967300"  , 
                            "note": "2^32 + 4"
                        }
                    ],
                    "expected": "same as case with seed 4",
                    "description": "Seed values larger than 2^32 must be reduced modulo 2^32; 4294967300 masks to 4."
                }
            ]
        },
        {
            "group": "Idempotent hashing",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "new byte[]{(byte)0xFF, (byte)0xEE, (byte)0xDD, (byte)0xCC}"
                        },
                        {
                            "parameter": "offset",
                            "value": "0"
                        },
                        {
                            "parameter": "length",
                            "value": "4"
                        },
                        {
                            "parameter": "seed",
                            "value": "123456789"
                        }
                    ],
                    "expected": "identical results on repeated calls with same arguments",
                    "description": "Calling hash128x64 repeatedly with identical parameters must return the same long[2] each time (no internal state leakage)."
                }
            ]
        }
    ]
}