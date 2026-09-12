{
    "group": "nextUnquotedValue Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc\rdef"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 7
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a carriage return '\\r' which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc\ndef"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 7
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a newline '\\n' which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc\tdef"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 7
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a tab '\\t' which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc\fdef"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 7
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a form feed '\\f' which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "{}[]:,"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 6
                }
            ],
            "expected": "",
            "description": "Test with a string starting with JSON structural characters which should result in an empty string."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 62
                }
            ],
            "expected": "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
            "description": "Test with a string containing only alphanumeric characters which should read the entire buffer."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc def ghi"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 11
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing spaces between words, should return the first word."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc=def ghi"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 11
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing an '=' followed by spaces, should return the first segment before '='."
        }
    ]
}