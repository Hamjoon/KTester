{
    "testCases": [
        {
            "group": "decode method - EOF conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "context.eof = false",
                    "description": "Test when context.eof is initially false and inAvail is positive."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
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
                            "value": "new Context()"
                        }
                    ],
                    "expected": "context.eof = true",
                    "description": "Test when inAvail is negative, should set context.eof to true."
                }
            ]
        },
        {
            "group": "decode method - handling padding and modulus",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 61}" 		} 	,
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "context.eof = true",
                    "description": "Test when '=' padding character is encountered, should set context.eof to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "context.modulus = 0",
                    "description": "Test when input is valid and modulus is calculated correctly."
                }
            ]
        },
        {
            "group": "decode method - modulus cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 2) & MASK_8BITS))",
                    "description": "Test when modulus is 2, should drop 2 bits and output one byte."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 7) & MASK_8BITS))",
                    "description": "Test when modulus is 3, should drop 7 bits and output one byte."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS))",
                    "description": "Test when modulus is 4, should validate character and output two bytes."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS))",
                    "description": "Test when modulus is 5, should validate character and output three bytes."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS))",
                    "description": "Test when modulus is 6, should validate trailing characters."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 24) & MASK_8BITS))",
                    "description": "Test when modulus is 7, should validate character and output four bytes."
                }
            ]
        }
    ]
}