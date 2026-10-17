{
    "groups": [
        {
            "group": "MurmurHash3.hash32(long) - distinct inputs produce distinct hashes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0x1111111111111111L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0x1111111111111111L, MurmurHash3.DEFAULT_SEED)",
                    "description": "First distinct long value; ensures a deterministic hash is produced."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0x2222222222222222L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0x2222222222222222L, MurmurHash3.DEFAULT_SEED) and not equal to the hash of 0x1111111111111111L",
                    "description": "Second distinct long value; verifies that two different inputs do not collide (hashes differ)."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "-0x3333333333333333L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(-0x3333333333333333L, MurmurHash3.DEFAULT_SEED) and not equal to the hash of the previous two values",
                    "description": "Third distinct (negative) long value; further confirms lack of accidental collisions."
                }
            ]
        },
        {
            "group": "MurmurHash3.hash32(long) - upper vs. lower 32‑bit influence",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0xFFFFFFFF00000000L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0xFFFFFFFF00000000L, MurmurHash3.DEFAULT_SEED)",
                    "description": "Long where only the high 32 bits are set; checks that the high half participates in the hash."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0x00000000FFFFFFFFL"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0x00000000FFFFFFFFL, MurmurHash3.DEFAULT_SEED) and not equal to the hash of 0xFFFFFFFF00000000L",
                    "description": "Long where only the low 32 bits are set; ensures the hash differs from the high‑bits‑only case."
                }
            ]
        },
        {
            "group": "MurmurHash3.hash32(long) - interleaved overload calls maintain determinism",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0x7FFFFFFFFFFFFFFFL"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0x7FFFFFFFFFFFFFFFL, MurmurHash3.DEFAULT_SEED)",
                    "description": "Call hash32(byte[]) before and after the long hash; the long hash must stay unchanged, proving no hidden mutable static state."
                }
            ]
        },
        {
            "group": "MurmurHash3.hash32(long) - boundary values not previously covered",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "Long.MAX_VALUE - 1L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(Long.MAX_VALUE - 1L, MurmurHash3.DEFAULT_SEED)",
                    "description": "Just below Long.MAX_VALUE; verifies correct handling of values near the upper limit."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "Long.MIN_VALUE + 1L"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(Long.MIN_VALUE + 1L, MurmurHash3.DEFAULT_SEED)",
                    "description": "Just above Long.MIN_VALUE; checks correct handling of values near the lower limit."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "0x123456789ABCDEFL"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(0x123456789ABCDEFL, MurmurHash3.DEFAULT_SEED)",
                    "description": "Arbitrary positive long with a mixed bit pattern; confirms deterministic output for a typical random value."
                },
                {
                    "input": [
                        {
                            "parameter": "data",
                            "value": "-0x123456789ABCDEFL"
                        }
                    ],
                    "expected": "int value equal to MurmurHash3.hash32(-0x123456789ABCDEFL, MurmurHash3.DEFAULT_SEED)",
                    "description": "Arbitrary negative long with a mixed bit pattern; ensures sign extension is handled correctly."
                }
            ]
        }
    ]
}