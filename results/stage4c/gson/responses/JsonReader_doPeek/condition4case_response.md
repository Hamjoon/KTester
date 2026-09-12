{
    "group": "JsonReader.doPeek() Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_ARRAY]"
                }
            ],
            "expected": "PEEKED_END_ARRAY",
            "description": "Test when the stack is EMPTY_ARRAY and the next character is ']', expecting PEEKED_END_ARRAY."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "]"
                }
            ],
            "expected": "PEEKED_END_ARRAY",
            "description": "Test when the stack is NONEMPTY_ARRAY and the next character is ']', expecting PEEKED_END_ARRAY."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": ","
                }
            ],
            "expected": "No exception",
            "description": "Test when the stack is NONEMPTY_ARRAY and the next character is ',', expecting no exception."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "}"
                }
            ],
            "expected": "PEEKED_END_OBJECT",
            "description": "Test when the stack is EMPTY_OBJECT and the next character is '}', expecting PEEKED_END_OBJECT."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_OBJECT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "}"
                }
            ],
            "expected": "PEEKED_END_OBJECT",
            "description": "Test when the stack is NONEMPTY_OBJECT and the next character is '}', expecting PEEKED_END_OBJECT."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.DANGLING_NAME]"
                },
                {
                    "parameter": "nextChar",
                    "value": ":"
                }
            ],
            "expected": "No exception",
            "description": "Test when the stack is DANGLING_NAME and the next character is ':', expecting no exception."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_DOCUMENT]"
                }
            ],
            "expected": "No exception",
            "description": "Test when the stack is EMPTY_DOCUMENT, expecting stack to change to NONEMPTY_DOCUMENT."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.NONEMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": -1
                }
            ],
            "expected": "PEEKED_EOF",
            "description": "Test when the stack is NONEMPTY_DOCUMENT and EOF is reached, expecting PEEKED_EOF."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.CLOSED]"
                }
            ],
            "expected": "IllegalStateException",
            "description": "Test when the stack is CLOSED, expecting an IllegalStateException."
        },
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "[JsonScope.EMPTY_ARRAY]"
                },
                {
                    "parameter": "nextChar",
                    "value": "null"
                }
            ],
            "expected": "PEEKED_NULL",
            "description": "Test when the stack is EMPTY_ARRAY and lenient mode allows a null literal, expecting PEEKED_NULL."
        },
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
                    "value": "[JsonScope.EMPTY_DOCUMENT]"
                },
                {
                    "parameter": "nextChar",
                    "value": "{"
                }
            ],
            "expected": "PEEKED_BEGIN_OBJECT",
            "description": "Test when the stack is EMPTY_DOCUMENT and the next character is '{', expecting PEEKED_BEGIN_OBJECT."
        }
    ]
}