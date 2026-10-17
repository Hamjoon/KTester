{
    "testCases": [
        {
            "group": "Null argument handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "null"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "1"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null input array should cause a NullPointerException when the method attempts to read input[inPos]."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{'A'}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "1"
                        },
                        {
                            "parameter": "context",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null Context should cause a NullPointerException when the method accesses context.eof."
                }
            ]
        },
        {
            "group": "validateCharacter failure for modulus 2 (low‑2‑bits not zero)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "0"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=true, modulus=2, lbitWorkArea=0x3L, pos=0}"
                        }
                    ],
                    "expected": "IllegalArgumentException thrown by validateCharacter (low‑2‑bits are not zero).",
                    "description": "When the trailing block has modulus = 2 the decoder must verify that the lowest 2 bits are zero; a non‑zero value should trigger an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "validateCharacter failure for modulus 7 (low‑3‑bits not zero)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "0"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=true, modulus=7, lbitWorkArea=0x7L, pos=0}"
                        }
                    ],
                    "expected": "IllegalArgumentException thrown by validateCharacter (low‑3‑bits are not zero).",
                    "description": "When the trailing block has modulus = 7 the decoder must verify that the lowest 3 bits are zero; a non‑zero value should trigger an IllegalArgumentException."
                }
            ]
        }
    ]
}