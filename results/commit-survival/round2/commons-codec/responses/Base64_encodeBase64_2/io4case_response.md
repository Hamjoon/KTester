{
    "groups": [
        {
            "group": "Additional Padding Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2, 3, 4, 5]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "AQIDBAU=",
                    "description": "Encode a five‑byte array (not a multiple of 3) without chunking – verifies correct handling of two‑byte remainder and padding."
                }
            ]
        },
        {
            "group": "Chunked Boundary Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97]
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        }
                    ],
                    "expected": "YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFh",
                    "description": "Encode exactly 57 bytes (19 groups of 3) with chunking enabled – output length is 76 characters, so no CRLF should be inserted."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97]
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        }
                    ],
                    "expected": "YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFh\r\nYQ==",
                    "description": "Encode 58 bytes (57 bytes + 1 extra) with chunking – a CRLF must appear after the first 76 characters, followed by the encoding of the remaining single byte."
                }
            ]
        },
        {
            "group": "Non‑ASCII Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [255, 238, 221]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "/+7d",
                    "description": "Encode a three‑byte array containing values > 127 to ensure signed‑byte handling does not corrupt the result."
                }
            ]
        },
        {
            "group": "Chunking with Single Byte",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [255]
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        }
                    ],
                    "expected": "/w==",
                    "description": "Single‑byte input with chunking flag true – result must be the same as non‑chunked encoding and must not contain a line break."
                }
            ]
        },
        {
            "group": "Immutability",
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
                        }
                    ],
                    "expected": "new array instance",
                    "description": "Verify that the returned byte array is a new object, not the original input array (method should not expose internal mutable state)."
                }
            ]
        }
    ]
}