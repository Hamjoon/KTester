{
    "testCases": [
        {
            "group": "nextUnquotedValue - Valid Input with Whitespace",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "   validString   "
                        }
                    ],
                    "expected": "validString",
                    "description": "Test case where the buffer contains a valid unquoted string value surrounded by whitespace."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "special@Value$"
                        }
                    ],
                    "expected": "special@Value$",
                    "description": "Test case where the buffer contains a valid unquoted string with special characters."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Edge Case with Newline",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "newlineValue\n"
                        }
                    ],
                    "expected": "newlineValue",
                    "description": "Test case where the buffer contains a valid unquoted string followed by a newline character."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Buffer with Escape Sequences",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "escaped\\Value"
                        }
                    ],
                    "expected": "escaped\\Value",
                    "description": "Test case where the buffer contains a valid unquoted string with an escape sequence."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Multiple Non-Literal Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "value1, value2; value3#"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test case where the buffer contains multiple non-literal characters and checks if the first unquoted value is returned."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Single Character Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "a"
                        }
                    ],
                    "expected": "a",
                    "description": "Test case where the buffer contains a single character, expecting it to be returned as the unquoted value."
                }
            ]
        },
        {
            "group": "nextUnquotedValue - Long Input with Non-Literal Character",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "longInputStringThatHasNonLiteralCharacter#"
                        }
                    ],
                    "expected": "longInputStringThatHasNonLiteralCharacter",
                    "description": "Test case where the buffer contains a long input string followed by a non-literal character, checking if the whole valid string is returned."
                }
            ]
        }
    ]
}