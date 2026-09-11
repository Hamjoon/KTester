{
    "testCases": [
        {
            "group": "peek method - PEEKED_NONE case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BEGIN_OBJECT after doPeek() sets it to PEEKED_BEGIN_OBJECT."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_OBJECT after doPeek() sets it to PEEKED_END_OBJECT."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BEGIN_ARRAY after doPeek() sets it to PEEKED_BEGIN_ARRAY."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_ARRAY after doPeek() sets it to PEEKED_END_ARRAY."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BOOLEAN after doPeek() sets it to PEEKED_TRUE."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NULL",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.NULL after doPeek() sets it to PEEKED_NULL."
                },
                {
                    "input": [],
                    "expected": "JsonToken.STRING",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.STRING after doPeek() sets it to PEEKED_SINGLE_QUOTED."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.NUMBER after doPeek() sets it to PEEKED_LONG."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_DOCUMENT",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_DOCUMENT after doPeek() sets it to PEEKED_EOF."
                },
                {
                    "input": [],
                    "expected": "AssertionError",
                    "description": "Test that calling peek() when peeked is PEEKED_NONE and doPeek() returns an invalid value throws an AssertionError."
                }
            ]
        },
        {
            "group": "peek method - PEEKED cases",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.NAME",
                    "description": "Test that calling peek() when peeked is PEEKED_SINGLE_QUOTED_NAME returns JsonToken.NAME."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NAME",
                    "description": "Test that calling peek() when peeked is PEEKED_DOUBLE_QUOTED_NAME returns JsonToken.NAME."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NAME",
                    "description": "Test that calling peek() when peeked is PEEKED_UNQUOTED_NAME returns JsonToken.NAME."
                },
                {
                    "input": [],
                    "expected": "AssertionError",
                    "description": "Test that calling peek() when peeked is an invalid value throws an AssertionError."
                }
            ]
        }
    ]
}