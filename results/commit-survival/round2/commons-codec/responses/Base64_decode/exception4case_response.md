{
    "tests": [
        {
            "group": "Null input array – should throw NullPointerException",
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
                            "value": "new Base64().new Context()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Calling decode with a null byte[] should immediately raise NPE when the method attempts to read input[inPos]"
                }
            ]
        },
        {
            "group": "Null context – should throw NullPointerException",
            "cases": [
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
                    "description": "The method accesses fields of the Context object; passing null must result in NPE"
                }
            ]
        },
        {
            "group": "inPos out of bounds – should throw ArrayIndexOutOfBoundsException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{'A','B','C','D'}"
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
                    "description": "When inPos points beyond the end of the input array the first read `input[inPos++]` triggers an AIOOBE"
                }
            ]
        },
        {
            "group": "validateCharacter throws for non‑zero low 4 bits (modulus == 2)",
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
                            "value": "Context with eof=true, modulus=2, ibitWorkArea=0b00001111 (low 4 bits = 0b1111)"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "After the main loop, eof is true and modulus == 2, so `validateCharacter(MASK_4BITS, context)` is invoked. The low‑4‑bit mask is not zero, causing the validator to throw IllegalArgumentException"
                }
            ]
        },
        {
            "group": "validateCharacter throws for non‑zero low 2 bits (modulus == 3)",
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
                            "value": "Context with eof=true, modulus=3, ibitWorkArea=0b00000101 (low 2 bits = 0b01)"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When modulus == 3 the method calls `validateCharacter(MASK_2BITS, context)`. Because the lowest two bits are not zero, the validator throws IllegalArgumentException"
                }
            ]
        }
    ]
}