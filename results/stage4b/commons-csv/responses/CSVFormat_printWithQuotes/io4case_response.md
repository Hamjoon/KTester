{
    "testCases": [
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "\tTab Character"
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
                    "expected": "\"\\tTab Character\"",
                    "description": "Test when QuoteMode is MINIMAL with a tab character, should quote the output."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "   Leading Spaces"
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
                    "expected": "\"   Leading Spaces\"",
                    "description": "Test when QuoteMode is MINIMAL with leading spaces, should quote the output."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Trailing Spaces   "
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
                    "expected": "\"Trailing Spaces   \"",
                    "description": "Test when QuoteMode is MINIMAL with trailing spaces, should quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.MINIMAL - Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Special@Char"
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
                    "expected": "\"Special@Char\"",
                    "description": "Test when QuoteMode is MINIMAL with special characters, should quote the output."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "Quote\"Inside\""
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
                    "expected": "\"Quote\\\"Inside\\\"\"",
                    "description": "Test when QuoteMode is MINIMAL with quotes inside the string, should escape quotes and quote the output."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.NONE - Edge Cases",
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
                            "value": false
                        }
                    ],
                    "expected": "",
                    "description": "Test when QuoteMode is NONE with an empty charSeq, should return empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "Sample Object"
                        },
                        {
                            "parameter": "charSeq",
                            "value": " "
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
                    "expected": " ",
                    "description": "Test when QuoteMode is NONE with a space character, should return the original space."
                }
            ]
        }
    ]
}