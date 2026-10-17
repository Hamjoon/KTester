{
    "test_cases": [
        {
            "group": "testEncode_NullInputArray",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "null"
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
                            "value": "new Base64().new Context()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "When the input byte array is null, the method attempts to read from it and throws NullPointerException."
                }
            ]
        },
        {
            "group": "testEncode_InvalidInPos",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "new byte[]{1,2,3}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "5"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "1"
                        },
                        {
                            "parameter": "context",
                            "value": "new Base64().new Context()"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Providing an inPos that is beyond the length of the input array causes an out‑of‑bounds access when the method reads in[inPos++]."
                }
            ]
        },
        {
            "group": "testEncode_NullContext",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "new byte[]{1,2,3}"
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
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "If the Context argument is null, any access to its fields (e.g., context.eof) throws NullPointerException."
                }
            ]
        },
        {
            "group": "testEncode_InvalidModulusAtEOF",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "new byte[]{1,2,3}"
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
                            "value": "corruptedContext"
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "When EOF is signaled (inAvail < 0) the method switches on context.modulus. If the modulus has an illegal value (e.g., 3) the default branch throws IllegalStateException."
                }
            ]
        },
        {
            "group": "testEncode_NullEncodeTable",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "new byte[]{1,2,3}"
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
                            "value": "new Base64().new Context()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "If the internal encodeTable is set to null (via reflection) any attempt to index it (encodeTable[...]) results in NullPointerException."
                }
            ]
        },
        {
            "group": "testEncode_NullLineSeparatorWhenChunked",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "in",
                            "value": "new byte[]{1,2,3}"
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
                            "value": "chunkedContext"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "When lineLength > 0 (chunked mode) and EOF processing reaches the block that appends the line separator, a null lineSeparator causes System.arraycopy to throw NullPointerException."
                }
            ]
        }
    ]
}