{
    "result": [
        {
            "group": "encodeQuotedPrintable with invalid byte values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "byte array with negative values"
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the input byte array contains negative byte values. The method should handle these values correctly and return the encoded byte array."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with large byte array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "large byte array"
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the input byte array is significantly large. The method should correctly encode the entire array and return the encoded byte array."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable with varying strictness",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": "BitSet with printable characters"
                        },
                        {
                            "parameter": "bytes",
                            "value": "byte array with mixed printable and non-printable characters"
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": "encoded byte array",
                    "description": "Test case where the input byte array contains a mix of printable and non-printable characters in non-strict mode. The method should encode the non-printable characters correctly."
                }
            ]
        }
    ]
}