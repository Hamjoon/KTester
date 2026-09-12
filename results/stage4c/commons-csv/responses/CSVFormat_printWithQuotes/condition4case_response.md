{
    "group": "printWithQuotes method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "123"
                },
                {
                    "parameter": "charSeq",
                    "value": "123"
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "123",
            "description": "Test with a numeric object and minimal quote mode, expecting no quotes around the number."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "\"Hello, World!\""
                },
                {
                    "parameter": "charSeq",
                    "value": "\"Hello, World!\""
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "\"\"\"Hello, World!\"\"\"",
            "description": "Test with a non-numeric string containing a quote, expecting quotes and escaping of internal quotes."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": null
                },
                {
                    "parameter": "charSeq",
                    "value": ""
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "\"\"",
            "description": "Test with an empty string and new record, expecting quotes due to minimal quoting policy."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc,def"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc,def"
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "\"abc,def\"",
            "description": "Test with a string containing a delimiter, expecting quotes around the string."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc"
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "abc",
            "description": "Test with a simple string without special characters, expecting no quotes."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc\ndef"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc\ndef"
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "\"abc\ndef\"",
            "description": "Test with a string containing a newline character, expecting quotes around the string."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc\ndef"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc\ndef"
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "\"abc\ndef\"",
            "description": "Test with a string containing a newline character, expecting quotes around the string."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "  "
                },
                {
                    "parameter": "charSeq",
                    "value": "  "
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": false
                }
            ],
            "expected": "\"  \"",
            "description": "Test with a string containing only spaces, expecting quotes due to minimal quoting policy."
        }
    ]
}