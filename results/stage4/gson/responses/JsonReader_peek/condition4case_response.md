{
    "test cases": [
        {
            "group": "test peek with no prior peeking",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test peek when no prior peeking has been done and the next token is a begin object."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test peek when no prior peeking has been done and the next token is an end object."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test peek when no prior peeking has been done and the next token is a begin array."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test peek when no prior peeking has been done and the next token is an end array."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NULL",
                    "description": "Test peek when no prior peeking has been done and the next token is null."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test peek when no prior peeking has been done and the next token is a boolean."
                },
                {
                    "input": [],
                    "expected": "JsonToken.STRING",
                    "description": "Test peek when no prior peeking has been done and the next token is a string."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test peek when no prior peeking has been done and the next token is a number."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_DOCUMENT",
                    "description": "Test peek when no prior peeking has been done and the next token is end of document."
                }
            ]
        },
        {
            "group": "test peek with prior peeking",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test peek when prior peeking has set the state to BEGIN_OBJECT."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test peek when prior peeking has set the state to END_OBJECT."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test peek when prior peeking has set the state to BEGIN_ARRAY."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test peek when prior peeking has set the state to END_ARRAY."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NULL",
                    "description": "Test peek when prior peeking has set the state to NULL."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test peek when prior peeking has set the state to BOOLEAN."
                },
                {
                    "input": [],
                    "expected": "JsonToken.STRING",
                    "description": "Test peek when prior peeking has set the state to STRING."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test peek when prior peeking has set the state to NUMBER."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_DOCUMENT",
                    "description": "Test peek when prior peeking has set the state to END_DOCUMENT."
                }
            ]
        },
        {
            "group": "test peek with assertion error",
            "cases": [
                {
                    "input": [],
                    "expected": "AssertionError",
                    "description": "Test peek when the state is set to an unexpected value, triggering an AssertionError."
                }
            ]
        }
    ]
}