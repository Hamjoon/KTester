{
    "group": "nextUnquotedValue - Exception Scenarios",
    "cases": [
        {
            "input": [],
            "expected": "EOFException",
            "description": "Test case where the buffer is empty, expecting an EOFException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "validString"
                }
            ],
            "expected": "EOFException",
            "description": "Test case where the buffer contains a valid string but the end of the stream is reached unexpectedly, expecting an EOFException."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "value1, value2; value3#"
                }
            ],
            "expected": "EOFException",
            "description": "Test case where the buffer contains multiple non-literal characters and checks if an EOFException is thrown when the end of the stream is reached unexpectedly."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "value1\\"
                }
            ],
            "expected": "EOFException",
            "description": "Test case where the buffer contains an escape character at the end and expects an EOFException when the next character is not available."
        }
    ]
}