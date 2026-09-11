{
    "testCases": [
        {
            "group": "printWithQuotes - QuoteMode.ALL",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\"Sample String\""
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "\"Sample String\"",
                    "description": "Test when QuoteMode is ALL, should always quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.ALL_NON_NULL",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\"Sample String\""
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "\"Sample String\"",
                    "description": "Test when QuoteMode is ALL_NON_NULL, should always quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.NON_NUMERIC",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\"Sample String\""
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "\"Sample String\"",
                    "description": "Test when QuoteMode is NON_NUMERIC with a non-numeric object, should quote the output."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": 123
                        },
                        {
                            "parameter": "charSeq",
                            "value": "123"
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "123",
                    "description": "Test when QuoteMode is NON_NUMERIC with a numeric object, should not quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.NONE",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Sample String"
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "Sample String",
                    "description": "Test when QuoteMode is NONE, should write the original value without quotes."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": ""
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "\"\"",
                    "description": "Test when QuoteMode is MINIMAL with an empty charSeq, should quote the empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Sample String"
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": false
                        }
                    ],
                    "expected": "Sample String",
                    "description": "Test when QuoteMode is MINIMAL with no special characters, should write the original value without quotes."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\nNew Line"
                        },
                        {
                            "parameter": "out",
                            "value": "StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": false
                        }
                    ],
                    "expected": "\"\\nNew Line\"",
                    "description": "Test when QuoteMode is MINIMAL with new line character, should quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - Exception Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Sample String"
                        },
                        {
                            "parameter": "out",
                            "value": "null"
                        },
                        {
                            "parameter": "newRecord",
                            "value": true
                        }
                    ],
                    "expected": "IOException",
                    "description": "Test when the Appendable out is null, should throw IOException."
                }
            ]
        }
    ]
}