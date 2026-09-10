{
    "testCases": [
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with newRecord true and empty charSeq",
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
                    "description": "Test when QuoteMode is MINIMAL with newRecord true and an empty charSeq; should quote the empty token."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with newRecord false and special characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\tvalue"
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
                    "expected": "\"\\tvalue\"",
                    "description": "Test when QuoteMode is MINIMAL with a tab character at the start and newRecord false; should quote the value."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.ALL_NON_NULL with non-null object",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "new Object()"
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
                            "value": "true"
                        }
                    ],
                    "expected": "\"value\"",
                    "description": "Test when QuoteMode is ALL_NON_NULL with a non-null object; should always quote the value."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with trailing spaces",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value   "
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
                    "expected": "\"value   \"",
                    "description": "Test when QuoteMode is MINIMAL with trailing spaces; should quote the value."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL with multiple delimiters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value,value2,value3"
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
                    "expected": "value,value2,value3",
                    "description": "Test when QuoteMode is MINIMAL with multiple delimiters; should write out the original value without quotes."
                }
            ]
        }
    ]
}