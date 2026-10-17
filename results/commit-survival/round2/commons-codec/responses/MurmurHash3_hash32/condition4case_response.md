{
    "group": "MurmurHash3.hash32(long) - delegation and edge values",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "0L"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(0L, MurmurHash3.DEFAULT_SEED)",
            "description": "Verify that hash32(long) correctly delegates to the overloaded method with the default seed for the zero value."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "Long.MAX_VALUE"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(Long.MAX_VALUE, MurmurHash3.DEFAULT_SEED)",
            "description": "Check delegation for the maximum positive long value; ensures no overflow or sign‑related bugs."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "Long.MIN_VALUE"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(Long.MIN_VALUE, MurmurHash3.DEFAULT_SEED)",
            "description": "Check delegation for the most negative long value; verifies handling of two‑s complement edge case."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "1234567890123456789L"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(1234567890123456789L, MurmurHash3.DEFAULT_SEED)",
            "description": "Typical positive long; confirms deterministic result and proper seed usage."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "-987654321098765432L"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(-987654321098765432L, MurmurHash3.DEFAULT_SEED)",
            "description": "Typical negative long; ensures sign bits are incorporated correctly."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "0xFFFFFFFFFFFFFFFFL"
                }
            ],
            "expected": "int value equal to MurmurHash3.hash32(0xFFFFFFFFFFFFFFFFL, MurmurHash3.DEFAULT_SEED)",
            "description": "All‑bits‑set long (equivalent to -1L); tests that the method works for a value where every bit is 1."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "0L"
                }
            ],
            "expected": "same int value as the first call with 0L (deterministic)",
            "description": "Call the method twice with the same input to verify that the hash is deterministic (no random state)."
        }
    ]
}