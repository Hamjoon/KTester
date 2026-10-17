{
    "testCases": [
        {
            "group": "Null context handling – expect NullPointerException",
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
                            "value": "2"
                        },
                        {
                            "parameter": "context",
                            "value": "null"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "When the supplied Context object is null the method should throw a NullPointerException on the first access to context.eof."
                }
            ]
        },
        {
            "group": "Null input handling – expect NullPointerException",
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
                            "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[8] }"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "If the input byte array is null the method must throw a NullPointerException when trying to read input[inPos]."
                }
            ]
        },
        {
            "group": "Invalid inPos (negative) – expect ArrayIndexOutOfBoundsException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x01, 0x02, 0x03}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "-1"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "3"
                        },
                        {
                            "parameter": "context",
                            "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[8] }"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "A negative inPos causes the array access input[inPos++] to throw an ArrayIndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "inAvail larger than remaining bytes – expect ArrayIndexOutOfBoundsException",
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
                            "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[16] }"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "When inAvail exceeds the actual number of bytes available in the input array the loop reads past the end, causing an ArrayIndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "Null lineSeparator with lineLength > 0 – expect NullPointerException on EOF handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}"
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
                            "value": "new Base32(){ lineLength=8, lineSeparator=null }.new Context(){ eof=false, lineLength=8, lineSeparator=null, pos=0, buffer=new byte[10] }"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "After encoding a full block the EOF branch attempts to copy the lineSeparator (which is null) via System.arraycopy, resulting in a NullPointerException."
                }
            ]
        },
        {
            "group": "Null encodeTable – expect NullPointerException during normal encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}"
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
                            "value": "new Base32(){ /* encodeTable will be set to null via reflection before invoking encode */ }.new Context(){ eof=false, pos=0, buffer=new byte[8] }"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "If the internal encodeTable field is null, any attempt to read encodeTable[...] throws a NullPointerException."
                }
            ]
        },
        {
            "group": "Illegal modulus – negative value triggers IllegalStateException",
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
                            "value": "Context{eof=false, modulus=-1, lbitWorkArea=0, lineLength=0, pos=0, buffer=new byte[0]}"
                        }
                    ],
                    "expected": "IllegalStateException with message \"Impossible modulus -1\"",
                    "description": "When EOF is signaled and context.modulus is set to a negative value, the default case of the switch throws IllegalStateException."
                }
            ]
        }
    ]
}