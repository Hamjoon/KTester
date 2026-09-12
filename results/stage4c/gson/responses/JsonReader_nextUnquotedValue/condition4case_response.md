{
    "group": "nextUnquotedValue Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 3
                }
            ],
            "expected": "abc",
            "description": "Test with a simple unquoted literal without special characters."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc/"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 4
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a '/' which should trigger checkLenient and stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc;"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 4
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a ';' which should trigger checkLenient and stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc{"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 4
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing '{' which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc "
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 4
                }
            ],
            "expected": "abc",
            "description": "Test with a string containing a space which should stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abcdef"
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
            "expected": "abcdef",
            "description": "Test with a string that fits entirely in the buffer without any special characters."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abcdefghij"
                },
                {
                    "parameter": "pos",
                    "value": 0
                },
                {
                    "parameter": "limit",
                    "value": 10
                }
            ],
            "expected": "abcdefghij",
            "description": "Test with a string that exceeds the initial buffer size and requires building with StringBuilder."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc\\def"
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
            "description": "Test with a string containing a '\\' which should trigger checkLenient and stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc#def"
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
            "description": "Test with a string containing a '#' which should trigger checkLenient and stop reading."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "abc=def"
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
            "description": "Test with a string containing a '=' which should trigger checkLenient and stop reading."
        }
    ]
}