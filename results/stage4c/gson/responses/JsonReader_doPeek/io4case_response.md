{
    "group": "JsonReader.doPeek() Method Tests - Additional Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "'"
                }
            ],
            "expected": "PEEKED_SINGLE_QUOTED",
            "description": "Test when the stack is NONEMPTY_ARRAY and the next character is a single quote, expecting PEEKED_SINGLE_QUOTED."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "'"
                }
            ],
            "expected": "PEEKED_SINGLE_QUOTED_NAME",
            "description": "Test when the stack is NONEMPTY_OBJECT and the next character is a single quote, expecting PEEKED_SINGLE_QUOTED_NAME."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "\""
                }
            ],
            "expected": "PEEKED_DOUBLE_QUOTED_NAME",
            "description": "Test when the stack is NONEMPTY_OBJECT and the next character is a double quote, expecting PEEKED_DOUBLE_QUOTED_NAME."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "["
                }
            ],
            "expected": "PEEKED_BEGIN_ARRAY",
            "description": "Test when the stack is EMPTY_DOCUMENT and the next character is '[', expecting PEEKED_BEGIN_ARRAY."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "{"
                }
            ],
            "expected": "PEEKED_BEGIN_OBJECT",
            "description": "Test when the stack is NONEMPTY_DOCUMENT and the next character is '{', expecting PEEKED_BEGIN_OBJECT."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "["
                }
            ],
            "expected": "PEEKED_BEGIN_ARRAY",
            "description": "Test when the stack is NONEMPTY_DOCUMENT and the next character is '[', expecting PEEKED_BEGIN_ARRAY."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.DANGLING_NAME]"
                },
                {
                    "parameter": "nextChar",
                    "value": "="
                }
            ],
            "expected": "No exception",
            "description": "Test when the stack is DANGLING_NAME and the next character is '=', expecting no exception due to lenient mode."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "null"
                }
            ],
            "expected": "PEEKED_NULL",
            "description": "Test when the stack is NONEMPTY_ARRAY and lenient mode allows a null literal, expecting PEEKED_NULL."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "\""
                }
            ],
            "expected": "PEEKED_DOUBLE_QUOTED_NAME",
            "description": "Test when the stack is EMPTY_OBJECT and the next character is a double quote, expecting PEEKED_DOUBLE_QUOTED_NAME."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "'"
                }
            ],
            "expected": "PEEKED_SINGLE_QUOTED_NAME",
            "description": "Test when the stack is EMPTY_OBJECT and the next character is a single quote, expecting PEEKED_SINGLE_QUOTED_NAME."
        }
    ]
}