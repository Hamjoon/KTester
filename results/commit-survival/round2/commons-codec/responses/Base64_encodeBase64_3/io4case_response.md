{
    "test_cases": [
        {
            "group": "Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": []
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "",
                    "description": "Encoding an empty byte array should return an empty byte array (empty string) regardless of chunking or URL‑safe flags."
                }
            ]
        },
        {
            "group": "Single Byte Standard",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [255]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "/w==",
                    "description": "Encode a single byte 0xFF using standard Base64 (no chunking, not URL‑safe). Expected result is \"/w==\"."
                }
            ]
        },
        {
            "group": "Two Bytes Standard",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [255, 238]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "/+4=",
                    "description": "Encode two bytes 0xFF,0xEE with standard Base64. The padding rule produces \" /+4=\"."
                }
            ]
        },
        {
            "group": "Three Bytes Standard",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2, 3]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "AQID",
                    "description": "Encode three bytes that form a complete Base64 quantum; no padding is required."
                }
            ]
        },
        {
            "group": "Non‑Multiple‑of‑3 Length",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 0, 0, 0]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "AAAAAA==",
                    "description": "Four zero bytes produce two full Base64 groups (\"AAA\") and a padded group (\"AA==\")."
                }
            ]
        },
        {
            "group": "Chunked Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "zeroes-76"
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        }
                    ],
                    "expected": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\nAAAAAAAAAA==",
                    "description": "Encode 76 zero bytes with chunking enabled. Standard Base64 of all‑zero data is a long string of 'A's. After 76 characters a CRLF should be inserted, followed by the remaining 28 'A's and the final padding \"AA==\"."
                }
            ]
        },
        {
            "group": "URL Safe Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [251]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": true
                        }
                    ],
                    "expected": "-w==",
                    "description": "Encode a single byte 0xFB using URL‑safe Base64. The '+' character from the standard encoding \"+w==\" is replaced by '-'."
                }
            ]
        },
        {
            "group": "Chunked URL Safe Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "zeroes-76"
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        },
                        {
                            "parameter": "urlSafe",
                            "value": true
                        }
                    ],
                    "expected": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\nAAAAAAAAAA==",
                    "description": "Same data as the Chunked Encoding test but with URL‑safe flag set. Since the data contains only 'A' characters, the output is identical apart from the line break."
                }
            ]
        }
    ]
}