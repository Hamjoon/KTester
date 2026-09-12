{
    "group": "printWithQuotes additional method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "null"
                },
                {
                    "parameter": "charSeq",
                    "value": "null"
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
            "description": "Test with null object and charSeq, expecting quotes due to minimal quoting policy."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "123.456"
                },
                {
                    "parameter": "charSeq",
                    "value": "123.456"
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
            "expected": "123.456",
            "description": "Test with a floating-point number, expecting no quotes around the number."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc#def"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc#def"
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
            "expected": "\"abc#def\"",
            "description": "Test with a string starting with a comment character, expecting quotes around the string."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc\"def"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc\"def"
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
            "expected": "\"abc\"\"def\"",
            "description": "Test with a string containing a quote character, expecting quotes and escaping of the internal quote."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "abc\\def"
                },
                {
                    "parameter": "charSeq",
                    "value": "abc\\def"
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
            "expected": "\"abc\\def\"",
            "description": "Test with a string containing an escape character, expecting quotes around the string."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": ""
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
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with an empty string and not a new record, expecting no quotes since it is not the first item on a new line."
        }
    ]
}