{
    "group": "hash128x64(byte[]) additional edge‑case tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x00}"
                }
            ],
            "expected": "Returns a long[2] array equal to MurmurHash3.hash128x64(data, 0, 1, 0).",
            "description": "Single‑byte input – checks that the one‑argument overload correctly forwards a length of 1 and produces the same 128‑bit hash as the four‑argument version."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[16]"  ,
                    "comment": "all bytes are 0"
                }
            ],
            "expected": "Returns a long[2] array equal to MurmurHash3.hash128x64(data, 0, 16, 0).",
            "description": "Exactly one 16‑byte block – verifies correct handling of a full block boundary."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[15]" ,
                    "comment": "all bytes are 0"
                }
            ],
            "expected": "Returns a long[2] array equal to MurmurHash3.hash128x64(data, 0, 15, 0).",
            "description": "Fifteen bytes (one byte short of a full block) – ensures the tail‑processing path yields the same result as the four‑argument overload."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[17]" ,
                    "comment": "all bytes are 0"
                }
            ],
            "expected": "Returns a long[2] array equal to MurmurHash3.hash128x64(data, 0, 17, 0).",
            "description": "Seventeen bytes (one byte over a full block) – tests the algorithm’s handling of a second, incomplete block."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{(byte)0xFF, (byte)0x80, (byte)0x7F, (byte)0x00}"
                }
            ],
            "expected": "Returns a long[2] array equal to MurmurHash3.hash128x64(data, 0, 4, 0).",
            "description": "Array containing negative‑valued bytes (signed byte values) – confirms that the hash treats byte values as unsigned 8‑bit data."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[]{0x01, 0x02, 0x03, 0x04}"
                }
            ],
            "expected": "Two successive calls return different long[2] object references (but equal contents).",
            "description": "Object‑identity test – ensures the method creates a fresh result array each time rather than reusing a static buffer."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "byte[] mutable = new byte[]{0x11, 0x22, 0x33, 0x44}; mutable"
                }
            ],
            "expected": "The hash value returned before mutating the array remains equal to MurmurHash3.hash128x64(original, 0, original.length, 0) after the array is changed.",
            "description": "Immutability test – verifies that the computed hash is not affected by later modifications to the original byte array."
        }
    ]
}