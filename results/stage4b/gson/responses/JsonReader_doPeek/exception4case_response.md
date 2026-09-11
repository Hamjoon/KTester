{
    "group": "JsonReader doPeek Method Tests",
    "cases": [
        {
            "input": [],
            "expected": "throws IOException with message 'Expected:'",
            "description": "Test when a colon is expected but not found after a dangling name, expecting an IOException for 'Expected:'."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Expected value'",
            "description": "Test when the next character is not a valid JSON value, expecting an IOException for 'Expected value'."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Unterminated object'",
            "description": "Test when there is an unexpected character in the object, expecting an IOException for 'Unterminated object'."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Unterminated array'",
            "description": "Test when there is an unexpected character in the array, expecting an IOException for 'Unterminated array'."
        },
        {
            "input": [],
            "expected": "throws IllegalStateException with message 'JsonReader is closed'",
            "description": "Test when the JsonReader is closed, expecting an IllegalStateException."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Expected name'",
            "description": "Test when a name is expected but not found, expecting an IOException for 'Expected name'."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Expected value'",
            "description": "Test when a value is expected but not found, expecting an IOException for 'Expected value'."
        },
        {
            "input": [],
            "expected": "throws IOException with message 'Unexpected value'",
            "description": "Test when an unexpected value is encountered, expecting an IOException for 'Unexpected value'."
        }
    ]
}