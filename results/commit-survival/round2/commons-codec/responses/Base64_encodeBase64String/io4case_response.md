{
    "testCases": [
        {
            "group": "Non‑ASCII Bytes (negative signed values)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [255, 128, 127]
                        }
                    ],
                    "expected": "/4B/",
                    "description": "Encode a three‑byte array containing values > 127 (Java signed bytes -1, -128, 127). The length is a multiple of 3, so no padding is added. Verifies that the method correctly handles signed byte values."
                }
            ]
        },
        {
            "group": "All‑Zero Triple Repetition (no padding, multiple quanta)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        }
                    ],
                    "expected": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    "description": "30 zero bytes (10 full Base64 quanta). Each triple of zeros encodes to \"AAAA\"; the final string should be 40 'A' characters with no padding."
                }
            ]
        },
        {
            "group": "Very Large Input (10 000 bytes, pattern 0x01‑0x02‑0x03)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "generated: repeat [1,2,3] 3333 times then append [1]"
                        }
                    ],
                    "expected": "length 13336, ends with \"AQ==\"",
                    "description": "Creates a 10 000‑byte array by repeating the pattern 0x01,0x02,0x03. The expected Base64 string length is 13 336 characters (⌈10000/3⌉ × 4) and the final four characters must be \"AQ==\" (encoding of the single leftover byte 0x01). This tests internal buffer growth and correct handling of a large, non‑chunked payload."
                }
            ]
        },
        {
            "group": "Full Byte Range (0‑255)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "generated: bytes 0 through 255 inclusive"
                        }
                    ],
                    "expected": "length 344, starts with \"AAEC\", ends with \"//8=\"",
                    "description": "Encodes a 256‑byte array containing every possible byte value. The result should be 344 characters long (86 quanta × 4) with two padding characters. The prefix \"AAEC\" corresponds to the first three bytes (0,1,2) and the suffix \"//8=\" corresponds to the last three bytes (253,254,255)."
                }
            ]
        },
        {
            "group": "Alphabet Validation (random data)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "generated: random 57‑byte array"
                        }
                    ],
                    "expected": "matches regex ^[A-Za-z0-9+/]*={0,2}$",
                    "description": "Ensures that for any input the returned string contains only characters from the Base64 alphabet (A‑Z, a‑z, 0‑9, '+', '/') and at most two trailing '=' padding characters. Uses a 57‑byte random payload to exercise multiple encoding blocks."
                }
            ]
        }
    ]
}