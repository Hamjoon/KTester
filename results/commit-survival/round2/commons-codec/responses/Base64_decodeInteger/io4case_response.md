{
    "tests": [
        {
            "group": "Empty and Null inputs",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": []
                        }
                    ],
                    "expected": "0",
                    "description": "An empty byte array should decode to an empty byte[] which the BigInteger constructor interprets as zero."
                },
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": null
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Passing a null reference must result in a NullPointerException because decodeBase64 dereferences the argument."
                }
            ]
        },
        {
            "group": "Invalid / non‑Base64 characters handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [33, 65, 81, 73, 68, 64]   }
                    ],
                    "expected": "66051",
                    "description": "Characters '!' (33) and '@' (64) are not part of the Base64 alphabet and should be ignored, yielding the same result as decoding \"AQID\"."
                },
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [65, 81, 73, 68, 10, 9]   }
                    ],
                    "expected": "66051",
                    "description": "Tab (9) and newline (10) are whitespace; they must be ignored, producing the same integer as the plain string."
                }
            ]
        },
        {
            "group": "Whitespace and line‑separator variations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [65, 32, 81, 73, 68]   }
                    ],
                    "expected": "66051",
                    "description": "A space inserted between Base64 characters should be ignored."
                },
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [65, 81, 13, 10, 73, 68, 13, 10]   }
                    ],
                    "expected": "66051",
                    "description": "Multiple CRLF sequences interleaved with the data must be ignored."
                }
            ]
        },
        {
            "group": "Leading zero bytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [65, 65, 69, 61]   }
                    ],
                    "expected": "1",
                    "description": "Base64 \"AAE=\" represents the byte array {0x00,0x01}. The leading zero should not affect the numeric value; the result is 1."
                }
            ]
        },
        {
            "group": "Large integer values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [47, 47, 47, 47, 47, 119, 61, 61]   }
                    ],
                    "expected": "4294967295",
                    "description": "Base64 \"/////w==\" decodes to four bytes {0xFF,0xFF,0xFF,0xFF}. The resulting unsigned BigInteger equals 2^32‑1."
                },
                {
                    "input": [
                        {
                            "parameter": "pArray",
                            "value": [77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122, 77, 84, 73, 122]   }
                    ],
                    "expected": "340282366920938463463374607431768211455",
                    "description": "Base64 \"MTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIzMTIz\" decodes to 16 bytes all set to 0xFF, representing the maximum unsigned 128‑bit integer (2^128‑1)."
                }
            ]
        }
    ]
}