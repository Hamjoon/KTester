{
    "group": "JsonReader.peek() method additional tests",
    "cases": [
        {
            "input": [],
            "expected": "JsonToken.BEGIN_OBJECT",
            "description": "Test when doPeek() returns PEEKED_BEGIN_OBJECT, expect JsonToken.BEGIN_OBJECT."
        },
        {
            "input": [],
            "expected": "JsonToken.END_OBJECT",
            "description": "Test when doPeek() returns PEEKED_END_OBJECT, expect JsonToken.END_OBJECT."
        },
        {
            "input": [],
            "expected": "JsonToken.BEGIN_ARRAY",
            "description": "Test when doPeek() returns PEEKED_BEGIN_ARRAY, expect JsonToken.BEGIN_ARRAY."
        },
        {
            "input": [],
            "expected": "JsonToken.END_ARRAY",
            "description": "Test when doPeek() returns PEEKED_END_ARRAY, expect JsonToken.END_ARRAY."
        },
        {
            "input": [],
            "expected": "JsonToken.NAME",
            "description": "Test when doPeek() returns PEEKED_SINGLE_QUOTED_NAME, expect JsonToken.NAME."
        },
        {
            "input": [],
            "expected": "JsonToken.NAME",
            "description": "Test when doPeek() returns PEEKED_DOUBLE_QUOTED_NAME, expect JsonToken.NAME."
        },
        {
            "input": [],
            "expected": "JsonToken.NAME",
            "description": "Test when doPeek() returns PEEKED_UNQUOTED_NAME, expect JsonToken.NAME."
        },
        {
            "input": [],
            "expected": "JsonToken.BOOLEAN",
            "description": "Test when doPeek() returns PEEKED_TRUE, expect JsonToken.BOOLEAN."
        },
        {
            "input": [],
            "expected": "JsonToken.BOOLEAN",
            "description": "Test when doPeek() returns PEEKED_FALSE, expect JsonToken.BOOLEAN."
        },
        {
            "input": [],
            "expected": "JsonToken.NULL",
            "description": "Test when doPeek() returns PEEKED_NULL, expect JsonToken.NULL."
        },
        {
            "input": [],
            "expected": "JsonToken.STRING",
            "description": "Test when doPeek() returns PEEKED_SINGLE_QUOTED, expect JsonToken.STRING."
        },
        {
            "input": [],
            "expected": "JsonToken.STRING",
            "description": "Test when doPeek() returns PEEKED_DOUBLE_QUOTED, expect JsonToken.STRING."
        },
        {
            "input": [],
            "expected": "JsonToken.STRING",
            "description": "Test when doPeek() returns PEEKED_UNQUOTED, expect JsonToken.STRING."
        },
        {
            "input": [],
            "expected": "JsonToken.STRING",
            "description": "Test when doPeek() returns PEEKED_BUFFERED, expect JsonToken.STRING."
        },
        {
            "input": [],
            "expected": "JsonToken.NUMBER",
            "description": "Test when doPeek() returns PEEKED_LONG, expect JsonToken.NUMBER."
        },
        {
            "input": [],
            "expected": "JsonToken.NUMBER",
            "description": "Test when doPeek() returns PEEKED_NUMBER, expect JsonToken.NUMBER."
        },
        {
            "input": [],
            "expected": "JsonToken.END_DOCUMENT",
            "description": "Test when doPeek() returns PEEKED_EOF, expect JsonToken.END_DOCUMENT."
        },
        {
            "input": [],
            "expected": "AssertionError",
            "description": "Test when doPeek() returns an unexpected value, expect AssertionError."
        }
    ]
}