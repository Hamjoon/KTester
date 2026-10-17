{
    "groups": [
        {
            "group": "Invalid character handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{(byte)0x41, (byte)0xFF, (byte)0x42}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "3"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}"
                        }
                    ],
                    "expected": "No bytes are written; context.modulus remains 0 and eof stays false (the 0xFF byte is ignored because decodeTable[0xFF] == -1).",
                    "description": "A byte that is not part of the Base‑32 alphabet must be ignored; it must not affect the modulus or produce output."
                }
            ]
        },
        {
            "group": "Partial block with padding character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{'A','B','C','D','='}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "5"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}"
                        }
                    ],
                    "expected": "Two decoded bytes are emitted (the low‑4‑bit validation passes). Context.pos becomes 2, context.eof is true, and context.modulus is 4 before the trailing‑character switch.",
                    "description": "Four valid Base‑32 symbols followed by the padding character should cause the decoder to treat the four symbols as a trailing block (modulus = 4) and output the corresponding two bytes."
                }
            ]
        },
        {
            "group": "EOF signalled by negative inAvail with pending bits",
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
                            "value": "-1"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=false, pos=0, modulus=5, lbitWorkArea=0x1FFFFFL}"
                        }
                    ],
                    "expected": "Three decoded bytes are emitted: (lbitWorkArea>>16)&0xFF, (lbitWorkArea>>8)&0xFF, lbitWorkArea&0xFF. Context.pos becomes 3, eof becomes true.",
                    "description": "A negative inAvail forces EOF; with modulus = 5 the decoder must execute the case‑5 branch, validate the low‑1‑bit, drop it and write three bytes."
                }
            ]
        },
        {
            "group": "Extra padding characters are ignored after first '='",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{'A','B','C','D','E','F','G','H','=', '=', '=', '='}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "12"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}"
                        }
                    ],
                    "expected": "Five decoded bytes are emitted (the first eight symbols). The loop stops at the first '=', no further bytes are written, and eof becomes true.",
                    "description": "When padding characters appear after a complete block, decoding must stop at the first '=', ignoring any subsequent padding."
                }
            ]
        },
        {
            "group": "Whitespace characters are skipped",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{'A',' ','B','\\n','C','D','E','F','G','H'}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "10"
                        },
                        {
                            "parameter": "context",
                            "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}"
                        }
                    ],
                    "expected": "Five decoded bytes are emitted (identical to decoding \"ABCDEFGH\"). Whitespace does not affect modulus or output.",
                    "description": "Characters that are not in the Base‑32 alphabet (e.g., space, newline) must be ignored by the decoder."
                }
            ]
        },
        {
            "group": "validateCharacter failure for modulus 4 (low‑bits not zero)",
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
                            "value": "Context{eof=true, modulus=4, lbitWorkArea=0xABCDEFL, pos=0}"
                        }
                    ],
                    "expected": "IllegalArgumentException thrown by validateCharacter (low‑4‑bits are not empty).",
                    "description": "When the trailing block has modulus = 4 the decoder must verify that the lowest 4 bits are zero; a non‑zero value must cause an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "validateCharacter failure for modulus 5 (low‑bit not zero)",
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
                            "value": "Context{eof=true, modulus=5, lbitWorkArea=0x1FFFFFL, pos=0}"
                        }
                    ],
                    "expected": "IllegalArgumentException thrown by validateCharacter (the single low bit is not zero).",
                    "description": "For modulus = 5 the decoder must ensure the lowest 1 bit is zero; a non‑zero bit triggers an IllegalArgumentException."
                }
            ]
        }
    ]
}