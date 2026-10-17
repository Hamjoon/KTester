{
    "groups": [
        {
            "group": "Strict Decoding – Invalid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "SGVsbG8gV@29ybGQh"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When the global CodecPolicy is set to STRICT, any character not in the Base64 alphabet (e.g., '@') must cause an IllegalArgumentException instead of being ignored."
                }
            ]
        },
        {
            "group": "Strict Decoding – Invalid Padding Position",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "YWJj=Zg=="
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "With CodecPolicy set to STRICT, a padding character ('=') appearing before the end of the input is considered malformed and must trigger an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Strict Decoding – Incorrect Padding Length",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "Zm9vYg="
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "A Base64 string that ends with a single padding character but whose length is not a multiple of 4 (e.g., \"Zm9vYg=\") is invalid under strict mode and should raise IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Strict Decoding – Non‑Base64 Byte Values (> 127)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "éâß"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When strict decoding is enabled, bytes with values outside the standard Base64 alphabet (e.g., characters with code points > 127) must cause an IllegalArgumentException."
                }
            ]
        }
    ]
}