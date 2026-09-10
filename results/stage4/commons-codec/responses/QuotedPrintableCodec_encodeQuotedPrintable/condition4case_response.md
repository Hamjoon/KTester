{
    "testCases": [
        {
            "group": "encodeQuotedPrintable - Null Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": null
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": null,
                    "description": "Test case when both printable and bytes are null; should return null."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Empty Byte Array",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": []
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": null,
                    "description": "Test case with an empty byte array and strict mode; should return null."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Short Byte Array in Strict Mode",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [1, 2]
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": null,
                    "description": "Test case with a byte array shorter than MIN_BYTES in strict mode; should return null."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Valid Encoding in Strict Mode",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [65, 66, 67, 68, 69, 70]
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": [65, 66, 67, 68, 69, 70],
                    "description": "Test case with a valid byte array in strict mode; should return the same byte array as all are printable."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Encoding Non-Printable Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [9, 10, 11, 12, 13, 14]  
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": [61, 9, 10, 61, 12, 61, 13, 14],  
                    "description": "Test case with non-printable characters; should return encoded bytes."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Soft Line Breaks",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80]
                        },
                        {
                            "parameter": "strict",
                            "value": true
                        }
                    ],
                    "expected": [65, 66, 67, 68, 69, 70, 61, 13, 10, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80],
                    "description": "Test case to check soft line breaks after exceeding SAFE_LENGTH; should return encoded bytes with line breaks."
                }
            ]
        },
        {
            "group": "encodeQuotedPrintable - Non-Strict Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "printable",
                            "value": null
                        },
                        {
                            "parameter": "bytes",
                            "value": [65, 66, 67, 255]
                        },
                        {
                            "parameter": "strict",
                            "value": false
                        }
                    ],
                    "expected": [65, 66, 67, 61, 255],
                    "description": "Test case with non-printable byte in non-strict mode; should return bytes with the non-printable byte encoded."
                }
            ]
        }
    ]
}