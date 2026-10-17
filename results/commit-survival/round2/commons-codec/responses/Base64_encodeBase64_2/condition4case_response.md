{
    "testCases": [
        {
            "group": "Null and Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": null
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "When the input byte array is null the method should return null regardless of the chunking flag."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": []
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": [],
                    "description": "An empty input array should produce an empty output array (no padding, no line‑breaks)."
                }
            ]
        },
        {
            "group": "Basic Encoding (no chunking)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 110, 121]                      

                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "YW55",
                    "description": "Encode a short ASCII string (\"any\") without chunking – verifies normal Base64 conversion and that no line separator is added."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 0, 0]                    
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "AAAA",
                    "description": "Encode three zero bytes – a multiple of 3, so no padding characters are required."
                }
            ]
        },
        {
            "group": "Padding Cases (no chunking)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0]                    
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "AA==",
                    "description": "Single byte input must be padded with two '=' characters."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 1]                    
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "AAE=",
                    "description": "Two‑byte input must be padded with one '=' character."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2, 3, 4]                    
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        }
                    ],
                    "expected": "AQIDBA==",
                    "description": "Four bytes produce one full Base64 quartet (AQID) and a second quartet with padding (BA==)."
                }
            ]
        },
        {
            "group": "Chunked Encoding (line breaks every 76 characters)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [
                                84, 104, 105, 115, 32, 105, 115, 32, 97, 32, 108, 111, 110, 103, 101, 114,
                                32, 115, 116, 114, 105, 110, 103, 32, 116, 111, 32, 116, 101, 115, 116, 32,
                                116, 104, 101, 32, 99, 104, 117, 110, 107, 105, 110, 103, 32, 108, 111, 103,
                                105, 99, 32, 102, 101, 97, 116, 117, 114, 101, 115, 46, 32, 73, 116, 32,
                                99, 111, 110, 116, 97, 105, 110, 115, 32, 109, 111, 114, 101, 32, 116, 104,
                                97, 110, 76, 69, 78, 71, 84, 72, 32, 99, 104, 97, 114, 115, 46
                            ]
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        }
                    ],
                    "expected": "VGhpcyBpcyBhIGxvbmdlciBzdHJpbmcgdG8gdGVzdCB0aGUgY2h1bmtpbmcgbG9naWMgZmVhdHVyZXMuIEl0IGNvbnRhaW5zIG1vcmUgdGhhbkxFTkdUSENoYXJzLg==\r\n",
                    "description": "Encode a >76‑character payload with chunking enabled. The result must contain a CRLF (`\\r\\n`) after the 76‑character Base64 line. The test verifies that the internal line‑separator logic is exercised."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2, 3]                    
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        }
                    ],
                    "expected": "AQID",
                    "description": "Chunking flag true but the encoded output is shorter than 76 characters – no line break should be added."
                }
            ]
        }
    ]
}