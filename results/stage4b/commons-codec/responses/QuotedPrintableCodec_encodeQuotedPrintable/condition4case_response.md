{
    "testCases": [
        {
            "group": "encodeQuotedPrintable with null bytes",
            "cases": [
                {
                    "input": [],
                    "expected": null,
                    "description": "Test case where the input byte array is null. The expected output should also be null."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with null printable BitSet",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": "Hello World"
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the printable BitSet is null. The method should use the default PRINTABLE_CHARS."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with strict mode and insufficient bytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "Hi"
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": null,
                    "description": "Test case where the input byte array has less than MIN_BYTES (3). Expected output should be null."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with strict mode and sufficient bytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "Hello"
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the input byte array has sufficient bytes (>= MIN_BYTES) in strict mode. The method should return the encoded byte array."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with non-strict mode",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "Hello"
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the input byte array is processed in non-strict mode. The method should return the encoded byte array."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with whitespace handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "Hello World  "
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "encoded byte array with whitespace encoded",
                    "description": "Test case where the input contains trailing whitespace. The method should encode the whitespace according to rules."
                }
            ]
        }
    ]
}