{
    "test_cases": [
        {
            "group": "JsonReader doPeek Method Tests",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_NULL",
                    "description": "Test when the next character in a lenient mode array is a 0-length literal, expecting PEEKED_NULL."
                },
                {
                    "input": [],
                    "expected": "throws IOException with message 'Expected:'",
                    "description": "Test when a colon is expected after a dangling name but not found, expecting an IOException for 'Expected:'."
                },
                {
                    "input": [],
                    "expected": "throws IOException with message 'Unexpected value'",
                    "description": "Test when an unexpected character is found in a non-empty array, expecting an IOException for 'Unexpected value'."
                },
                {
                    "input": [],
                    "expected": "PEEKED_END_OBJECT",
                    "description": "Test when the next character is '}' in a non-empty object, expecting PEEKED_END_OBJECT."
                },
                {
                    "input": [],
                    "expected": "throws IOException with message 'Expected name'",
                    "description": "Test when an unquoted string is expected but not found, expecting an IOException for 'Expected name'."
                },
                {
                    "input": [],
                    "expected": "PEEKED_BEGIN_ARRAY",
                    "description": "Test when the next character is '[' in an empty document, expecting PEEKED_BEGIN_ARRAY."
                },
                {
                    "input": [],
                    "expected": "throws IOException with message 'Unterminated document'",
                    "description": "Test when the document is malformed and ends unexpectedly, expecting an IOException for 'Unterminated document'."
                }
            ]
        }
    ]
}