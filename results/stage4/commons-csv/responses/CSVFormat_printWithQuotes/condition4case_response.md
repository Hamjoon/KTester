{
    "testCases": [
        {
            "group": "printWithQuotes - QuoteMode.ALL",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\"value\""
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "true"
                        }
                    ],
                    "expected": "\"value\"",
                    "description": "Test when QuoteMode is ALL; should always quote the value."
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
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\"value\""
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "value",
                    "description": "Test when QuoteMode is NONE; should write out the original value without quotes."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with empty input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": ""
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "true"
                        }
                    ],
                    "expected": "\"\"",
                    "description": "Test when QuoteMode is MINIMAL with an empty charSeq; should quote the empty token."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with special characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\nvalue"
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "true"
                        }
                    ],
                    "expected": "\"\\nvalue\"",
                    "description": "Test when QuoteMode is MINIMAL with a newline character at the start; should quote the value."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with delimiter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value,value2"
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "value,value2",
                    "description": "Test when QuoteMode is MINIMAL with delimiters present; should write out the original value without quotes."
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
                            "value": "new Object()"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "123"
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "123",
                    "description": "Test when QuoteMode is NON_NUMERIC with a numeric value; should not quote the number."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "new Object()"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "text"
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "\"text\"",
                    "description": "Test when QuoteMode is NON_NUMERIC with a non-numeric value; should quote the text."
                }
            ]
        },
        {
            "group": "printWithQuotes - IllegalStateException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value"
                        },
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "Test when QuoteMode is an unexpected value; should throw IllegalStateException."
                }
            ]
        }
    ]
}