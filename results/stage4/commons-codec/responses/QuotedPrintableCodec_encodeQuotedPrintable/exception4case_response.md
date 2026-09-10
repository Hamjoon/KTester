{
    "output": [
        {
            "group": "encodeQuotedPrintable - Handling Unsupported Charset Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [65, 66, 67]
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": "UnsupportedEncodingException",
                    "description": "Test case when an unsupported charset is specified; should throw UnsupportedEncodingException."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Handling Illegal Charset Name Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [65, 66, 67]
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": "IllegalCharsetNameException",
                    "description": "Test case when an illegal charset name is specified; should throw IllegalCharsetNameException."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Handling Decoder Exception",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [255]
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": "DecoderException",
                    "description": "Test case when decoding results in an invalid byte; should throw DecoderException."
                }
            ]
        }
    ]
}