{
    "testCases": [
        {
            "group": "Null and Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "When the input byte array is null the method should return null without throwing an exception."
                },
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": []
                        }
                    ],
                    "expected": [],
                    "description": "An empty byte array represents no data; the decoder should return an empty byte array."
                }
            ]
        },
        {
            "group": "Valid Base64 Decoding (no padding, no whitespace)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "SGVsbG8gV29ybGQh"
                        }
                    ],
                    "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
                    "description": "Standard Base64 string \"Hello World!\" without padding or line breaks should decode to its original UTF‑8 bytes."
                },
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "AQID"
                        }
                    ],
                    "expected": [1, 2, 3],
                    "description": "Simple numeric bytes 0x01,0x02,0x03 encoded as Base64 should decode correctly."
                }
            ]
        },
        {
            "group": "Padding Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "Zm9vYg=="   }
                    ],
                    "expected": [102, 111, 111, 98],
                    "description": "Base64 string with two padding characters (\"==\") should decode to the original 4‑byte sequence."
                },
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "Zm9vYw="
                        }
                    ],
                    "expected": [102, 111, 111, 99],
                    "description": "Base64 string with a single padding character (=) should decode correctly."
                }
            ]
        },
        {
            "group": "Whitespace and Line Separator Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": " SG V s b G 8 g V 2 9 y b G Q h "
                        }
                    ],
                    "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
                    "description": "Base64 decoder must ignore whitespace characters (spaces, tabs, newlines). The decoded result should be identical to the compact form."
                },
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "SGVsbG8g\r\nV29ybGQh"
                        }
                    ],
                    "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
                    "description": "CR/LF line separators should be ignored by the decoder."
                }
            ]
        },
        {
            "group": "Invalid Characters Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "SGVsbG8gV@29ybGQh"
                        }
                    ],
                    "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
                    "description": "Characters not part of the Base64 alphabet (e.g., '@') are ignored; decoding should still succeed for the valid portion."
                },
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "!!!"
                        }
                    ],
                    "expected": [],
                    "description": "A string containing only illegal characters should result in an empty byte array (no valid data to decode)."
                }
            ]
        },
        {
            "group": "Large Input / Chunked Output",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64Data",
                            "value": "VGhpcyBpcyBhIHZlcnkgbG9uZyBzdHJpbmcgdGhhdCBpcyB0byBiZSBkZWNvZGVkIGluIG11bHRpcGxlIGJsb2NrcyB0byB0ZXN0IHRoZSBjaGFyYWN0ZXJpc2l0eSBvZiB0aGUgZGVjb2RlIG1ldGhvZC4="
                        }
                    ],
                    "expected": [
                        84, 104, 105, 115, 32, 105, 115, 32, 97, 32, 118, 101, 114, 121, 32, 108,
                        111, 110, 103, 32, 115, 116, 114, 105, 110, 103, 32, 116, 104, 97, 116,
                        32, 105, 115, 32, 116, 111, 32, 98, 101, 32, 100, 101, 99, 111, 100,
                        101, 100, 32, 105, 110, 32, 109, 117, 108, 116, 105, 112, 108, 101,
                        32, 98, 108, 111, 99, 107, 115, 32, 116, 111, 32, 116, 101, 115, 116,
                        32, 116, 104, 101, 32, 99, 104, 97, 114, 97, 99, 116, 101, 114, 105,
                        115, 116, 105, 99, 32, 111, 102, 32, 116, 104, 101, 32, 100, 101, 99,
                        111, 100, 101, 32, 109, 101, 116, 104, 111, 100, 46
                    ],
                    "description": "Decoding a long Base64 string (multiple 76‑character lines when encoded) should correctly produce the original byte sequence without being affected by internal chunking logic."
                }
            ]
        }
    ]
}