{
    "test_cases": [
        {
            "group": "Null input array",
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
                            "value": "new Context(eof=false, modulus=0, lbitWorkArea=0, pos=0, currentLinePos=0)"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null input array should cause a NullPointerException when the method attempts to read input[inPos]."
                }
            ]
        },
        {
            "group": "Null context",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x61}"
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
                    "description": "Providing a null Context should result in a NullPointerException when the method accesses context fields."
                }
            ]
        },
        {
            "group": "Invalid modulus triggers IllegalStateException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x61}"
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
                            "value": "new Context(eof=false, modulus=5, lbitWorkArea=0, pos=0, currentLinePos=0)"
                        }
                    ],
                    "expected": "IllegalStateException with message containing \"Impossible modulus\"",
                    "description": "When EOF is signaled (inAvail < 0) and context.modulus holds an illegal value (5), the switch‑default branch throws IllegalStateException."
                }
            ]
        },
        {
            "group": "Negative modulus triggers IllegalStateException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x61}"
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
                            "value": "new Context(eof=false, modulus=-2, lbitWorkArea=0, pos=0, currentLinePos=0)"
                        }
                    ],
                    "expected": "IllegalStateException with message containing \"Impossible modulus\"",
                    "description": "A negative modulus value also falls through to the default case, causing IllegalStateException during EOF handling."
                }
            ]
        },
        {
            "group": "Input array index out of bounds",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x01, 0x02}"
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
                            "value": "new Context(eof=false, modulus=0, lbitWorkArea=0, pos=0, currentLinePos=0)"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "If inPos + inAvail exceeds the length of the input array, the loop will attempt to read past the array bounds, throwing ArrayIndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "Null line separator causes NullPointerException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x68,0x65,0x6c,0x6c,0x6f}"
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
                            "value": "new Context(eof=false, modulus=0, lbitWorkArea=0, pos=0, currentLinePos=8, lineLength=8, lineSeparator=null)"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "When lineLength > 0 and the encoder needs to insert a line separator, a null lineSeparator array leads to a NullPointerException during System.arraycopy."
                }
            ]
        }
    ]
}