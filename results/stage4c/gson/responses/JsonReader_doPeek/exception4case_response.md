{
    "group": "JsonReader.doPeek() Method Tests - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "invalidChar"
                }
            ],
            "expected": "syntaxError(\"Unterminated array\")",
            "description": "Test when the stack is NONEMPTY_ARRAY and the next character is invalid, expecting a syntax error for unterminated array."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "invalidChar"
                }
            ],
            "expected": "syntaxError(\"Unterminated object\")",
            "description": "Test when the stack is NONEMPTY_OBJECT and the next character is invalid, expecting a syntax error for unterminated object."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "unexpectedChar"
                }
            ],
            "expected": "syntaxError(\"Expected name\")",
            "description": "Test when the stack is NONEMPTY_OBJECT and the next character is unexpected, expecting a syntax error for expected name."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.DANGLING_NAME]"
                },
                {
                    "parameter": "nextChar",
                    "value": "unexpectedChar"
                }
            ],
            "expected": "syntaxError(\"Expected ':'\")",
            "description": "Test when the stack is DANGLING_NAME and the next character is unexpected, expecting a syntax error for expected ':'."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "unexpectedChar"
                }
            ],
            "expected": "syntaxError(\"Unexpected value\")",
            "description": "Test when the stack is NONEMPTY_ARRAY and the next character is unexpected, expecting a syntax error for unexpected value."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "unexpectedChar"
                }
            ],
            "expected": "syntaxError(\"Expected value\")",
            "description": "Test when the stack is EMPTY_DOCUMENT and the next character is unexpected, expecting a syntax error for expected value."
        }
    ]
}