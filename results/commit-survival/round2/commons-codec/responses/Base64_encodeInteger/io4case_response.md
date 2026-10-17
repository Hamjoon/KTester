Invalid control character at: line 13 column 64 (char 422)
{
    "test_cases": [
        {
            "group": "Base64.encodeInteger(BigInteger) - Zero value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "BigInteger.ZERO"
                        }
                    ],
                    "expected": "byte[] { 'A', 'A', '=', '=' }  // Base64 string \"AA==\"",
                    "description": "Encode the integer 0. The method should convert the single zero byte to Base64 resulting in the bytes for \"AA==\"."
                }
            ]
        },
        {
            "group": "Base64.encodeInteger(BigInteger) - Small positive values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "BigInteger.ONE"
                        }
                    ],
                    "expected": "byte[] { 'A', 'Q', '=', '=' }  // Base64 string \"AQ==\"",
                    "description": "Encode the integer 1. The resulting Base64 representation must be \"AQ==\"."
                },
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"255\")"
                        }
                    ],
                    "expected": "byte[] { 'A', 'P', '8', '=' }  // Base64 string \"AP8=\"",
                    "description": "Encode the integer 255 (0xFF). Because the most‑significant bit is set, toIntegerBytes adds a leading zero byte, so the Base64 output is \"AP8=\"."
                },
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"256\")"
                        }
                    ],
                    "expected": "byte[] { 'A', 'Q', 'A', 'A' }  // Base64 string \"AQAA\"",
                    "description": "Encode the integer 256 (0x0100). No extra sign byte is needed; the Base64 result must be \"AQAA\"."
                }
            ]
        },
        {
            "group": "Base64.encodeInteger(BigInteger) - Large positive value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"123456789012345678901234567890\")"
                        }
                    ],
                    "expected": "byte[] equal to Base64.encodeBase64(toIntegerBytes(bigInteger), false)",
                    "description": "Encode a large 30‑digit integer. The method should produce the same byte array as a direct call to encodeBase64(toIntegerBytes(...))."
                }
            ]
        },
        {
            "group": "Base64.encodeInteger(BigInteger) - Negative value round‑trip",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"-1\")"
                        }
                    ],
                    "expected": "byte[] equal to Base64.encodeBase64(toIntegerBytes(bigInteger), false)",
                    "description": "Encode a negative integer (-1). The resulting Base64 bytes must be the same as encodeBase64(toIntegerBytes(-1), false)."
                },
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"-12345678901234567890\")"
                        }
                    ],
                    "expected": "byte[] equal to Base64.encodeBase64(toIntegerBytes(bigInteger), false)",
                    "description": "Encode a larger negative integer and verify that the output matches the direct encodeBase64 of its integer byte representation."
                }
            ]
        },
        {
            "group": "Base64.encodeInteger(BigInteger) - Round‑trip consistency",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"0\")"
                        }
                    ],
                    "expected": "decoded integer equals original (BigInteger.ZERO)",
                    "description": "Encode then decode the integer 0 using encodeInteger and decodeInteger; the decoded value must equal the original."
                },
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"98765432109876543210\")"
                        }
                    ],
                    "expected": "decoded integer equals original",
                    "description": "Round‑trip test for a typical large positive integer."
                },
                {
                    "input": [
                        {
                            "parameter": "bigInteger",
                            "value": "new BigInteger(\"-98765432109876543210\")"
                        }
                    ],
                    "expected": "decoded integer equals original",
                    "description": "Round‑trip test for a typical large negative integer."
                }
            ]
        }
    ]
}